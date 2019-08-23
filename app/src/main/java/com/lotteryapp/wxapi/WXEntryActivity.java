package com.lotteryapp.wxapi;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import com.lotteryapp.gson.AccessToken;
import com.lotteryapp.mapping.WxUser;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.litepal.crud.DataSupport;
import org.litepal.util.DBUtility;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;
    private BaseResp resp = null;
    private Properties prop=new Properties();
    public  String WX_APP_ID = "";
    private String WX_APP_SECRET = "";
    private OkHttpClient client;
    private Request request;
    String TAG="lwh";

    // 获取第一步的code后，请求以下链接获取access_token
    private String GetCodeRequest = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    // 获取用户个人信息
    private String GetUserInfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client=new OkHttpClient();
        Log.d(TAG, "onCreate: ");
        //初始化
        try {
            prop.load(this.getAssets().open("wxconfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        WX_APP_ID=prop.getProperty("appid");
        WX_APP_SECRET=prop.getProperty("secret");
        api= WXAPIFactory.createWXAPI(this,"wxf6853a239d53094e",true);
        api.handleIntent(getIntent(), this);
        //可能出错 https://blog.csdn.net/livart_corp/article/details/94354236
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.d(TAG, "onReq: ");
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d(TAG, "onresp: ");

        String result = "";
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = "发送成功";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                String code = ((SendAuth.Resp) resp).code;
                getAccessToken(code);
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "发送取消";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "发送被拒绝";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                finish();
                break;
            default:
                result = "发送返回";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                finish();
                break;
        }

    }

    /**
     * 获取access_token的URL（微信）
     *
     * @param code
     *            授权时，微信回调给的
     * @return URL
     */
    private String getCodeRequest(String code) {
        String result = null;
        GetCodeRequest = GetCodeRequest.replace("APPID",
                urlEnodeUTF8(WX_APP_ID));
        GetCodeRequest = GetCodeRequest.replace("SECRET",
                urlEnodeUTF8(WX_APP_SECRET));
        GetCodeRequest = GetCodeRequest.replace("CODE", urlEnodeUTF8(code));
        result = GetCodeRequest;
        return result;
    }


    private String getUserInfoRequest(String accesstoken,String openid) {
        String result = null;
        GetUserInfo = GetUserInfo.replace("ACCESS_TOKEN",
                urlEnodeUTF8(accesstoken));
        GetUserInfo = GetUserInfo.replace("OPENID",
                urlEnodeUTF8(openid));
        result = GetUserInfo;
        return result;
    }




    private void getAccessToken(String code){
        request=new Request.Builder().get().url(getCodeRequest(code)).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(WXEntryActivity.this, "获取accessToken失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseString=response.body().string();
                Gson go=new Gson();
                AccessToken accessToken=go.fromJson(responseString, AccessToken.class);
                getUserInfo(accessToken.accessToken,accessToken.openid);
            }
        });

    }

    private void getUserInfo(String accessToken, String openid){
        request=new Request.Builder().get().url(getUserInfoRequest(accessToken,openid)).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(WXEntryActivity.this, "获取userInfo失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseString=response.body().string();
                Gson gson=new Gson();
                WxUser wxUser=gson.fromJson(responseString, WxUser.class);
                WxUser user=DataSupport.select("openid").where("openid=?",wxUser.getOpenid()).findFirst(WxUser.class);
               if(user==null){
                   wxUser.save();
               }
               SharedPreferences.Editor editor= PreferenceManager.getDefaultSharedPreferences(WXEntryActivity.this).edit();
               editor.putString("isLogin","1");
               editor.apply();

            }
        });
    }


    private String urlEnodeUTF8(String str) {
        String result = str;
        try {
            result = URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

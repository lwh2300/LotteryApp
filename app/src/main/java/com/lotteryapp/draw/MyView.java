package com.lotteryapp.draw;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import com.lotteryapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class MyView extends View {

    Bitmap bitmap;
    Paint paint;
    List<Point> points=new ArrayList<>();
    Scroller scroller;
    int scrollHeight;
    int stateBarHeight;
    float mLastMotionY=0;
    float leftLength;
    float perWidth;
    float perHeight;
    Random random;


    public MyView(Context context, Bitmap bitmap,int heightPer) {
        super(context);
        this.bitmap=bitmap;
        perHeight=heightPer;
        init(context);

    }

    private void init(Context context){
        paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
        paint.setAlpha(100);
        paint.setStyle(Paint.Style.STROKE);
        scroller=new Scroller(context);
        WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        scrollHeight=metrics.heightPixels;
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height","dimen", "android");
        stateBarHeight = resources.getDimensionPixelSize(resourceId);
        leftLength=65*(metrics.densityDpi/160f);
        perWidth=113;
//        perWidth=(metrics.widthPixels-leftLength)/8;
        random=new Random();
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, bitmap.getWidth(), bitmap.getHeight());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,null);
        Bitmap bm=bitmap;
        Log.d("TAG", "onDraw: ");
        if(points.size()>0){
            for(Point point:points){
                canvas.drawCircle(point.x,point.y,perHeight/2,paint);
                Path path=new Path();
                if(point.getX2()!=-1&&point.getX2()!=-1) {
                    path.moveTo(point.getX(), point.getY());//设置Path的起点
                    float x=(point.getX()+point.getX2())/2+100;
                    float y=(point.getY()+point.getY2())/2+100;
                    path.quadTo(x,y, point.getX2(), point.getY2()); //设置贝塞尔曲线的控制点坐标和终点坐标
                    canvas.drawPath(path,paint);
                    canvas.drawCircle(point.x2,point.y2,perHeight/2,paint);
                }
            }
        }
    }


    float beginY;
    public void setRy(float y) {
        this.beginY = y;
    }

    public float redirectViewPosition(float clickYPosition) {
        int offsetY = (int) (clickYPosition - beginY);
        scrollBy(0,-offsetY);
        return -(clickYPosition - beginY);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
    }

    public void setPoints(Point point) {
        points.add(point);
    }

    public List<Point> getPoints() {
        return points;
    }

    float offsetY=0;
    float crruentX;
    float crruentY;

    int scrollEnable=1;
    List<Point>lines=new ArrayList<>();
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        float x = event.getX();
        crruentX=x;
        crruentY=y;
        if(x<=leftLength){
            return true;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (scrollEnable == 1){
                    if (!scroller.isFinished()) {
                        scroller.abortAnimation();
                    }
                mLastMotionY = event.getY();
                }else{
                    int Xnum= (int) ((x-leftLength)/perWidth);
                    int Ynum= (int) ((y+offsetY)/perHeight);
                    float pointX =(float) ((Xnum+0.5f)*perWidth)+leftLength;
                    float pointY =(float)((Ynum+0.5f)*perHeight);
                    Point point=new Point();
                    point.setX(pointX);
                    point.setY(pointY);
                    Log.d("offsetYy", "y:"+y+" py:"+pointY);
                    points.add(point);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(scrollEnable==1) {
                    float delt = mLastMotionY - y;
                    mLastMotionY = y;
                    scrollBy(0, (int) delt);
                    int finalyHeight = (int) (getHeight() - scrollHeight + stateBarHeight);
                    float changeY=getScrollY();
                    if (getScrollY() < 0) {
                        scrollTo(0, 0);
                        changeY=0;
                    }
                    if (getScrollY() >= finalyHeight) {
                        scrollTo(0, finalyHeight);
                        changeY=finalyHeight;
                    }
                    offsetY=changeY;
                    Log.d("offsetY", "offsetY: "+offsetY);
                }else{
                    Log.d("TAG", "can not to scroll -move");
                }
                break;
            case MotionEvent.ACTION_UP:
                if(scrollEnable==1) {
                }else {
                     Point crruentPoint=points.get(points.size()-1);
                    int Xnum= (int) ((x-leftLength)/perWidth);
                    int Ynum= (int) ((y+offsetY)/perHeight);
                    float pointX =(float) ((Xnum+0.5f)*perWidth)+leftLength;
                    float pointY =(float)((Ynum+0.5f)*perHeight);
                    if(crruentPoint.getX()==pointX&&crruentPoint.getY()==pointY){
                        break;
                    }
                    crruentPoint.setX2(pointX);
                    crruentPoint.setY2(pointY);
                }
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }


}

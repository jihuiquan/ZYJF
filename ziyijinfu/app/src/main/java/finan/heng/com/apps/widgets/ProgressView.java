package finan.heng.com.apps.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import finan.heng.com.apps.helper.ViewHelper;
import finan.zhimabao.com.apps.R;

/**
 * 显示进度的进度条
 * Created by YDYWork on 2018/5/9.
 */

public class ProgressView extends View {

    private  int CIRCLE_WIDTH = 18;//透明圆的半径
    private  int IN_CIRCLE_WIDTH = 12;//实心圆的半径
    private  int LINE_HEIGHT=10;//线的宽度
    private Paint mProgressPaint;
    private Paint mSecondPaint;
    private Paint mFloatPaint;
    private int mProgress;
    private int mTotalProgress;


    public ProgressView(Context context) {
        this(context,null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public void setProgress(int progress){
        if (progress> 100 || progress < 0){
            return;
        }
        this.mProgress = progress;
        invalidate();
    }
    public int getProgress(){

        return mProgress;
    }
    @SuppressLint("ResourceAsColor")
    private void init(Context context) {

        CIRCLE_WIDTH = ViewHelper.dp2px(context,4);
        IN_CIRCLE_WIDTH = ViewHelper.dp2px(context,2);
        LINE_HEIGHT = ViewHelper.dp2px(context,5);//暂用dp方便后续对齐的处理

        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStrokeWidth(LINE_HEIGHT);
        mProgressPaint.setColor(Color.parseColor("#5b9cf8"));
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);


        mSecondPaint = new Paint();
        mSecondPaint.setAntiAlias(true);
        mSecondPaint.setStrokeWidth(LINE_HEIGHT);
        mSecondPaint.setColor(Color.parseColor("#ffcccccc"));
        mSecondPaint.setStrokeCap(Paint.Cap.ROUND);

//        mFloatPaint = new Paint();
//        mFloatPaint.setAntiAlias(true);
//        mFloatPaint.setStrokeWidth(LINE_HEIGHT);
//        mFloatPaint.setColor(Color.parseColor("#77ff0000"));
//        mFloatPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width,height);
        mTotalProgress = width-2*CIRCLE_WIDTH;

    }

    private int getMinWidth(){
        return 2*CIRCLE_WIDTH;
    }
    private int getMinHeight(){

        return 2*CIRCLE_WIDTH;
    }

    private int measureWidth(int widthMeasureSpec){
        int result;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);

        if (mode == MeasureSpec.EXACTLY){
            return size;
        }else {
            result = getMinWidth();
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec){
        int result;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);

        if (mode == MeasureSpec.EXACTLY){
            return size;
        }else {
            result = getMinHeight();
        }
        return result;
    }
    @Override
    protected void onDraw(Canvas canvas) {

        int rightProgress = (int) (mProgress/100f*mTotalProgress);

        canvas.drawLine(CIRCLE_WIDTH,getMeasuredHeight()/2,CIRCLE_WIDTH+mTotalProgress,getMeasuredHeight()/2,mSecondPaint);
        canvas.drawLine(CIRCLE_WIDTH,getMeasuredHeight()/2,CIRCLE_WIDTH+rightProgress,getMeasuredHeight()/2,mProgressPaint);
//        canvas.drawCircle(CIRCLE_WIDTH+rightProgress,getMeasuredHeight()/2,CIRCLE_WIDTH,mFloatPaint);
//
//        canvas.drawCircle(CIRCLE_WIDTH+rightProgress,getMeasuredHeight()/2,IN_CIRCLE_WIDTH,mProgressPaint);
//        super.onDraw(canvas);
    }
}

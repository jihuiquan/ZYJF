package finan.heng.com.apps.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import finan.zhimabao.com.apps.R;

public class MyScrollView extends ScrollView {

	private OnScrollChangedListeneer onScrollChangedListeneer;// 滚动监听接口
	private int top;
	private int pageHeight;
	private int mDownY;
	private  int screenHeight;
	private static int SCROLL_DISTANCE = 100;
	private boolean needAutoScroll;

	public void setPageHeight(int pageHeight) {
		this.pageHeight = pageHeight;
	}
	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public MyScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) { // 屏蔽touch事件,才能在监听其子控件的touch事件
		// TODO Auto-generated method stub
		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:
				needAutoScroll = false;
				break;
			case MotionEvent.ACTION_MOVE:

				return super.onTouchEvent(event);
			case MotionEvent.ACTION_UP:
				int upY = (int) event.getY();
				if (upY>mDownY){//下滑
					if (needAutoScroll){
						if (pageHeight - top > SCROLL_DISTANCE){
							smoothScrollTo(0,pageHeight - screenHeight);
						}else {
							smoothScrollTo(0,pageHeight);
						}
					}
				}else if (upY<mDownY){//上滑
					if ((top+screenHeight) > pageHeight+SCROLL_DISTANCE){
						smoothScrollTo(0,pageHeight);
					}else {
						if (top + screenHeight > pageHeight){
							smoothScrollTo(0,pageHeight - screenHeight);
						}
					}
				}
				break;

		}
		return true;
//		return false;
	}
	
	@Override  
	public boolean onInterceptTouchEvent(MotionEvent event)// 屏蔽touch事件传递,才能在监听其子控件的touch事件
    {  
//        super.onInterceptTouchEvent(event);
		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:
				mDownY = (int) event.getY();
				needAutoScroll = true;
				break;
		}
        return super.onInterceptTouchEvent(event);
//		return false;
    }

	@Override
	public void onStopNestedScroll(View target) {
		if (pageHeight - top > SCROLL_DISTANCE){
			smoothScrollTo(0,pageHeight - screenHeight);
		}else {
			smoothScrollTo(0,pageHeight);
		}
		super.onStopNestedScroll(target);
	}



	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		top = t;
		super.onScrollChanged(l, t, oldl, oldt);
		if(onScrollChangedListeneer != null)
		{
			onScrollChangedListeneer.onScrollChanged(l, t, oldl, oldt);
		}
	}


	// 滚动事件监听，获取滚动的距离，用户处理一些其他事
	public interface OnScrollChangedListeneer
	{
		public void onScrollChanged(int l, int t, int oldl, int oldt);
	}

	public void setOnScrollChangedListeneer(OnScrollChangedListeneer onScrollChangedListeneer)
	{
		this.onScrollChangedListeneer = onScrollChangedListeneer;
	}

}

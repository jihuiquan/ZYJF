package finan.heng.com.apps.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/9/15.
 */

public class CanScrollView extends ScrollView {

    private ScrollListener listener;

    public CanScrollView(Context context) {
        super(context);
    }

    public CanScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (listener != null){
            listener.onScrollChanged(l,t,oldl,oldt);
        }
    }

    //停止惯性滑动
    @Override
    public void fling(int velocityY) {
        super.fling(velocityY/1000);
    }
}

package finan.heng.com.apps.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import finan.zhimabao.com.apps.R;

/**
 * Created by YDYWork on 2018/5/16.
 */

@SuppressLint("AppCompatCustomView")
public class EditTextWithDel extends TextInputEditText {
    private final static String TAG = "EditTextWithDel";
    private Drawable imgInable;
    private Drawable imgAble;
    private Context mContext;
    private Drawable mLeftDrawable;

    public EditTextWithDel(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public EditTextWithDel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.EditTextWithDel);
        if (null != typedArray) {
            int resourceId = typedArray.getResourceId(R.styleable.EditTextWithDel_leftImageSrc, -1);
            if (resourceId != -1) {
                mLeftDrawable = mContext.getResources().getDrawable(
                        resourceId);
            }
            typedArray.recycle();
        }
        init();
    }

    public EditTextWithDel(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.EditTextWithDel);
        if (null != typedArray) {
            int resourceId = typedArray.getResourceId(R.styleable.EditTextWithDel_leftImageSrc, -1);
            if (resourceId != -1) {
                mLeftDrawable = mContext.getResources().getDrawable(
                        resourceId);
            }
            typedArray.recycle();
        }
        init();
    }

    private void init() {
        imgAble = mContext.getResources().getDrawable(
                R.drawable.ic_realname_close);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                setDrawable();
            }
        });
        setDrawable();
    }

    // 设置删除图片
    private void setDrawable() {
        if (length() < 1) {
            setCompoundDrawablesWithIntrinsicBounds(mLeftDrawable, null, null, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(mLeftDrawable, null, imgAble, null);
        }
    }

    // 处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imgAble != null && event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
//            Log.e(TAG, "eventX = " + eventX + "; eventY = " + eventY);
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 50;
            if (rect.contains(eventX, eventY))
                setText("");
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}


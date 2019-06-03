package finan.heng.com.apps.app.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import finan.zhimabao.com.apps.R;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */
public class HeaderView extends LinearLayout {
    private ViewGroup leftView;
    private ViewGroup midView;
    private ViewGroup rightView;
    private ImageView leftIcon;
    private ImageView rightIcon;
    private ImageView midIcon;
    private TextView rightText;
    private TextView midText;
    private TextView bottomText;
    private ViewGroup root;
    private Context context;
    private ImageView bottomLine;
    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setupView(context, attrs);

    }

    private void setupView(Context context, AttributeSet attrs) {
        root = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.header_view, null);

//        setFocusable(true);
//        setFocusableInTouchMode(true);
        //View
        leftView = (ViewGroup) root.findViewById(R.id.left_view);
        midView = (ViewGroup) root.findViewById(R.id.mid_view);
        rightView = (ViewGroup) root.findViewById(R.id.right_view);
        //Icon
        leftIcon = (ImageView) root.findViewById(R.id.left_iv);
        rightIcon = (ImageView) root.findViewById(R.id.right_iv);
        midIcon = (ImageView) root.findViewById(R.id.mid_view_icon);
        //Text
        midText = (TextView) root.findViewById(R.id.mid_tv);
        rightText = (TextView) root.findViewById(R.id.right_tv);
        bottomText = (TextView) root.findViewById(R.id.bottom_title);
        bottomLine = root.findViewById(R.id.iv_bottom);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderView);

        bottomText.setTextColor(getResources().getColor(typedArray.getResourceId(R.styleable.HeaderView_midTextColor, (R.color.txt_gray))));

        setBackgroundColor(getResources().getColor(typedArray.getResourceId(R.styleable.HeaderView_backGround, R.color.bg_white)));
        if (typedArray.hasValue(R.styleable.HeaderView_rightIcon)) {
            rightIcon.setImageResource(typedArray.getResourceId(R.styleable.HeaderView_rightIcon, android.R.color.transparent));
            rightIcon.setVisibility(VISIBLE);
            rightView.setVisibility(View.VISIBLE);
        }
        if (typedArray.hasValue(R.styleable.HeaderView_rightText)) {
            rightText.setText(typedArray.getString(R.styleable.HeaderView_rightText));
            rightView.setVisibility(View.VISIBLE);
            rightText.setVisibility(View.VISIBLE);
        }
        leftIcon.setImageResource(typedArray.getResourceId(R.styleable.HeaderView_leftIcon, R.drawable.back_black));
        if (typedArray.hasValue(R.styleable.HeaderView_leftIcon)) {
            leftIcon.setVisibility(VISIBLE);
            leftView.setVisibility(View.VISIBLE);
        }
        midText.setTextColor(getResources().getColor(typedArray.getResourceId(R.styleable.HeaderView_midTextColor, (R.color.txt_gray))));
        midText.setText(typedArray.getString(R.styleable.HeaderView_midText));
        if (typedArray.hasValue(R.styleable.HeaderView_midText)) {
            midText.setVisibility(VISIBLE);
            midText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, typedArray.getDimension(R.styleable.HeaderView_midTextSize, 20));
        }
        bottomText.setTextColor(getResources().getColor(typedArray.getResourceId(R.styleable.HeaderView_midTextColor, (R.color.txt_gray))));
        bottomText.setText(typedArray.getString(R.styleable.HeaderView_midText));
        if (typedArray.hasValue(R.styleable.HeaderView_bottomText)) {
            bottomText.setVisibility(VISIBLE);
        }

        leftView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContext() instanceof Activity) {
                    ((Activity) getContext()).finish();
                }
            }
        });

        bottomLine.setVisibility(View.GONE);

        typedArray.recycle();

        addView(root, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void setMidText(String s) {
        midText.setVisibility(VISIBLE);
        midText.setText(s);
    }

    public void setBottomText(String s){
        bottomText.setVisibility(VISIBLE);
        bottomText.setText(s);
    }

    public CharSequence getBottomText(){
        bottomText.setVisibility(VISIBLE);
        return bottomText.getText();
    }

    public void setRightText(String s){
        rightView.setVisibility(VISIBLE);
        rightText.setVisibility(View.VISIBLE);
        rightText.setText(s);
    }

    public TextView getRightText(){
        rightView.setVisibility(VISIBLE);
        rightText.setVisibility(View.VISIBLE);
        return rightText;
    }


    public ImageView getMidIcon() {
        midIcon.setVisibility(VISIBLE);
        return midIcon;
    }

    public void setRightIcon(int imageId) {
        rightIcon.setImageResource(imageId);
        rightIcon.setVisibility(VISIBLE);
        rightView.setVisibility(View.VISIBLE);
        invalidate();
    }

    public ViewGroup getLeftView() {
        return leftView;
    }

    public ViewGroup getMidView() {
        return midView;
    }

    public ViewGroup getRightView(){
        return rightView;
    }

    public void setLeftOnClickListener(OnClickListener onClickListener) {
        leftView.setOnClickListener(onClickListener);
    }

    public void setRightOnClickListener(OnClickListener onClickListener) {
        rightView.setOnClickListener(onClickListener);
    }

}

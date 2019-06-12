package finan.heng.com.apps.app.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import finan.zhimabao.com.apps.R;


/**
 */
public class MenuItem extends LinearLayout {

    private ViewGroup delegate;
    private RelativeLayout leftGroup;
    private LinearLayout rightGroup;
    private ImageView leftImage;
    private ImageView rightImage;
    private TextView centerText;
    private TextView centerMidText;
    private TextView centerRightText;
    private ImageView circleLabel;
    public MenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupViews(context, attrs);
    }

    private void setupViews(Context context, AttributeSet attrs) {
        setBackgroundResource(R.drawable.menu_item_selector);
        delegate = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.property_item, null);
        leftImage = (ImageView) delegate.findViewById(R.id.setting_item_left_image);
        rightImage = (ImageView) delegate.findViewById(R.id.setting_item_right_image);
        centerText = (TextView) delegate.findViewById(R.id.setting_item_center_text);
        centerMidText = (TextView) delegate.findViewById(R.id.center_mid_text);
        leftGroup = (RelativeLayout) delegate.findViewById(R.id.left_view);
        rightGroup = (LinearLayout) delegate.findViewById(R.id.right_layout);
        centerRightText = (TextView) delegate.findViewById(R.id.center_right_text);
        circleLabel = (ImageView) delegate.findViewById(R.id.iv_label);
        TypedArray type = context.obtainStyledAttributes(attrs, R.styleable.ItemView);
        if (type.hasValue(R.styleable.ItemView_rightTextColor)){
            centerRightText.setTextColor(getResources().getColor(type.getResourceId(R.styleable.ItemView_rightTextColor, (R.color.txt_mene_gray))));
        }
        if (type.hasValue(R.styleable.ItemView_leftImage)) {
            leftGroup.setVisibility(VISIBLE);
            leftImage.setScaleType(ImageView.ScaleType.CENTER);
            leftImage.setImageResource(type.getResourceId(R.styleable.ItemView_leftImage, 0));

        }
        rightImage.setBackgroundResource(type.getResourceId(R.styleable.ItemView_rightImage, 0));
        centerText.setText(type.getString(R.styleable.ItemView_centerText));
        if (type.hasValue(R.styleable.ItemView_centerMidText)) {
            centerMidText.setVisibility(VISIBLE);
            centerMidText.setText(type.getString(R.styleable.ItemView_centerMidText));
        }

        if (type.hasValue(R.styleable.ItemView_position)) {
            ImageView topLine = (ImageView) delegate.findViewById(R.id.iv_line_ver_top);
            ImageView bottomLine = (ImageView) delegate.findViewById(R.id.iv_line_ver_bottom);
            int i = type.getInt(R.styleable.ItemView_position, -1);
            if (i == 0) {
                topLine.setVisibility(INVISIBLE);
            } else if (i == 1) {
                bottomLine.setVisibility(INVISIBLE);
            } else if (i == 3) {
                topLine.setVisibility(INVISIBLE);
                bottomLine.setVisibility(INVISIBLE);
            }else {
                topLine.setVisibility(INVISIBLE);
                bottomLine.setVisibility(INVISIBLE);
            }
        }
        if (type.hasValue(R.styleable.ItemView_centerRightText)){
            centerRightText.setVisibility(View.VISIBLE);
            centerRightText.setText(type.getString(R.styleable.ItemView_centerRightText));

        }
        //添加底端线是否显示功能
        if (type.hasValue(R.styleable.ItemView_bottomLine)) {
            ImageView bottom = (ImageView) delegate.findViewById(R.id.iv_line_bottom);
            int i = type.getInt(R.styleable.ItemView_bottomLine, -1);
            if (i == 0) {
                bottom.setVisibility(VISIBLE);
            } else if (i == 1) {
                bottom.setVisibility(GONE);
            }
        }

        setClickable(true);
        addView(delegate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void addRightView(View view) {
        if (view != null) {
            rightGroup.removeAllViews();
            rightGroup.addView(view);
            view.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }
    public void setRightText(String s){
        if (centerRightText != null){
            centerRightText.setText(s);
        }
    }
    public void setCenterText(String s) {
        if (centerText != null)
            centerText.setText(s);
    }

    public TextView getCenterRightText() {
        return centerRightText;
    }

    public void setRightTextBackGround(int drawable){
        if (null != centerRightText){
            centerRightText.setBackgroundResource(drawable);
        }
    }
    public void setCircleLabelState(int state){
        circleLabel.setVisibility(state);
        this.invalidate();
    }

    public void setRightTextColor(int white) {
        if (null != centerRightText){
            centerRightText.setTextColor(white);
        }
    }

    public void setGravity(int gravity){
        if (null != centerRightText){
            centerRightText.setGravity(gravity);
        }
    }
}

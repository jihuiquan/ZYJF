package finan.heng.com.apps.utils;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.app.home.activity.FindJiaoYiMimaActivity;
import finan.heng.com.apps.app.my.adapter.InputPawAdapter;


/**
 * Created by Administrator on 2016/9/19.
 */
public class WithdrawlDialog extends Dialog {


    private String number;
    private int type;
    private Context context;
    private GridView key_input_grid;
    private InputPawAdapter inputPawAdapter;


    public WithdrawlDialog(Context context, int themeResId, int layoutId, String number, int type) {
        super(context, themeResId);
        this.context = context;
        this.number = number;
        this.type = type;
        setContentView(layoutId);
        TextView tvType = (TextView) findViewById(R.id.dialog_trader_password_ct);
        if (type == 1) {
            tvType.setText("充值");
        } else if (type == 2) {
            tvType.setText("提现");
        }

        setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = ScreenUtils.getScreenWidth(context);
        Window dialogWindow = getWindow();
        dialogWindow.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, R.color.transparent)));
        dialogWindow.setAttributes(params);
        dialogWindow.setGravity(Gravity.BOTTOM);
        initView();
        setClick();
    }

    public void showPSWError(String text) {
        TextView error = (TextView) findViewById(R.id.tv_again);
        error.setText(text);
        error.setVisibility(View.VISIBLE);
        shakeView(error);
    }


    private ArrayList<Integer> passwords = new ArrayList<>();

    private void setClick() {
        inputPawAdapter.setNumberItemClick(new InputPawAdapter.NumberItemClick() {
            @Override
            public void itemOnClick(int position) {
                if (position != 11 && passwords.size() < 6) {
                    passwords.add(position);
                } else if (passwords.size() > 0 && 11 == position) {
                    passwords.remove(passwords.size() - 1);
                }
                if (passwords.size() == 6) {
                    if (trueButton != null) {
                        String pwd = "";
                        for (int i = 0; i < passwords.size(); i++) {
                            pwd += passwords.get(i);
                        }
                        trueButton.getText(pwd);
                    }
                    if (type == 2){
                        passwords.clear();
                    } else if (type != 0 && type !=1) {
                        cancel();
                    } else {
                        passwords.clear();
                    }
                }
                setPassWord(passwords);

            }
        });

    }

    private void shakeView(View view) {
        int delta = 20;
        PropertyValuesHolder pvhTranslateX = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
                Keyframe.ofFloat(0f, 0),
                Keyframe.ofFloat(.10f, -delta),
                Keyframe.ofFloat(.26f, delta),
                Keyframe.ofFloat(.42f, -delta),
                Keyframe.ofFloat(.58f, delta),
                Keyframe.ofFloat(.74f, -delta),
                Keyframe.ofFloat(.90f, delta),
                Keyframe.ofFloat(1f, 0f)
        );
        ObjectAnimator.ofPropertyValuesHolder(view, pvhTranslateX).
                setDuration(500).start();
    }

    private ArrayList<TextView> textViews = new ArrayList<>();

    private void setPassWord(ArrayList<Integer> passwords) {
        for (int i = 0; i < textViews.size(); i++) {
            textViews.get(i).setText("");
        }
        for (int i = 0; i < passwords.size(); i++) {
            textViews.get(i).setText(passwords.get(i) + "");
        }
    }

    private TrueButton trueButton;

    public void setTrueButton(TrueButton trueButton) {
        this.trueButton = trueButton;
    }

    private void initView() {
        TextView tvMoney = (TextView) findViewById(R.id.dialog_trader_password_money);
        tvMoney.setText(Html.fromHtml(number));
        key_input_grid = (GridView) findViewById(R.id.key_input_grid);
        TextView position_one = (TextView) findViewById(R.id.position_one);
        TextView position_two = (TextView) findViewById(R.id.position_two);
        TextView position_three = (TextView) findViewById(R.id.position_three);
        TextView position_four = (TextView) findViewById(R.id.position_four);
        TextView position_five = (TextView) findViewById(R.id.position_five);
        TextView position_six = (TextView) findViewById(R.id.position_six);

        ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
        TextView forgetPaw = (TextView) findViewById(R.id.tv_forget_pasword);

        textViews.add(position_one);
        textViews.add(position_two);
        textViews.add(position_three);
        textViews.add(position_four);
        textViews.add(position_five);
        textViews.add(position_six);
        inputPawAdapter = new InputPawAdapter(context);
        key_input_grid.setAdapter(inputPawAdapter);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
        forgetPaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, FindJiaoYiMimaActivity.class));
            }
        });

    }
}

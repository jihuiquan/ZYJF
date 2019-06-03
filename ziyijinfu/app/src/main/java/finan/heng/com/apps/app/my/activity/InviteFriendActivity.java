package finan.heng.com.apps.app.my.activity;
/*
 * Created by hhm on 2017/5/5.
 */

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.system.text.ShortMessage;
import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.app.my.fragment.BottomDialogFragment;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.manager.entity.model.CommonHttpModel;
import finan.heng.com.apps.model.InviteFriendBean;
import finan.heng.com.apps.utils.DeviceUtils;
import finan.zhimabao.com.apps.R;
import rx.functions.Action1;


public class InviteFriendActivity extends BaseActivity implements View.OnClickListener {

    //    private RelativeLayout rlRight;
//    private TextView tvCode, tvGuiZe;
//    private ImageView ivErWeiMa;
    private String cusCode;
    //    private TextView ivOne, ivTwo, ivThree, ivFour, ivFive;
    private TextView copyText, inviteCode, shareFriend, rules, rightText;
    private String url;
    private String title;
    private String content;
    private String shareImageUrl;
    private ImageView qrImage;
    private ImageView img_share;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friend);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        String activityTitle = getIntent().getStringExtra("title");
        setBarTitle(activityTitle);
        initView();
        init();
//        MobSDK.init(getApplicationContext(), "20fd45ceb4d20", "530525291318eabcd74e35b3c1fde5b9");
        title = "子壹金服国资控股，投资更灵活资金更安全";
        content = "专享1000元红包+0.5%加息券+12%年化投资";
        getSystemData();
    }

    private void getSystemData() {
        showLoadingDialog();
        new HttpHelper().initInviteFriends().subscribe(new Action1<CommonHttpModel<InviteFriendBean>>() {
            @Override
            public void call(CommonHttpModel<InviteFriendBean> inviteFriendBeanCommonHttpModel) {
                try {
                    if (inviteFriendBeanCommonHttpModel.code.equals("0")) {
                        InviteFriendBean result = inviteFriendBeanCommonHttpModel.result;
                        setBarTitle(result.title);
                        Glide.with(InviteFriendActivity.this).load(result.shareBackgroundImgUrl).into(img_share);
                        title = inviteFriendBeanCommonHttpModel.result.inviteTitle;
                        content = inviteFriendBeanCommonHttpModel.result.inviteDescription;
                        url = inviteFriendBeanCommonHttpModel.result.shareLink;
                        cusCode = inviteFriendBeanCommonHttpModel.result.cusCode;
                        shareImageUrl = inviteFriendBeanCommonHttpModel.result.shareImageUrl;
                        inviteCode.setText(cusCode);
                        if (!TextUtils.isEmpty(url)) {
                            qrImage.setImageBitmap(DeviceUtils.createQRCode(url, DeviceUtils.dp2px(getApplicationContext(), 70)));
                        }
                    }
                    dismissLoadingDialog();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                try {
                    dismissLoadingDialog();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void initView() {
        rules = findViewById(R.id.view_rules);
        shareFriend = findViewById(R.id.share_to_friends);
        copyText = findViewById(R.id.copy_code);
        inviteCode = findViewById(R.id.invite_code);
        rightText = findViewById(R.id.tv_guizeshuoming);
        qrImage = findViewById(R.id.qr_code);
        img_share = findViewById(R.id.img_share);
    }

    private void initData() {
        rightText.setText("邀请记录");
        rightText.setVisibility(View.VISIBLE);

    }

    private void init() {
        rightText.setOnClickListener(this);
        shareFriend.setOnClickListener(this);
        rules.setOnClickListener(this);
        copyText.setOnClickListener(this);
        try {
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_guizeshuoming:
                startActivity(new Intent(InviteFriendActivity.this, InviteHistoryActivity.class)
                        .putExtra("url", url)
                        .putExtra("title", title)
                        .putExtra("content", content)
                        .putExtra("shareImageUrl", shareImageUrl)
                );

                break;
            case R.id.copy_code:
                copy();
                break;
            case R.id.share_to_friends:
                showDialog();
                break;
            case R.id.view_rules:
                Intent intent2 = new Intent(InviteFriendActivity.this, WebActivity.class);
                intent2.putExtra("title", "邀请奖励规则");
                intent2.putExtra("url", URLHelper.getInstance().buildUrl(IConstants.Server.ADDRESS_REWARD_RULE));
                startActivity(intent2);
                break;
        }
    }

    private void showDialog() {
        if (!TextUtils.isEmpty(url)) {
            BottomDialogFragment dialogFragment = new BottomDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", title);
            bundle.putString("content", content);
            bundle.putString("url", url);
            bundle.putString("shareImageUrl", url);
            dialogFragment.setArguments(bundle);
            dialogFragment.show(getSupportFragmentManager(), "dialog");
        }
    }

    private void copy() {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", inviteCode.getText().toString());
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
//        Toast.makeText(this,"复制成功",Toast.LENGTH_SHORT).show();
        ViewHelper.showToast(this, "复制成功");

    }

    private void sendMessage() {
        Platform platwx = ShareSDK.getPlatform(ShortMessage.NAME);
        final OnekeyShare okwx = new OnekeyShare();
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        okwx.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                toast("分享取消");
            }

            @Override
            public void onCancel(Platform platform, int i) {
                toast("分享取消");
            }
        });
        if (platwx.getName() != null) {
            okwx.setPlatform(platwx.getName());
        }
        //关闭sso授权
        okwx.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        okwx.setTitle(title);
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
//        okwx.setTitleUrl("http://api.hengll.com/hotspot/compony");
        // text是分享文本，所有平台都需要这个字段
        okwx.setText(content);
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//        okwx.setImageUrl("http://admin.hengll.com/upload/image/logo.png");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
//        okwx.setUrl("http://api.hengll.com/hotspot/compony");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        okwx.setComment(" ");
        // site是分享此内容的网站名称，仅在QQ空间使用
//        okwx.setSite("子壹金服");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        okwx.setSiteUrl("http://api.hengll.com/hotspot/compony");
        //启动分享

        okwx.show(this);
    }

    /**
     * 跳转Activity
     * @param title
     * @param mContext
     */
    public static void show( String title, Context mContext){
        Intent intent = new Intent(mContext, InviteFriendActivity.class);
        intent.putExtra("title", title);
        mContext.startActivity(intent);
    }
}

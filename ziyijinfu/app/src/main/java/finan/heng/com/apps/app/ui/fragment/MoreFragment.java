package finan.heng.com.apps.app.ui.fragment;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dynamic.foundations.common.assist.Log;
import com.dynamic.foundations.common.utils.StringUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.app.my.activity.AboutUsActivity;
import finan.heng.com.apps.app.my.activity.GongGaoDetailActivity;
import finan.heng.com.apps.app.my.activity.JiaoYiJiLuActivity;
import finan.heng.com.apps.app.my.activity.YiJianFanKuiActivity;
import finan.heng.com.apps.app.presenter.MorePresenter;
import finan.heng.com.apps.app.ui.widget.MenuItem;
import finan.heng.com.apps.app.view.IMoreView;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.CompanyInfoResponseDeta;
import finan.heng.com.apps.model.MoreModel;
import finan.heng.com.apps.model.MoreModelResponse;
import finan.heng.com.apps.model.TradeTypeModelList;
import finan.heng.com.apps.model.TradeTypeModelResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.utils.SharedpreferenceUtil;
import finan.zhimabao.com.apps.R;
import rx.functions.Action1;


/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class MoreFragment extends finan.heng.com.apps.base.BaseFragment implements IMoreView {
    ClipboardManager cmb;
    private View actionView;
    private TextView titleText;
    private RelativeLayout backBtn;
    //    private LinearLayout menu_aboutus;
    private MenuItem menu_safe;
    private MenuItem menu_notice;
    private MenuItem menu_help;
    private MenuItem menu_mark;
    private MenuItem menu_wechat;
    private ImageView iv_phone;
    private String phone;
    MorePresenter presenter = new MorePresenter(this);
    private String mHelperCenterUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, null);
//        menu_aboutus = (MenuItem) view.findViewById(R.id.menu_aboutus);
        menu_safe = view.findViewById(R.id.menu_safe);
        menu_notice = view.findViewById(R.id.menu_notice);
        menu_help = view.findViewById(R.id.menu_help);
        menu_mark = view.findViewById(R.id.menu_mark);
        menu_wechat = view.findViewById(R.id.menu_wechat);
        iv_phone = view.findViewById(R.id.iv_phone);
        menu_safe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result != null) {
                    YiJianFanKuiActivity.show(result.getFeedbackLabel(), getActivity());
                } else {
                    YiJianFanKuiActivity.show("意见反馈", getActivity());
                }
            }
        });
        menu_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result != null) {
                    GongGaoDetailActivity.show(URLHelper.getInstance().buildUrl(IConstants.Server.ADDRESS_NOTICECENTER),
                            result.getNoticeLabel(), getActivity());
                } else {
                    GongGaoDetailActivity.show(URLHelper.getInstance().buildUrl(IConstants.Server.ADDRESS_NOTICECENTER),
                            getString(R.string.menu_notice), getActivity());
                }
            }
        });
        menu_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mHelperCenterUrl != null ? mHelperCenterUrl : "";
                if (result != null) {
                    WebActivity.show(url, result.getHelpLabel(), getActivity());
                } else {
                    WebActivity.show(url, getString(R.string.menu_help), getActivity());
                }
            }
        });

        cmb = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);

        setUpToolbar(view);
        menu_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doMark();
            }
        });
        iv_phone.setClickable(false);
        iv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.i("result" + "phone" + phone);
                showPointDialog(phone);
            }
        });
        initData();
        return view;
    }

    private void initData() {
    }

    private CompanyInfoResponseDeta result;

    /**
     * 更新界面
     *
     * @param result
     */
    private void updateUi(CompanyInfoResponseDeta result) {
        if (result == null) {
            return;
        }
        this.result = result;
        setBarTitle(result.getTitle());
        menu_notice.setCenterText(result.getNoticeLabel());
        menu_help.setCenterText(result.getHelpLabel());
        menu_safe.setCenterText(result.getFeedbackLabel());
        menu_wechat.setCenterText(result.getWechatLabel());
    }

    /**
     * 去打分
     */
    private void doMark() {
        //调应用市场
        boolean isHad = false;
        final PackageManager packageManager = getActivity().getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (TextUtils.equals("com.tencent.android.qqdownloader", pn)) {
                    isHad = true;
                }
            }
        }
        if (isHad) {
            Uri uri = Uri.parse("market://details?id=" + getActivity().getPackageName());
            Intent in = new Intent(Intent.ACTION_VIEW, uri);
            in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(in);
        } else {
            ViewHelper.showToast(getActivity(), "请先安装应用宝");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getCompanyInfo();
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void copyByClick(String s) {
        if (Build.VERSION.SDK_INT >= 11) {
            cmb.setPrimaryClip(ClipData.newPlainText("子壹金服", s));
        } else {
            cmb.setText(s);
        }
        toast("已复制到粘贴板，请到微信上关注");
    }

    /**
     * 现实通知条数
     *
     * @param artNoRead
     */
    @Override
    public void showNoticeNumber(int artNoRead) {
        Logger.i("result" + artNoRead);
//        menu_notice.setCircleLabelState(artNoRead > 0 ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * 网上加载
     *
     * @param phoneImgUrl
     */
    @Override
    public void showCompanyImg(String phoneImgUrl) {
//        Log.i("return",phoneImgUrl);
        Glide.with(getActivity().getApplicationContext())
                .load(phoneImgUrl)
                .into(iv_phone);
    }

    @Override
    public void showCompanyMobile(final String phone) {
        Logger.i("result" + "phone" + phone);
        if (StringUtils.isNotEmpty(phone)) {
            this.phone = phone;
            iv_phone.setClickable(true);
        }
    }

    @Override
    public void showCompanyWechat(final String wechat) {
//        menu_wechat.setCenterText("微信公众号:" + wechat);
        menu_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyByClick(wechat);
            }
        });
    }

    @Override
    public void setHelperCenterUrl(String helperCenterUrl) {
        mHelperCenterUrl = helperCenterUrl;
    }

    @Override
    public void success(CompanyInfoResponseDeta response) {
        updateUi(response);
    }

    private void showPointDialog(final String phone) {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(getActivity(), R.style.dialog);
        View inflate = View.inflate(getActivity(), R.layout.loginout_dialog, null);
        normalDialog.setView(inflate);
        // 显示
        final AlertDialog alertDialog = normalDialog.create();
        alertDialog.show();
        TextView loginout_content = (TextView) inflate.findViewById(R.id.loginout_content);
        loginout_content.setText(phone);
        TextView loginout_quxiao = (TextView) inflate.findViewById(R.id.loginout_quxiao);
        loginout_quxiao.setText("取消");
        loginout_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        TextView loginout_sure = (TextView) inflate.findViewById(R.id.loginout_sure);
        loginout_sure.setText("拨打");
        loginout_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                startActivity(intent);
            }
        });
    }
}

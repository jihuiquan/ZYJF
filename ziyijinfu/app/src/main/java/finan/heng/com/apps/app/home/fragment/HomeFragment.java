package finan.heng.com.apps.app.home.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.util.AbSharedUtil;
import com.bumptech.glide.Glide;
import com.dynamic.foundations.common.utils.ArithUtils;
import com.dynamic.foundations.common.utils.StringUtils;
import com.mcxiaoke.bus.Bus;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.transformer.ABaseTransformer;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.MainActivity;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.app.finance.activity.ProductDetailModifyActivity;
import finan.heng.com.apps.app.home.activity.EvaluationSuccessActivity;
import finan.heng.com.apps.app.home.activity.RiskEvaluationActivity;
import finan.heng.com.apps.app.home.activity.SetJiaoYiMimaActivity;
import finan.heng.com.apps.app.home.activity.ShiMingRenZhengActivity;
import finan.heng.com.apps.app.home.activity.TouziActivity;
import finan.heng.com.apps.app.my.activity.ChongZhiListActivity;
import finan.heng.com.apps.app.my.activity.InviteFriendActivity;
import finan.heng.com.apps.app.presenter.HomePresenter;
import finan.heng.com.apps.app.ui.activity.CheckPhoneActivity;
import finan.heng.com.apps.app.ui.activity.VerifyInfoActivity;
import finan.heng.com.apps.app.view.IHomeView;
import finan.heng.com.apps.base.BaseApplication;
import finan.heng.com.apps.base.BaseFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.manager.entity.model.BannerModel;
import finan.heng.com.apps.manager.entity.model.CommonHttpModel;
import finan.heng.com.apps.manager.entity.model.NotificationModel;
import finan.heng.com.apps.manager.entity.model.ProductModel;
import finan.heng.com.apps.model.EvaluationSubmitResult;
import finan.heng.com.apps.model.HomeInfo;
import finan.heng.com.apps.model.LoginResponse;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.model.ProductInfo;
import finan.heng.com.apps.model.event.AccountRefreshEvent;
import finan.heng.com.apps.model.event.HomeInfoRefreshEvent;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.GlideImageLoader;
import finan.heng.com.apps.utils.ProjectUtil;
import finan.heng.com.apps.utils.SharedpreferenceUtil;
import finan.heng.com.apps.widgets.NoticeView;
import finan.zhimabao.com.apps.R;
import rx.functions.Action1;

/*
 * Created by hhm on 2017/4/22.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, IHomeView {

    //    private Banner banner, emptyBanner;
    private Banner banner;
    private Button btnPurchase;
    private NoticeView mScollTextView, emptyScrllTextview;
    private String status;
    private int id;
    private LinearLayout mLlScroll, emptymLLScroll;
    private LinearLayout hotRoot, hotroot, ll_activity_label,hotroot_empty;
    private ListView lvProduct;
    private View headerView;
    private TextView tv_rate_desc, tv_rate, text1, text2;
    HomePresenter presenter = new HomePresenter(this);
    private SmartRefreshLayout mPullToRefreshView;
    CommonAdapter<ProductModel> adapter;
    private List<ProductModel> productList = new ArrayList<>();
    private LinearLayout ll_empty, ll_normal_lable;
    private ImageView img_NewUser;
    private AlertDialog loading;
    private TextView tv_safe, tv_guide, tv_invite, tv_home_helpcenter, tv_platform_intro;
//    private TextView tv_safe_empty, tv_guide_empty, tv_invite_empty, tv_home_helpcenter_empty, tv_platform_intro_empty;
    private TextView mLeftTitle,tv_term;
    private ImageView mImageTag;
    private String platformDataUrl;
    private TextView productName;
    private LinearLayout productTags;

    private String mPlatformIntroUrl;
    private String mHelpCenterUrl;
    private String mGuideUrl;
    private String mSafeUrl;

    private LinearLayout ll_platformIntro,ll_safe,ll_invite,ll_userGuide,ll_helpCenter;
    private ImageView img_platformIntro,img_safe,img_invite,img_userGuide,img_helpCenter;
    private HomeInfo homeInfo;
    private static final int LOGIN = 0x101;
    private ImageView img_noData,img_emptyData;
    private TextView tv_bottomTips;
    private ImageView img_product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bus.getDefault().register(this);
        EventBus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setUpToolbar(view);
        loading = finan.heng.com.apps.utils.DialogUtil.getInstance().showLoading(getActivity());
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getHomeInfo();
    }

    private void initView(final View view) {
        img_noData = view.findViewById(R.id.img_noData);
        headerView = LayoutInflater.from(getActivity()).inflate(R.layout.header_home, null);
        img_product = headerView.findViewById(R.id.img_product);
        /**平台简介*/
        ll_platformIntro = headerView.findViewById(R.id.ll_platformIntro);
        img_platformIntro = headerView.findViewById(R.id.img_platformIntro);
        /**安全保障*/
        ll_safe = headerView.findViewById(R.id.ll_safe);
        img_safe = headerView.findViewById(R.id.img_safe);
        /**好友邀请*/
        ll_invite = headerView.findViewById(R.id.ll_invite);
        img_invite = headerView.findViewById(R.id.img_invite);
        /**新手引导*/
        ll_userGuide = headerView.findViewById(R.id.ll_userGuide);
        img_userGuide = headerView.findViewById(R.id.img_userGuide);
        /**帮助中心*/
        ll_helpCenter = headerView.findViewById(R.id.ll_helpCenter);
        img_helpCenter = headerView.findViewById(R.id.img_helpCenter);

        view.findViewById(R.id.bottom_line).setVisibility(View.GONE);
        lvProduct = view.findViewById(R.id.lv_product);

        mPullToRefreshView = view.findViewById(R.id.refreshview);
        mPullToRefreshView.setEnableOverScrollBounce(false);
        ll_empty = view.findViewById(R.id.ll_empty);
//        emptyBanner = view.findViewById(R.id.banner);
        emptyScrllTextview = view.findViewById(R.id.fragment_home_scolltextview);
        emptymLLScroll = view.findViewById(R.id.fragment_home_scroll);
        img_NewUser = headerView.findViewById(R.id.home_newer);
        mLeftTitle = headerView.findViewById(R.id.left_title);
        tv_term = headerView.findViewById(R.id.tv_term);
        mImageTag = headerView.findViewById(R.id.image_tag);
        img_NewUser.setVisibility(isLogin() ? View.GONE : View.VISIBLE);
        img_NewUser.setOnClickListener(this);
        tv_safe = headerView.findViewById(R.id.tv_safe);
        tv_guide = headerView.findViewById(R.id.new_user_guide);
        tv_invite = headerView.findViewById(R.id.tv_invite);
        tv_home_helpcenter = headerView.findViewById(R.id.tv_home_helpcenter);
        tv_platform_intro = headerView.findViewById(R.id.tv_platform_intro);
        ll_safe.setOnClickListener(this);

        ll_invite.setOnClickListener(this);
        ll_userGuide.setOnClickListener(this);
        ll_platformIntro.setOnClickListener(this);
        ll_helpCenter.setOnClickListener(this);
        ll_normal_lable = headerView.findViewById(R.id.ll_normal_lable);
        ll_activity_label = headerView.findViewById(R.id.ll_activity_label);

        banner = headerView.findViewById(R.id.banner);
        mLlScroll = headerView.findViewById(R.id.fragment_home_scroll);
        text1 = headerView.findViewById(R.id.text1);
        text2 = headerView.findViewById(R.id.text2);
        tv_rate_desc = headerView.findViewById(R.id.tv_rate_desc);
        tv_rate = headerView.findViewById(R.id.tv_rate);
        productName = headerView.findViewById(R.id.tv_product_name);
        productTags = headerView.findViewById(R.id.product_tags);
        btnPurchase = headerView.findViewById(R.id.btn_purchase);
        mScollTextView = headerView.findViewById(R.id.fragment_home_scolltextview);
        headerView.findViewById(R.id.fragment_home_right_arrow).setOnClickListener(this);
        hotRoot = headerView.findViewById(R.id.hotroot);
        hotroot = headerView.findViewById(R.id.hotroot);
        hotroot_empty = headerView.findViewById(R.id.hotroot_empty);
        img_emptyData = headerView.findViewById(R.id.img_emptyData);
        initClick();

        mPullToRefreshView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.getHomeInfo();
            }
        });
        adapter = new CommonAdapter<ProductModel>(getActivity(), R.layout.item_product_home, productList) {
            @Override
            protected void convert(ViewHolder viewHolder, final ProductModel item, int position) {
//                int plstimeLimitType = item.getPlstimeLimitType();
                viewHolder.setText(R.id.finance_adapter_item_times,item.getPrdtimeLimitTypeLabel());
                int status = item.getStatus();
                TextView tv_immediateLoan = viewHolder.getView(R.id.tv_immediateLoan);
                //20.预告中；30.募集中：立即出借；40+.已售罄
                if(status==20){
                    tv_immediateLoan.setBackgroundResource(R.drawable.shape_rect_white_stroke_yellow);
                    tv_immediateLoan.setTextColor(getResources().getColor(R.color.color_FDD912));
                }else if(status==30){
                    tv_immediateLoan.setBackgroundResource(R.drawable.shape_rect_white_stroke_blue);
                    tv_immediateLoan.setTextColor(getResources().getColor(R.color.color_5B9CF8));
                }if(status>=40){
                    tv_immediateLoan.setBackgroundResource(R.drawable.shape_rect_white_stroke_gray);
                    tv_immediateLoan.setTextColor(getResources().getColor(R.color.color_9F9F9F));
                }
                tv_immediateLoan.setText(item.getButtonLabel());

                viewHolder.setText(R.id.tv_repaymentTypeLabel,item.getRepaymentTypeLabel());
                viewHolder.setText(R.id.tv_profitLabel,item.getProfitLabel());

                TextView finance_adapter_item_name = viewHolder.getView(R.id.finance_adapter_item_name);
                finance_adapter_item_name.setText(item.getTitle());

//                ProgressView progressView = viewHolder.getView(R.id.progress_bar);
                View secondLine = viewHolder.getView(R.id.second_line);

                TextView finance_adapter_item_rate = viewHolder.getView(R.id.finance_adapter_item_rate);
                SpannableStringBuilder rateText = new SpannableStringBuilder();
                double baseRate = ArithUtils.round(item.getProfit() * 100, 1);
                double addRate = ArithUtils.round(item.getProfitFloat() * 100, 1);
                SpannableString label = new SpannableString("%");
                label.setSpan(new RelativeSizeSpan(0.7f), 0, label.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                SpannableString addRates = new SpannableString("+" + IConstants.Formatter.rateFormat.format(addRate) + "%");
                addRates.setSpan(new RelativeSizeSpan(0.7f), 0, addRates.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                rateText.append(String.valueOf(IConstants.Formatter.rateFormat.format(baseRate))).append(label);
                if (item.isHasAddRate()) {
                    rateText.append(addRates);
                } else if (item.getCashRateShow()) {
                    if (!TextUtils.isEmpty(item.getCashRateProfit())) {
                        addRate = ArithUtils.round(Double.parseDouble(item.getCashRateProfit()) * 100, 1);
                        addRates = new SpannableString("+" + IConstants.Formatter.rateFormat.format(addRate) + "%");
                        addRates.setSpan(new RelativeSizeSpan(0.7f), 0, addRates.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        rateText.append(addRates);
                    }
                }
                finance_adapter_item_rate.setText(rateText);
                TextView item_period = viewHolder.getView(R.id.item_period);
                item_period.setText(String.valueOf(item.getPlstimeLimitValue()));

                if (item.getSurplusAmount() >= 10000) {
                    Double surplus = ArithUtils.div2point(item.getSurplusAmount(), 10000, 2);
                } else {
                }


                ImageView ivStatus = viewHolder.getView(R.id.iv_status);

                switch (item.getStatus()) {// //-41.复审未通过；-11.初审未通过；10.待初审；11.初审通过；20.预告中；30.募集中；40.待复审；41.复审通过；50.还款中（计息中）；60.已完成；
                    case ProductModel.STATUS_REVIEW_PASS:

                        break;
                    case ProductModel.STATUS_FIRST_PASS:

                        break;
                    case ProductModel.STATUS_FIRST_WAITING:

                        break;
                    case ProductModel.STATUS_FIRST_THROUGH:

                        break;
                    case ProductModel.STATUS_NOTICE:

                        ivStatus.setImageResource(R.drawable.ic_product_status_pre);
                        secondLine.setVisibility(View.VISIBLE);
                        ivStatus.setVisibility(View.VISIBLE);
                        break;
                    case ProductModel.STATUS_PURCHASE:
                        ivStatus.setVisibility(View.GONE);
                        break;
                    case ProductModel.STATUS_REVIEW_WAITING:
                    case ProductModel.STATUS_REVIEW_THROUGH:
                    case ProductModel.STATUS_REPAYMENT:
                    case ProductModel.STATUS_FINISH:
                        ivStatus.setImageResource(R.drawable.ic_product_status_over);
                        secondLine.setVisibility(View.GONE);
                        break;
                }
                viewHolder.getView(R.id.root).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gotoDetail(item.getProductsId());
                    }
                });
                LinearLayout ll_activity_label = viewHolder.getView(R.id.ll_activity_label);
                ll_activity_label.removeAllViews();
                ll_activity_label.setVisibility(item.isActivity() ? View.VISIBLE : View.GONE);
                String[] tags = item.getTags();
                try {
                    for (int i = 0; i < tags.length; i++) {
                        TextView textView1 = new TextView(getActivity());
                        textView1.setBackgroundResource(R.drawable.home_fragment_activity_tv_bg);
                        textView1.setTextSize(12);
                        textView1.setGravity(Gravity.CENTER);
                        textView1.setPadding(10, 10, 10, 10);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        if (i != 0) {//第一个不添加margin
                            layoutParams.setMargins(15, 0, 0, 0);
                        }
                        textView1.setLayoutParams(layoutParams);
                        textView1.setVisibility(item.isActivity() ? View.VISIBLE : View.GONE);
                        textView1.setText(tags[i]);
                        textView1.setTextColor(getResources().getColor(R.color.txt_product_blue));
                        if (StringUtils.isNotEmpty(tags[i])) {
                            ll_activity_label.addView(textView1);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        lvProduct.addHeaderView(headerView);
        try {
            View footView = LayoutInflater.from(getActivity()).inflate(R.layout.finace_warning, null);
            tv_bottomTips = footView.findViewById(R.id.tv_bottomTips);
            lvProduct.addFooterView(footView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lvProduct.setAdapter(adapter);
    }

    /**
     * 根据url判断要跳转的页面
     * @param url
     * @param title webActivity标题
     * @param LinkUrl 加载的url
     */
    private void goActivity(String url,String title,String LinkUrl) {
        if(TextUtils.isEmpty(url)){
            return;
        }
        if(url.equals("URL")){
            WebActivity.show(LinkUrl,title,getActivity());
        }else {
            goLinkUrl(url);
        }
    }

    /**
     * 根据linkUrl跳转指定的页面
     * @param linkUrl
     */
    private void goLinkUrl(String linkUrl) {
        if(TextUtils.isEmpty(linkUrl)){
            return;
        }
        if(linkUrl.equals("ToShare")){
            startActivity(new Intent(getActivity(), InviteFriendActivity.class));
        }else if(linkUrl.equals("ToLogin")){
            startActivityForResult(new Intent(getActivity(), CheckPhoneActivity.class), LOGIN);
        }else if(linkUrl.equals("ToList")){
            startActivity(new Intent(getActivity(), MainActivity.class).putExtra("fromWeb", "fromWeb"));
        }else if(linkUrl.equals("ToRecharge")){
            startActivity(new Intent(getActivity(), ChongZhiListActivity.class).putExtra(IConstants.Extra.RECHARGE_TYPE, "1"));
        }else if(linkUrl.equals("ToAccount")){
            EventBus.getDefault().post(new AccountRefreshEvent());
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra("fromWeb", "gotoaccount");
            startActivity(intent);
        }else if(linkUrl.equals("ToRealName")){
            startActivity(new Intent(getActivity(), VerifyInfoActivity.class));
        }else if(linkUrl.equals("ToRiskAssessment")){
            MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
            if (cacheData == null){
                startActivityForResult(new Intent(getActivity(), CheckPhoneActivity.class), LOGIN);
            }else {
                if (TextUtils.isEmpty(cacheData.result.investStyle)){//未测评
                    startActivity(new Intent(getActivity(), RiskEvaluationActivity.class));
                }else{
                    getData(cacheData.result.investStyle);
                }
            }
        }
    }

    private void getData(final String investStyle) {
        showLoadingDialog();
        new HttpHelper().getInvestTypeInfo(investStyle).subscribe(new Action1<CommonHttpModel<EvaluationSubmitResult>>() {
            @Override
            public void call(CommonHttpModel<EvaluationSubmitResult> commonResult) {
                try {
                    dismissLoadingDialog();
                    if (commonResult.code.equals("0")) {
                        startActivity(new Intent(getActivity(), EvaluationSuccessActivity.class)
                                .putExtra(EvaluationSuccessActivity.INVEST_TYPE, investStyle)
                                .putExtra(EvaluationSuccessActivity.DESCRIPTION, commonResult.result.tipsOne)
                                .putExtra(EvaluationSuccessActivity.TYPE_DETAIL, commonResult.result.tipsTwo));
                    } else {
                        ViewHelper.showToast(getActivity(), commonResult.message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                try {
                    dismissLoadingDialog();
                    ViewHelper.showToast(getActivity(), throwable.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initClick() {
        mLlScroll.setOnClickListener(this);
    }

    public void onEvent(HomeInfoRefreshEvent infoRefreshEvent) {
        Logger.i("onevent" + "HomeInfoRefreshEvent");
        presenter.getHomeInfo();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Bus.getDefault().unregister(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_touzi:
                investment();
                break;

            case R.id.home_newer:
                goActivity(homeInfo.getRegisterAction(),"",homeInfo.getRegisterUrl());
                break;

            case R.id.ll_safe:
                goActivity(homeInfo.getSafeAction(),homeInfo.getSafeTitle(),homeInfo.safeUrl);
                break;

            case R.id.ll_invite:
                goActivity(homeInfo.getInviteAction(),homeInfo.getInviteTitle(),homeInfo.getInviteUrl());
                break;

            case R.id.ll_userGuide:
                goActivity(homeInfo.getGuideAction(),homeInfo.getGuideTitle(),homeInfo.guideUrl);
                break;

            case R.id.ll_platformIntro:
                goActivity(homeInfo.getPlatformIntroAction(),homeInfo.getPlatformIntroTitle(),homeInfo.platformIntroUrl);
                break;

            case R.id.ll_helpCenter:
                goActivity(homeInfo.getHelpCenterAction(),homeInfo.getHelpCenterTitle(),homeInfo.helpCenterUrl);
                break;
        }
    }

    private void investment() {
        if ((status.equals("30"))) {
            MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
            if (cacheData == null) {
                Intent intent = new Intent(getActivity(), CheckPhoneActivity.class);
                startActivity(intent);
            } else if ("0".equals(cacheData.result.realnameStatus) || "0".equals(cacheData.result.cardStatus)) {
                Intent intent = new Intent(getActivity(), ShiMingRenZhengActivity.class);
                startActivity(intent);
            } else if (TextUtils.isEmpty(cacheData.result.userPaypassword)) {
                Intent intent = new Intent(getActivity(), SetJiaoYiMimaActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(getActivity(), TouziActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        } else {
            Intent intent = new Intent(getActivity(), ProductDetailModifyActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
    }

    /**
     * 显示公告
     *
     * @param articleList
     */
    @Override
    public void showArticles(ArrayList<NotificationModel> articleList) {
        setArticles(articleList, mLlScroll, mScollTextView);
        setArticles(articleList, emptymLLScroll, emptyScrllTextview);
    }

    private void setArticles(final ArrayList<NotificationModel> articleList, LinearLayout mLlScroll, final NoticeView mScollTextView) {

        if (articleList != null) {
            final ArrayList<String> objects = new ArrayList<>();
            for (int i = 0; i < articleList.size(); i++) {
                Logger.i("hottitle" + articleList.get(i).getHotTitle());
                objects.add(articleList.get(i).getHotTitle());
            }
            mScollTextView.start(objects);
            mScollTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = StringUtils.trimToEmpty(articleList.get(mScollTextView.getIndex()).getHotTitle());
                    String url = StringUtils.trimToEmpty(articleList.get(mScollTextView.getIndex()).getUrl());
                    if (StringUtils.isNotEmpty(url)) {
                        startActivity(new Intent(getActivity(), WebActivity.class)
                                .putExtra(IConstants.Extra.TITLE, title)
                                .putExtra(IConstants.Extra.URL, url)
                        );
                    }
                }
            });
            mLlScroll.setVisibility(View.VISIBLE);
        } else {
            mLlScroll.setVisibility(View.GONE);
        }
    }

    /**
     * 显示banner
     *
     * @param bannerList
     */
    @Override
    public void showBanners(final ArrayList<BannerModel> bannerList) {
        setBanner(bannerList, banner);
//        setBanner(bannerList, emptyBanner);
    }

    private void setBanner(final ArrayList<BannerModel> bannerList, Banner banner) {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        ArrayList<String> imgUrls = new ArrayList<>();
        for (int i = 0; i < bannerList.size(); i++) {
            imgUrls.add(bannerList.get(i).getBannerImgae());
        }
        banner.setImages(imgUrls);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                //position标记是从1开始的
                BannerModel homeBannner = bannerList.get(position - 1);
                if (TextUtils.isEmpty(homeBannner.getImgaeLinkUrl())) {
                    return;
                }
                String url = homeBannner.getImgaeLinkUrl();
//                String url = "http://192.168.100.240:8080/luckdraw3?login";
                if (url.endsWith("login")) {
                    MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
                    if (cacheData != null) {
                        LoginResponse response = DataCache.instance.getCacheData("heng", "LoginResponse");
                        if (response != null) {
                            try {
                                if (StringUtils.isNotEmpty(response.result.sessionId)) {
                                    url = url + "=" + response.result.sessionId;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("title", homeBannner.getImgTitle());
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(4000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void showProducts(ArrayList<ProductModel> productList) {
        this.productList.clear();
        this.productList.addAll(productList);
        adapter.notifyDataSetChanged();
    }

    private void doCilck(int status, int productsId, boolean isNew) {
        if (isNew) {
            gotoHot(productsId);
        } else {
            gotoDetail(productsId);
        }
    }

    /**
     * 去普通详情
     *
     * @param productsId
     */
    private void gotoDetail(int productsId) {
        Intent intent = new Intent(getActivity(), ProductDetailModifyActivity.class);
        intent.putExtra(IConstants.Extra.ID, productsId);
        startActivity(intent);
    }

    /**
     * 去新手标详情
     *
     * @param productsId
     */
    private void gotoHot(int productsId) {
        Intent intent = new Intent(getActivity(), ProductDetailModifyActivity.class);
        Logger.i("productsId" + productsId);
        intent.putExtra(IConstants.Extra.ID, productsId);
        startActivity(intent);
    }

    @Override
    public void showHotActivity(final ProductModel productInfo, boolean isNew) {
        if (productInfo != null) {
            SharedpreferenceUtil.put("newInvestor", true);
            hotroot.setVisibility(View.VISIBLE);
            hotroot_empty.setVisibility(View.GONE);
//            tv_product_title.setText(productInfo.getProductTitle());
            TextView t1 = headerView.findViewById(R.id.text1_each);

            if (isNew) {
                t1.setText("元");
            } else {//授予可投
                if (productInfo.getSurplusAmount() > 10000){
                    t1.setText("万元");
                }else {
                    t1.setText("元");
                }
            }
            text1.setText(productInfo.getLeftDesc());
            text2.setText(productInfo.getRightDesc()+ "");
            TextView t2 = headerView.findViewById(R.id.text2_each);
            t2.setText(productInfo.getPeriodDesc(productInfo.getPlstimeLimitType()));
            tv_term.setText(productInfo.getPlstimeLimitLabel());
            mLeftTitle.setText(productInfo.getInvestBeginLabel());
            Glide.with(getActivity()).load(productInfo.getTypeIcon()).into(mImageTag);
            tv_rate_desc.setText(productInfo.getProfitLabel());
            btnPurchase.setText(productInfo.getButtonLabel());

            btnPurchase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doCilck(productInfo.getStatus(), productInfo.getProductsId(), true);
                }
            });
            SpannableStringBuilder rateText = new SpannableStringBuilder();
            double baseRate = ArithUtils.round(productInfo.getProfit() * 100, 2);
            double addRate = ArithUtils.round(productInfo.getProfitFloat() * 100, 2);
            SpannableString addRates;
            if (addRate > 0) {
                addRates = new SpannableString("%+" + IConstants.Formatter.rateFormat.format(addRate) + "%");
            } else {
                addRates = new SpannableString("%");
            }
            addRates.setSpan(new RelativeSizeSpan(0.7f), 0, addRates.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            rateText.append(String.valueOf(IConstants.Formatter.rateFormat.format(baseRate))).append(addRates);
            tv_rate.setText(rateText);
            productName.setText(productInfo.getTitle() == null ? "" : productInfo.getTitle());
            hotRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoHot(productInfo.getProductsId());
                }
            });
            Logger.i("hotactivity" + productInfo.isActivity() + productInfo.getTags());
            productTags.removeAllViews();
            productTags.setVisibility(productInfo.isActivity() ? View.VISIBLE : View.GONE);
            String[] tags = productInfo.getTags();
            for (int i = 0; i < tags.length; i++) {
                TextView textView1 = new TextView(getActivity());
                textView1.setBackgroundResource(R.drawable.home_fragment_activity_tv_bg);
                textView1.setTextSize(12);
                textView1.setGravity(Gravity.CENTER);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(15, 0, 0, 0);
                textView1.setPadding(10, 10, 10, 10);
                textView1.setLayoutParams(layoutParams);
                textView1.setVisibility(productInfo.isActivity() ? View.VISIBLE : View.GONE);
                textView1.setText(tags[i]);
                textView1.setTextColor(getResources().getColor(R.color.txt_product_blue));
                if (StringUtils.isNotEmpty(tags[i])) {
                    productTags.addView(textView1);
                }
            }
        } else {
            SharedpreferenceUtil.put("newInvestor", false);
            hotroot.setVisibility(View.GONE);
            hotroot_empty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showDesc(String newTitle, String newDescribe, String productTitile, String productDescribe) {
    }

    @Override
    public void finishRefresh() {
        if (loading != null && loading.isShowing()) {
            loading.dismiss();
        }
        mPullToRefreshView.finishRefresh();
    }

    @Override
    public void isShowEmptyView(boolean empty) {
        lvProduct.setVisibility(empty ? View.GONE : View.VISIBLE);
        ll_empty.setVisibility(empty ? View.VISIBLE : View.GONE);
        String localUrl = SharedpreferenceUtil.getString("noDataUrl");
        if(!TextUtils.isEmpty(localUrl)){
            Glide.with(getActivity()).load(localUrl).into(img_noData);
            Glide.with(getActivity()).load(localUrl).into(img_emptyData);
        }
    }

    @Override
    public void showViewByLoginStatus(boolean safeShow, boolean invitShow, boolean registerShow
            , boolean helpCenterShow, boolean guideShow, boolean platformIntroShow) {
        Logger.i("result" + "showUnLoginView=" + safeShow);
        img_NewUser.setVisibility(registerShow ? View.VISIBLE : View.GONE);
        ll_invite.setVisibility(invitShow ? View.VISIBLE : View.GONE);
        ll_safe.setVisibility(safeShow ? View.VISIBLE : View.GONE);
        ll_platformIntro.setVisibility(platformIntroShow ? View.VISIBLE : View.GONE);
        ll_helpCenter.setVisibility(helpCenterShow ? View.VISIBLE : View.GONE);
        ll_userGuide.setVisibility(guideShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showNormalProductsLabel(boolean isShow) {
        ll_normal_lable.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void passPlatformDataUrl(String platformDataUrl) {
        this.platformDataUrl = platformDataUrl;
    }

    /**
     * 获取首页成功
     * @param homeInfo
     */
    @Override
    public void setTabUrl(final HomeInfo homeInfo) {
        if (homeInfo != null) {
            mPlatformIntroUrl = homeInfo.platformIntroUrl;
            mGuideUrl = homeInfo.guideUrl;
            mHelpCenterUrl = homeInfo.helpCenterUrl;
            mSafeUrl = homeInfo.safeUrl;

            this.homeInfo = homeInfo;
            tv_platform_intro.setText(homeInfo.getPlatformIntroTitle());
            Glide.with(getActivity()).load(homeInfo.getPlatformIntroIcon()).into(img_platformIntro);
            tv_safe.setText(homeInfo.getSafeTitle());
            Glide.with(getActivity()).load(homeInfo.getSafeIcon()).into(img_safe);
            tv_invite.setText(homeInfo.getInviteTitle());
            Glide.with(getActivity()).load(homeInfo.getInviteIcon()).into(img_invite);
            tv_guide.setText(homeInfo.getGuideTitle());
            Glide.with(getActivity()).load(homeInfo.getGuideIcon()).into(img_userGuide);
            tv_home_helpcenter.setText(homeInfo.getHelpCenterTitle());
            Glide.with(getActivity()).load(homeInfo.getHelpCenterIcon()).into(img_helpCenter);
            setBarTitle(homeInfo.getTitle());
            Glide.with(getActivity()).load(homeInfo.getRegisterImageUrl()).into(img_NewUser);

            ProductInfo newProduct = homeInfo.newProduct;
            if(newProduct!=null){
                tv_term.setText(newProduct.getPlstimeLimitLabel());
                mLeftTitle.setText(newProduct.getInvestBeginLabel());
                Glide.with(getActivity()).load(newProduct.getTypeIcon()).into(mImageTag);
                tv_rate_desc.setText(newProduct.getProfitLabel());
                btnPurchase.setText(newProduct.getButtonLabel());
            }else {
                ProductInfo starProduct = homeInfo.starProduct;
                tv_term.setText(starProduct.getPlstimeLimitLabel());
                mLeftTitle.setText(starProduct.getInvestBeginLabel());
                Glide.with(getActivity()).load(starProduct.getTypeIcon()).into(mImageTag);
                tv_rate_desc.setText(starProduct.getProfitLabel());
                btnPurchase.setText(starProduct.getButtonLabel());
            }
            tv_bottomTips.setText(homeInfo.getHomeSlogan());
            Glide.with(getActivity()).load(homeInfo.getProductListImageUrl()).into(img_product);
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    if(!TextUtils.isEmpty(homeInfo.getHomeBackgroundImageUrl())){
                        try {
                            String localUrl = ProjectUtil.download(getActivity(), homeInfo.getHomeBackgroundImageUrl());
                            SharedpreferenceUtil.put("noDataUrl",localUrl);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
    }

    public void initUpData() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (loading != null) {
            loading.dismiss();
            loading = null;
        }
    }
}

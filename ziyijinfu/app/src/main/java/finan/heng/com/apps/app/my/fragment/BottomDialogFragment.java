package finan.heng.com.apps.app.my.fragment;


import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;

import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.system.text.ShortMessage;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import finan.heng.com.apps.base.BaseApplication;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.utils.DeviceUtils;
import finan.zhimabao.com.apps.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomDialogFragment extends DialogFragment implements View.OnClickListener {


    private TypedArray drawables;
    private String[] names;
    private String url;
    private String title;
    private String content;
    private String shareImageUrl;
    public BottomDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.BottomDialog);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_bottom_dialog, container, false);
        try{
            init(view);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (getArguments() != null) {
            url = getArguments().getString("url");
            title = getArguments().getString("title");
            content = getArguments().getString("content");
            shareImageUrl = getArguments().getString("shareImageUrl");

        }
        return view;
    }

    private void init(View view) {
        names = getActivity().getResources().getStringArray(R.array.share_names);
        drawables = getActivity().getResources().obtainTypedArray(R.array.share_pic);
        view.findViewById(R.id.close_dialog).setOnClickListener(this);
        GridView gridView = view.findViewById(R.id.grid_view);
        gridView.setAdapter(new GridAdapter());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dismiss();
                try {
                    itemSelected(position);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void itemSelected(int position) {
        switch (position) {
            case 0://微信
                Platform platwx2 = ShareSDK.getPlatform(Wechat.NAME);
                final OnekeyShare okwx2 = new OnekeyShare();
                //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
                if (platwx2.getName() != null) {
                    okwx2.setPlatform(platwx2.getName());
                }
                okwx2.setCallback(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        ViewHelper.showToast(BaseApplication.getApplication(), "分享取消");
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        ViewHelper.showToast(BaseApplication.getApplication(), "分享取消");
                    }
                });
                //关闭sso授权
                okwx2.disableSSOWhenAuthorize();
                okwx2.setUrl(url);
                okwx2.setTitleUrl(url);
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                okwx2.setTitle(title);
                // text是分享文本，所有平台都需要这个字段
                okwx2.setText(content);
                //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
                okwx2.setImageUrl(shareImageUrl);
                okwx2.show(getActivity());
                break;
            case 1://朋友圈
                Platform platwx = ShareSDK.getPlatform(WechatMoments.NAME);
                final OnekeyShare okwx = new OnekeyShare();
                //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
                if (platwx.getName() != null) {
                    okwx.setPlatform(platwx.getName());
                }
                okwx.setCallback(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        ViewHelper.showToast(BaseApplication.getApplication(), "分享取消");
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        ViewHelper.showToast(BaseApplication.getApplication(), "分享取消");
                    }
                });
                //关闭sso授权
                okwx.disableSSOWhenAuthorize();
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                okwx.setTitle(title);
                // text是分享文本，所有平台都需要这个字段
                okwx.setText(content);
                //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
                okwx.setImageUrl(shareImageUrl);
                okwx.setUrl(url);
                okwx.show(getActivity());

                break;
            case 2://QQ好友
                Platform plat = ShareSDK.getPlatform(QQ.NAME);
                final OnekeyShare oks = new OnekeyShare();
                //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
                if (plat.getName() != null) {
                    oks.setPlatform(plat.getName());
                }


                oks.setTitle(title);
                // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
                oks.setTitleUrl(url);
                // text是分享文本，所有平台都需要这个字段
                oks.setText(content);
                oks.setUrl(url);
                //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
                oks.setImageUrl(shareImageUrl);

                oks.setCallback(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        ViewHelper.showToast(BaseApplication.getApplication(), "分享取消");
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        ViewHelper.showToast(BaseApplication.getApplication(), "分享取消");
                    }
                });
                oks.show(getActivity());
                break;
            case 3://QQ空间
                Platform plats = ShareSDK.getPlatform(QZone.NAME);
                final OnekeyShare ok = new OnekeyShare();
                //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
                if (plats.getName() != null) {
                    ok.setPlatform(plats.getName());
                }
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                ok.setTitle(title);
                // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
                ok.setTitleUrl(url);
                // text是分享文本，所有平台都需要这个字段
                ok.setText(content);
                //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
                ok.setImageUrl(shareImageUrl);
                //启动分享
                ok.setCallback(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        ViewHelper.showToast(BaseApplication.getApplication(), "分享取消");
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        ViewHelper.showToast(BaseApplication.getApplication(), "分享取消");
                    }
                });
                ok.show(getActivity());

                break;
            case 4://短信
                sendMessage();
                break;
        }

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
                ViewHelper.showToast(BaseApplication.getApplication(), "分享取消");
            }

            @Override
            public void onCancel(Platform platform, int i) {
                ViewHelper.showToast(BaseApplication.getApplication(), "分享取消");
            }
        });
        if (platwx.getName() != null) {
            okwx.setPlatform(platwx.getName());
        }
        //关闭sso授权
        okwx.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        okwx.setTitle(title);

        // text是分享文本，所有平台都需要这个字段
        okwx.setText(content+url);


        okwx.show(getActivity());

    }

    @Override
    public void onStart() {

        super.onStart();
    }


    @Override
    public void onClick(View v) {
        dismiss();
    }

    class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return names == null ? 0 : names.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if (convertView == null) {

                textView = new TextView(getActivity());
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.parseColor("#666666"));
//                textView.setCompoundDrawablePadding(DeviceUtils.dp2px(getActivity(),6));
                textView.setPadding(0, DeviceUtils.dp2px(getActivity(), 10), 0, 0);
                convertView = textView;


            } else {
                textView = (TextView) convertView;
            }
            textView.setText(names[position]);
            Drawable drawable = drawables.getDrawable(position);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(null, drawable, null, null);


            return convertView;
        }
    }
}

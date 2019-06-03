package finan.heng.com.apps.app.presenter;

import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ab.http.AbFileHttpResponseListener;
import com.ab.http.AbHttpUtil;
import com.ab.util.AbDialogUtil;
import com.ab.view.progress.AbHorizontalProgressBar;
import com.dynamic.foundations.common.utils.AndroidUtil;
import com.mcxiaoke.bus.Bus;
import com.orhanobut.logger.Logger;

import java.io.File;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.MainActivity;
import finan.heng.com.apps.app.view.IMainView;
import finan.heng.com.apps.model.UpdateInfoRespond;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.zhimabao.com.apps.R;
import rx.functions.Action1;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class MainPresenter extends BasePresenter {
    IMainView view;
    // ProgressBar进度控制
    private AbHorizontalProgressBar mAbProgressBar;
    // 最大100
    private int max = 100;
    private int progress = 0;
    private TextView numberText, maxText, tvProgress;
    private DialogFragment mAlertDialog = null;
    public MainPresenter(IMainView view) {
        this.view = view;
    }
    public void getUpdateInfo(final String currentVersion,final String channel){
        new HttpHelper().getUpdateInfo(channel).subscribe(new Action1<UpdateInfoRespond>() {
            @Override
            public void call(UpdateInfoRespond updateInfoRespond) {
//                Logger.i("updateinfo" + updateInfoRespond);
                if (updateInfoRespond != null && updateInfoRespond.isSuccess()){
                    if ( updateInfoRespond.result.isNeedUpdate(currentVersion)){
                        view.viewUpdateView(updateInfoRespond.result.isForceUpdate()
                                , updateInfoRespond.result.link
                                , updateInfoRespond.result.description
                                , updateInfoRespond.result.version
                        );
                    }
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    view.toast(requestErrorThrowable.getMessage());
                }
            }
        });
    }

    public void downFile(final MainActivity mainActivity, String url) {
        AbHttpUtil.getInstance(mainActivity).get(url, new AbFileHttpResponseListener(url) {


            // 获取数据成功会调用这里
            @Override
            public void onSuccess(int statusCode, File file) {
                AbDialogUtil.removeDialog(mainActivity);
                Log.i("path", "ttttt" +file.getAbsolutePath());
                installApk(mainActivity, file);
            }

            // 开始执行前
            @Override
            public void onStart() {
                //打开进度框
                View v = LayoutInflater.from(mainActivity).inflate(R.layout.progress_bar_horizontal, null);
                mAbProgressBar = (AbHorizontalProgressBar) v.findViewById(R.id.horizontalProgressBar);
                tvProgress = (TextView) v.findViewById(R.id.progress);
                tvProgress.setText("0%");
                numberText = (TextView) v.findViewById(R.id.numberText);
                maxText = (TextView) v.findViewById(R.id.maxText);

                maxText.setText(progress + "/" );
                mAbProgressBar.setMax(max);
                mAbProgressBar.setProgress(progress);

                mAlertDialog = AbDialogUtil.showAlertDialog("正在下载", v);
                mAlertDialog.setCancelable(false);
            }

            // 失败，调用
            @Override
            public void onFailure(int statusCode, String content,
                                  Throwable error) {
                AbDialogUtil.removeDialog(mainActivity);
                view.toast(error.getMessage());
                view.setUpdateValue();

            }

            // 下载进度
            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                tvProgress.setText(bytesWritten / (totalSize / max) + "%");
                maxText.setText(bytesWritten + "/" + totalSize);
                mAbProgressBar.setProgress((int) (bytesWritten / (totalSize / max)));
            }

            // 完成后调用，失败，成功
            public void onFinish() {
                AbDialogUtil.removeDialog(mainActivity);
                view.setUpdateValue();
            }
        });
    }


    private void installApk(MainActivity mainActivity, File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");//编者按：此处Android应为android，否则造成安装不了
        mainActivity.startActivity(intent);
    }
}

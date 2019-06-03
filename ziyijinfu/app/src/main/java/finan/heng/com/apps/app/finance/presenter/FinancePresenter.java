package finan.heng.com.apps.app.finance.presenter;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.app.finance.view.IFinanceView;
import finan.heng.com.apps.app.presenter.BasePresenter;
import finan.heng.com.apps.model.FinanceTitleResponse;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/10/24.
 */

public class FinancePresenter extends BasePresenter {
    IFinanceView view;
    Subscription subscription;
    public FinancePresenter(IFinanceView view){
        this.view = view;
    }
    public void getDataWithDialog(){
        view.showDialog();
    }
    public void getData(){
       subscription =
        new HttpHelper().getFinanceTitle().subscribe(new Action1<FinanceTitleResponse>() {
            @Override
            public void call(FinanceTitleResponse financeTitleResponse) {
                try {
                    view.dismissDialog();
                    view.init(financeTitleResponse);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                try {
                    view.showError();
                    view.toast(throwable.getMessage());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public void destory(){
        if (subscription != null){
            subscription.unsubscribe();
        }
        view = null;
    }

}

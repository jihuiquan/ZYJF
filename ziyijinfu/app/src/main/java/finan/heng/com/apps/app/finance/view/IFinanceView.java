package finan.heng.com.apps.app.finance.view;

import finan.heng.com.apps.app.view.IBaseView;
import finan.heng.com.apps.model.FinanceTitleResponse;

/**
 * Created by Administrator on 2017/10/24.
 */

public interface IFinanceView extends IBaseView {
    void showDialog();
    void dismissDialog();
    void showError();
    void init(FinanceTitleResponse response);
}

package finan.heng.com.apps.app.home.view;

import finan.heng.com.apps.app.view.IBaseView;
import finan.heng.com.apps.model.EvaluationResponse;
import finan.heng.com.apps.model.EvaluationSubmitResponse;

/**
 * Created by Administrator on 2017/9/20.
 */

public interface IRiskEvaluationView extends IBaseView{
    void finishActivity();

    void initViews(EvaluationResponse responseBodyResponse);

    void showResult(EvaluationSubmitResponse evaluationSubmitResponse);
    void showLoading();

}

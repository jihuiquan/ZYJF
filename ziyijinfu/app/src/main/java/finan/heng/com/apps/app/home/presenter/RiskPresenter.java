package finan.heng.com.apps.app.home.presenter;

import java.util.HashMap;
import java.util.Map;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.app.home.view.IRiskEvaluationView;
import finan.heng.com.apps.app.presenter.BasePresenter;
import finan.heng.com.apps.model.EvaluationResponse;
import finan.heng.com.apps.model.EvaluationSubmitResponse;
import finan.heng.com.apps.model.RiskDefaultResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/9/20.
 */

public class RiskPresenter extends BasePresenter {

    IRiskEvaluationView riskEvaluationView;



    public RiskPresenter(IRiskEvaluationView evaluationView){
        riskEvaluationView = evaluationView;
    }
    public void destroy(){
        riskEvaluationView = null;
    }
    public void submitAnswer(HashMap<String,String> answers){
        riskEvaluationView.showLoading();
        new HttpHelper().sendAnswer(answers).subscribe(new Action1<EvaluationSubmitResponse>() {
            @Override
            public void call(EvaluationSubmitResponse evaluationSubmitResponse) {
                if (evaluationSubmitResponse.code.equals("0")){
                    riskEvaluationView.showResult(evaluationSubmitResponse);
                }else {
                    riskEvaluationView.toast(evaluationSubmitResponse.message);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                try {
                    if (throwable instanceof RequestErrorThrowable) {
                        RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                        riskEvaluationView.toast(requestErrorThrowable.getMessage());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    public void getData(){
        riskEvaluationView.showLoading();
        new HttpHelper().getEvaluationList().subscribe(new Action1<EvaluationResponse>() {
            @Override
            public void call(EvaluationResponse responseBodyResponse) {
                riskEvaluationView.initViews(responseBodyResponse);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    riskEvaluationView.toast(requestErrorThrowable.getMessage());
                }
            }
        });
    }
    public void defaultRisk(){
        riskEvaluationView.showLoading();
        new HttpHelper().riskDefault().subscribe(new Action1<RiskDefaultResponse>() {
            @Override
            public void call(RiskDefaultResponse riskDefaultResponse) {
                if (riskDefaultResponse.code.equals("0")){
                    riskEvaluationView.finishActivity();
                }else {
                    riskEvaluationView.toast(riskDefaultResponse.message);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    riskEvaluationView.toast(requestErrorThrowable.getMessage());
                }
            }
        });
    }



}

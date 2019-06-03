package finan.heng.com.apps.app.home.presenter;

import java.util.HashMap;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.app.home.view.ICardView;
import finan.heng.com.apps.app.home.view.IRiskEvaluationView;
import finan.heng.com.apps.app.presenter.BasePresenter;
import finan.heng.com.apps.model.CardTypeResponse;
import finan.heng.com.apps.model.EvaluationResponse;
import finan.heng.com.apps.model.EvaluationSubmitResponse;
import finan.heng.com.apps.model.RiskDefaultResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/9/20.
 */

public class CardPresenter extends BasePresenter {

    ICardView cardView;



    public CardPresenter(ICardView evaluationView){
        cardView = evaluationView;
    }
   public void getData(){
       new HttpHelper().getCardTypeList().subscribe(new Action1<CardTypeResponse>() {
           @Override
           public void call(CardTypeResponse cardTypeResponse) {

               cardView.init(cardTypeResponse.result.cdkeyTypeList);
           }
       }, new Action1<Throwable>() {
           @Override
           public void call(Throwable throwable) {

           }
       });
   }



}

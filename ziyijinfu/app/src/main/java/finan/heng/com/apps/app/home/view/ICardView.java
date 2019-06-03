package finan.heng.com.apps.app.home.view;

import java.util.List;

import finan.heng.com.apps.app.view.IBaseView;
import finan.heng.com.apps.model.CardTypeModel;
import finan.heng.com.apps.model.EvaluationResponse;
import finan.heng.com.apps.model.EvaluationSubmitResponse;

/**
 * Created by Administrator on 2017/9/20.
 */

public interface ICardView extends IBaseView{
    void init(List<CardTypeModel> models);

}

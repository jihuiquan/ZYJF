package finan.heng.com.apps.app.presenter;

import finan.heng.com.apps.app.view.IAssetView;
import finan.heng.com.apps.manager.BaseModel;
import finan.heng.com.apps.manager.entity.request.GetProductListRequest;
import finan.heng.com.apps.manager.entity.respond.MsgRespond;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class AssetPresenter extends BasePresenter{
    IAssetView view;

    public AssetPresenter(IAssetView view) {
        this.view = view;
    }
    public void getProductList(String id, String s){

        productManager.getProductList(new GetProductListRequest(id, s), new BaseModel.OnDataLoadListener() {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onFail(MsgRespond respond) {

            }

            @Override
            public void onNetworkError(String msg) {

            }

            @Override
            public void onFinish() {

            }
        });
    }
}

package finan.heng.com.apps.app.finance.view;

import finan.heng.com.apps.app.view.IBaseView;

import finan.heng.com.apps.model.ProductDetailResponse;

/**
 * Created by Administrator on 2017/10/24.
 */

public interface IProductDetailView extends IBaseView {

    void requestSuccess(ProductDetailResponse response);
    void requestFailed(Throwable throwable);
}

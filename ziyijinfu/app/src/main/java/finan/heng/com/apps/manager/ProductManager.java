package finan.heng.com.apps.manager;

import finan.heng.com.apps.app.view.IBaseView;
import finan.heng.com.apps.manager.entity.request.GetHomeInfoReqeust;
import finan.heng.com.apps.manager.entity.request.GetProductListRequest;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public interface ProductManager extends IBaseManager{
    void getHomeInfo(GetHomeInfoReqeust reqeust, BaseModel.OnDataLoadListener listener);

    void getProductList(GetProductListRequest request, BaseModel.OnDataLoadListener listener);
}

package finan.heng.com.apps.manager.impl;

import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.manager.BaseModel;
import finan.heng.com.apps.manager.ProductManager;
import finan.heng.com.apps.manager.entity.request.GetHomeInfoReqeust;
import finan.heng.com.apps.manager.entity.request.GetProductListRequest;
import finan.heng.com.apps.manager.entity.respond.HomeInfoRespond;
import finan.heng.com.apps.manager.entity.respond.ProductListRespond;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class ProductManagerImpl extends BaseManager implements ProductManager{

    @Override
    public void getHomeInfo(GetHomeInfoReqeust reqeust, BaseModel.OnDataLoadListener listener) {
        post(reqeust, URLHelper.getInstance().buildUrl(IConstants.Server.ADDRESS_HOMEINFO), HomeInfoRespond.class, listener);
    }

    @Override
    public void getProductList(GetProductListRequest request, BaseModel.OnDataLoadListener listener) {
        post(request, URLHelper.getInstance().buildUrl(IConstants.Server.ADDRESS_PRODUCTLIST), ProductListRespond.class, listener);
    }
}

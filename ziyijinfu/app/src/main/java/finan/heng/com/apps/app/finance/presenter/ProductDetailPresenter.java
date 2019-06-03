package finan.heng.com.apps.app.finance.presenter;


import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.app.finance.view.IProductDetailView;
import finan.heng.com.apps.model.ProductDetailResponse;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/11/16.
 */

public class ProductDetailPresenter  {
    IProductDetailView view;
    Subscription subscription;
    public ProductDetailPresenter(IProductDetailView view){
        this.view = view;
    }

    public void getData(int id){
        subscription = new HttpHelper().getProduct(id).subscribe(new Action1<ProductDetailResponse>() {
            @Override
            public void call(ProductDetailResponse productDetailResponse) {
                view.requestSuccess(productDetailResponse);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                view.requestFailed(throwable);
            }
        });
    }
    public void destroy(){
        if (subscription != null){
            subscription.unsubscribe();
        }
        view = null;
    }
}

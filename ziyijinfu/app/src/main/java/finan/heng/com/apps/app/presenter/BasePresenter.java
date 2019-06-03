package finan.heng.com.apps.app.presenter;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.greenrobot.event.EventBus;
import finan.heng.com.apps.manager.ManagerFactory;
import finan.heng.com.apps.manager.ProductManager;

import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @param <T>
 */
public abstract class BasePresenter<T> implements Serializable {
    protected ObjectMapper mapper = new ObjectMapper();
    protected Reference<T> mViewRef; // view接口类型的弱饮用

    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view); // 建立关联
    }

    protected T getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    protected ProductManager productManager = ManagerFactory.getInstance().getProductManager();

    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }


    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }


}

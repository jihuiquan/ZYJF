package finan.heng.com.apps.manager;


import finan.heng.com.apps.manager.entity.respond.MsgRespond;

/**
 */
public class BaseModel {

    public interface OnDataLoadListener<T> {

        void onSuccess(T t);

        void onFail(MsgRespond respond);

        void onNetworkError(String msg);
        
        void onFinish();
    }
}

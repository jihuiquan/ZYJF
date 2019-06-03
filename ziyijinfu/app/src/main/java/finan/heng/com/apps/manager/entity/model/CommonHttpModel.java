package finan.heng.com.apps.manager.entity.model;



/**
 * Created by Administrator on 2017/10/7.
 */

public class CommonHttpModel<T> {
    public String code;
    public String message;
    public T result;
    public boolean isSuccess(){
        return code.equals("0");
    }
}

package finan.heng.com.apps.testhttp;

import rx.Observable;

/**
 * Created by YDYWork on 2018/7/17.
 */

public interface IRequest  {
    Observable<String> get(String url);
    Observable<String>  post(String url);
}

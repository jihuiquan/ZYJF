package finan.heng.com.apps.manager;

import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.SmsManager;

import com.dynamic.foundations.core.injection.DependencyInjectingObjectFactory;
import com.dynamic.foundations.network.HttpManager;
import com.dynamic.foundations.sqlite.SqliteTemplate;

import finan.heng.com.apps.manager.impl.ProductManagerImpl;


public class ManagerFactory {
    private static final ManagerFactory INSTANCE = new ManagerFactory();

    private DependencyInjectingObjectFactory factory;

    public static ManagerFactory getInstance() {
        return INSTANCE;
    }

    public void initialize(Context context) {
        factory = new DependencyInjectingObjectFactory();
        factory.registerImplementationObject(Context.class, context);
        factory.registerImplementationObject(ContentResolver.class, context.getContentResolver());
        factory.registerImplementationObject(SmsManager.class, SmsManager.getDefault());
        factory.registerImplementationObject(NotificationManager.class, (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE));
        factory.registerImplementationObject(SharedPreferences.class, context.getSharedPreferences("key.xml", Context.MODE_PRIVATE));
        factory.registerImplementationObject(HttpManager.class, HttpManager.getInstance(context));
        factory.registerSingletonImplementationClass(ProductManager.class, ProductManagerImpl.class);

    }


    private <T> T getObject(Class<T> type) {
        return factory.getObject(type);
    }

    public Context getContext() {
        return getObject(Context.class);
    }


    public SqliteTemplate getSqliteTemplate() {
        return factory.getObject(SqliteTemplate.class, false);
    }

    public NotificationManager getAndroidNotificationManager() {
        return getObject(NotificationManager.class);
    }

    public SharedPreferences getSharedPreferences() {
        return getObject(SharedPreferences.class);
    }

    public HttpManager getHttpManager() {
        return getObject(HttpManager.class);
    }

    public ProductManager getProductManager(){
        return getObject(ProductManager.class);
    }
}

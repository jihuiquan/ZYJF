package com.dynamic.foundations;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.dynamic.foundations.core.injection.DependencyInjectingObjectFactory;

/**
 * Created by liuyang on 15/9/17.
 */
public class FoundationManageFactory {
    private static final FoundationManageFactory INSTANCE = new FoundationManageFactory();
    private DependencyInjectingObjectFactory factory;
    private String packageName;

    public FoundationManageFactory() {
    }

    public static FoundationManageFactory getInstance() {
        return INSTANCE;
    }

    public void initialize(Context context) {
        this.packageName = context.getApplicationInfo().packageName;
        this.factory = new DependencyInjectingObjectFactory();
        this.factory.registerImplementationObject(Context.class, context);

    }

    private <T> T getObject(Class<T> type) {
        return this.factory.getObject(type);
    }

    public String getPackageName() {
        return this.packageName;
    }

    public Context getContext() {
        return this.getObject(Context.class);
    }


}

package com.riseinsteps.daggerwithretrofit;

import android.app.Application;

import com.riseinsteps.daggerwithretrofit.component.AppComponent;
import com.riseinsteps.daggerwithretrofit.component.DaggerAppComponent;
import com.riseinsteps.daggerwithretrofit.module.AppModule;
import com.riseinsteps.daggerwithretrofit.module.NetworkModule;

public class BaseApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getNetworkComponent() {
        return appComponent;
    }
}

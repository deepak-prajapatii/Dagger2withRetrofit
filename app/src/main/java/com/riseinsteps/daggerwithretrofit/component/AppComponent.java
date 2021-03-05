package com.riseinsteps.daggerwithretrofit.component;

import com.riseinsteps.daggerwithretrofit.MainActivity;
import com.riseinsteps.daggerwithretrofit.module.AppModule;
import com.riseinsteps.daggerwithretrofit.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
}

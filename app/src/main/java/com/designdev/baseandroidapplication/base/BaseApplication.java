package com.designdev.baseandroidapplication.base;

import android.app.Application;
import android.util.Log;

import com.designdev.baseandroidapplication.dagger.component.ApplicationComponent;
import com.designdev.baseandroidapplication.dagger.component.DaggerApplicationComponent;
import com.designdev.baseandroidapplication.dagger.module.ApplicationModule;
import com.designdev.baseandroidapplication.data.local.DatabaseService;
import com.designdev.baseandroidapplication.data.remote.NetworkService;

import javax.inject.Inject;

public class BaseApplication extends Application {

    private String TAG = getClass().getSimpleName();

    public ApplicationComponent mApplicationComponent;

    @Inject
    NetworkService mNetworkService;

    @Inject
    DatabaseService mDatabaseService;

    @Override
    public void onCreate(){
        super.onCreate();
        getDependencies();
    }

    private void getDependencies(){
        mApplicationComponent = DaggerApplicationComponent.builder()
                                                            .applicationModule(new ApplicationModule(this))
                                                            .build();
        mApplicationComponent.inject(this);
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
    }

    @Override
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callbacks){

    }

}
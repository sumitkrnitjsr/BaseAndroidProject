package com.designdev.baseandroidapplication.dagger.module;


import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.designdev.baseandroidapplication.base.BaseApplication;
import com.designdev.baseandroidapplication.dagger.qualifiers.ApplicationContext;
import com.designdev.baseandroidapplication.dagger.qualifiers.DatabaseInfo;
import com.designdev.baseandroidapplication.dagger.qualifiers.NetworkInfo;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@Module
public class ApplicationModule {

    private BaseApplication mApplication;

    public ApplicationModule(BaseApplication application) {
            mApplication = application;
    }

    @ApplicationContext
    @Provides
    Context provideContext(){
            return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName(){
            return "app-db";
    }

    @Provides
    @NetworkInfo
    String provideApiKey(){
         return "api";
    }

    @Provides
    CompositeDisposable provideCompositeDisposable(){
            return new CompositeDisposable();
    }

    @Provides
    Toast provideToast(){
        return Toast.makeText(mApplication,"",Toast.LENGTH_SHORT);
    }
}

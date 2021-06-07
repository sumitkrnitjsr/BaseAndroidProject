package com.designdev.baseandroidapplication.dagger.component;


import android.content.Context;
import android.widget.Toast;

import com.designdev.baseandroidapplication.base.BaseApplication;

import com.designdev.baseandroidapplication.dagger.module.ApplicationModule;
import com.designdev.baseandroidapplication.dagger.qualifiers.ApplicationContext;
import com.designdev.baseandroidapplication.dagger.scope.ApplicationScope;
import com.designdev.baseandroidapplication.data.local.DatabaseService;
import com.designdev.baseandroidapplication.data.remote.NetworkService;
import com.designdev.baseandroidapplication.utils.NetworkHelper;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(BaseApplication application);

    @ApplicationContext
    Context getContext();

    NetworkService getNetworkService();

    DatabaseService getDatabaseService();

    CompositeDisposable getCompositeDisposable();

    NetworkHelper getNetworkHelper();

    Toast getToast();

}

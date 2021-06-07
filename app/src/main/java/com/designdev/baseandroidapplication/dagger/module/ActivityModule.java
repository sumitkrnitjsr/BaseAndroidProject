package com.designdev.baseandroidapplication.dagger.module;


import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.designdev.baseandroidapplication.MainViewModel;
import com.designdev.baseandroidapplication.base.BaseApplication;
import com.designdev.baseandroidapplication.base.recyclerview.PostAdapter;
import com.designdev.baseandroidapplication.base.ui.BaseActivity;
import com.designdev.baseandroidapplication.dagger.qualifiers.ActivityContext;
import com.designdev.baseandroidapplication.data.local.DatabaseService;
import com.designdev.baseandroidapplication.data.remote.NetworkService;
import com.designdev.baseandroidapplication.utils.NetworkHelper;
import com.designdev.baseandroidapplication.utils.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private BaseActivity mActivity;

    public ActivityModule(BaseActivity activity) {
            mActivity = activity;
    }

    @ActivityContext
    @Provides
    Context provideContext() {
        return mActivity;
    }

    @Provides
    MainViewModel provideMainViewModel(CompositeDisposable compositeDisposable,
                                       NetworkHelper networkHelper, DatabaseService databaseService,
                                       NetworkService networkService){

        return new ViewModelProvider(mActivity, new ViewModelProviderFactory<MainViewModel>(MainViewModel.class, new Callable() {
            @Override
            public MainViewModel call() throws Exception {

                MainViewModel mv =  new MainViewModel(databaseService, networkService, compositeDisposable,
                        networkHelper);
                return mv;
            }
        })).get(MainViewModel.class);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(mActivity);
    }

    @Provides
    PostAdapter providePostAdapter(){
        return new PostAdapter(mActivity.getLifecycle(), new ArrayList<>());
    }


}

package com.designdev.baseandroidapplication.dagger.module;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import com.designdev.baseandroidapplication.MainViewModel;
import com.designdev.baseandroidapplication.base.ui.BaseActivity;
import com.designdev.baseandroidapplication.base.ui.BaseFragment;
import com.designdev.baseandroidapplication.dagger.qualifiers.FragmentContext;
import com.designdev.baseandroidapplication.data.local.DatabaseService;
import com.designdev.baseandroidapplication.data.remote.NetworkService;
import com.designdev.baseandroidapplication.utils.NetworkHelper;
import com.designdev.baseandroidapplication.utils.ViewModelProviderFactory;

import java.util.concurrent.Callable;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@Module
public class FragmentModule {

    private BaseFragment mFragment;

    @FragmentContext
    public FragmentModule(BaseFragment fragment) {
        mFragment = fragment;
    }

    @Provides
    Context provideContext() {
        return mFragment.getContext();
    }

    @Provides
    MainViewModel provideMainViewModel(CompositeDisposable compositeDisposable,
                                       NetworkHelper networkHelper, DatabaseService databaseService,
                                       NetworkService networkService){
        return new ViewModelProvider(mFragment, new ViewModelProviderFactory<MainViewModel>(MainViewModel.class, new Callable() {
            @Override
            public MainViewModel call() throws Exception {
                return new MainViewModel(databaseService, networkService, compositeDisposable,
                        networkHelper);
            }
        })).get(MainViewModel.class);
    }
}

package com.designdev.baseandroidapplication.base.recyclerview;

import com.designdev.baseandroidapplication.base.recyclerview.base.BaseItemViewModel;
import com.designdev.baseandroidapplication.data.local.DatabaseService;
import com.designdev.baseandroidapplication.data.remote.NetworkService;
import com.designdev.baseandroidapplication.utils.NetworkHelper;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class PostViewModel extends BaseItemViewModel<PostModel> {

    DatabaseService mDatabaseService;

    NetworkService mNetworkService;

    @Inject
    public PostViewModel(CompositeDisposable compositeDisposable, NetworkHelper networkHelper,
                         DatabaseService databaseService, NetworkService networkService){
        super(compositeDisposable, networkHelper);
        mDatabaseService = databaseService;
        mNetworkService = networkService;
    }

    @Override
    protected void onCreate(){

    }

    @Override
    protected void handleNetworkError(Throwable err) {

    }
}

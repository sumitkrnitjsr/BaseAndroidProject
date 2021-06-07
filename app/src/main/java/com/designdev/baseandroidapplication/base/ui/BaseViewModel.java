package com.designdev.baseandroidapplication.base.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.designdev.baseandroidapplication.utils.NetworkHelper;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {

    protected  CompositeDisposable mCompositeDisposable;
    protected NetworkHelper mNetworkHelper;
    public MutableLiveData<String> mMessageString = new MutableLiveData<>();
    public MutableLiveData<Integer> mMessageStringid = new MutableLiveData<>();

    public BaseViewModel(CompositeDisposable compositeDisposable, NetworkHelper networkHelper) {
            mCompositeDisposable = compositeDisposable;
            mNetworkHelper = networkHelper;
    }

    protected abstract void onCreate();

    protected abstract void handleNetworkError(Throwable err);

    protected Boolean isNetworkConnected() {
            return mNetworkHelper.isNetworkConnected();
    }

    @Override
    protected void onCleared(){
        mCompositeDisposable.dispose();
        super.onCleared();
    }
}

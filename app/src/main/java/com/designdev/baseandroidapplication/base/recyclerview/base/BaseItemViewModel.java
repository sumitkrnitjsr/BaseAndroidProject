package com.designdev.baseandroidapplication.base.recyclerview.base;

import androidx.lifecycle.MutableLiveData;

import com.designdev.baseandroidapplication.base.ui.BaseViewModel;
import com.designdev.baseandroidapplication.utils.NetworkHelper;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public abstract class BaseItemViewModel<T> extends BaseViewModel {

        CompositeDisposable mCompositeDisposable;
        NetworkHelper mNetworkHelper;
        public MutableLiveData<T> data = new MutableLiveData<>();

        public BaseItemViewModel(CompositeDisposable compositeDisposable, NetworkHelper networkHelper) {
                super(compositeDisposable, networkHelper);
                mCompositeDisposable = compositeDisposable;
                mNetworkHelper = networkHelper;
        }

        void updateData(T data){
            this.data.postValue(data);
        }

        void onManualCleared(){
            onCleared();
        }


}

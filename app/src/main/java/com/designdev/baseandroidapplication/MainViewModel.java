package com.designdev.baseandroidapplication;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.designdev.baseandroidapplication.base.recyclerview.PostModel;
import com.designdev.baseandroidapplication.base.ui.BaseViewModel;
import com.designdev.baseandroidapplication.data.local.DatabaseService;
import com.designdev.baseandroidapplication.data.remote.NetworkService;
import com.designdev.baseandroidapplication.utils.NetworkHelper;

import java.util.ArrayList;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class MainViewModel extends BaseViewModel {

    private DatabaseService mDatabaseService;
    private NetworkService mNetworkService;
    MutableLiveData<String> testData = new MutableLiveData<String>();
    MutableLiveData<ArrayList<PostModel>> testData2 = new MutableLiveData<>();

    public MainViewModel(DatabaseService databaseService, NetworkService networkService, CompositeDisposable compositeDisposable, NetworkHelper networkHelper){
                super(compositeDisposable, networkHelper);
                mDatabaseService = databaseService;
                mNetworkService = networkService;
    }


    @Override
    protected void onCreate() {
            Log.d("here" , "{updated");
            testData.postValue("Hello from MainViewModel");
            if(!isNetworkConnected()) mMessageString.postValue(" No Internet Connection");
            ArrayList<PostModel> postList = new ArrayList<PostModel>();
             postList.add(new PostModel("First"));
             postList.add(new PostModel("Second"));
             postList.add(new PostModel("Third"));
             postList.add(new PostModel("Fourth"));

            testData2.postValue(postList);
    }

    @Override
    protected void handleNetworkError(Throwable err) {

    }
}

package com.designdev.baseandroidapplication.data.remote;


import android.content.Context;

import com.designdev.baseandroidapplication.dagger.qualifiers.ApplicationContext;
import com.designdev.baseandroidapplication.dagger.qualifiers.NetworkInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NetworkService {

    Context mContext;
    String mApiKey;

    @Inject
    public NetworkService(@ApplicationContext Context context, @NetworkInfo String apiKey){
        mContext = context;
        mApiKey = apiKey;
    }
}

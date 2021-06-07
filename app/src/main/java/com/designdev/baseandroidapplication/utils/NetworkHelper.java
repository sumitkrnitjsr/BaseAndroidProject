package com.designdev.baseandroidapplication.utils;

import android.content.Context;

import androidx.annotation.NonNull;

import com.designdev.baseandroidapplication.dagger.qualifiers.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NetworkHelper {

    Context mContext;

    @Inject
    public NetworkHelper(@ApplicationContext Context context){
            mContext = context;
    }

    public Boolean isNetworkConnected() {
            return false;
    }

}

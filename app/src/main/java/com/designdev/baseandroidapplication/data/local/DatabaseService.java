package com.designdev.baseandroidapplication.data.local;


import android.content.Context;

import com.designdev.baseandroidapplication.dagger.qualifiers.ApplicationContext;
import com.designdev.baseandroidapplication.dagger.qualifiers.DatabaseInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DatabaseService {

    Context mContext;
    String mDBName;

    @Inject
    public DatabaseService(@ApplicationContext Context context, @DatabaseInfo String dbName){
        mContext = context;
        mDBName = dbName;
    }
}

package com.designdev.baseandroidapplication.utils;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.concurrent.Callable;

import javax.inject.Singleton;

import kotlin.Suppress;
import kotlin.jvm.Throws;


@Singleton
public class ViewModelProviderFactory<T extends ViewModel> extends ViewModelProvider.NewInstanceFactory{


    private Class<T> mClass;
    private Callable mCreator;

    public ViewModelProviderFactory(Class<T> cls, Callable creator) {
            mClass = cls;
            mCreator = creator;
    }

    @Suppress(names = "UNCHECKED_CAST")
    @Throws(exceptionClasses = IllegalArgumentException.class)
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass){
            if(modelClass.isAssignableFrom(mClass)) {
                try {
                    return (T) (mCreator.call());
                } catch (Exception e) {
                    throw new IllegalArgumentException("Unknown class name");
                }

            }
            throw new IllegalArgumentException("Unknown class name");
    }

}

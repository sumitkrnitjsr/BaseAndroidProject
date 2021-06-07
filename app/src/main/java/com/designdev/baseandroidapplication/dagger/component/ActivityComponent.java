package com.designdev.baseandroidapplication.dagger.component;

import android.app.Activity;

import com.designdev.baseandroidapplication.MainActivity;
import com.designdev.baseandroidapplication.base.ui.BaseActivity;
import com.designdev.baseandroidapplication.dagger.module.ActivityModule;
import com.designdev.baseandroidapplication.dagger.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = {ApplicationComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity activity);
}

package com.designdev.baseandroidapplication.dagger.component;

import androidx.fragment.app.Fragment;

import com.designdev.baseandroidapplication.base.ui.BaseActivity;
import com.designdev.baseandroidapplication.base.ui.BaseFragment;
import com.designdev.baseandroidapplication.dagger.module.FragmentModule;
import com.designdev.baseandroidapplication.dagger.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = {ApplicationComponent.class}, modules = {FragmentModule.class})
public interface FragmentComponent {

    void inject(Fragment fragment);
}

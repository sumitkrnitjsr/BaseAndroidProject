package com.designdev.baseandroidapplication.base.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.designdev.baseandroidapplication.base.BaseApplication;
import com.designdev.baseandroidapplication.dagger.component.ApplicationComponent;
import com.designdev.baseandroidapplication.dagger.component.DaggerFragmentComponent;
import com.designdev.baseandroidapplication.dagger.component.FragmentComponent;
import com.designdev.baseandroidapplication.dagger.module.FragmentModule;

import javax.inject.Inject;

public abstract class BaseFragment<VM extends BaseViewModel> extends Fragment {

    @Inject
    public VM mViewModel;

    private String TAG = getClass().getSimpleName();

    @Inject
    public Toast mToast;

    @Override
    public void onCreate(Bundle savedInstance) {
        injectDependencies(buildFragmentComponent());
        super.onCreate(savedInstance);
        setUpObservers();
        mViewModel.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstance) {

        return layoutInflater.inflate(provideLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance) {
            super.onViewCreated(view, savedInstance);
            setUpview(view);
    }


    private FragmentComponent buildFragmentComponent() {
        BaseApplication baseApplication = (BaseApplication) getActivity().getApplication();
        ApplicationComponent applicationComponent = baseApplication.mApplicationComponent;
        return DaggerFragmentComponent.builder()
                .applicationComponent(applicationComponent)
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    protected abstract void injectDependencies(FragmentComponent fragmentComponent);
    protected abstract void setUpview(View view);

    protected void setUpObservers() {
        mViewModel.mMessageString.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });

        mViewModel.mMessageStringid.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer id) {

            }
        });
    }

    @LayoutRes
    protected abstract Integer provideLayoutRes();

    protected void showMessage(String message) {
        mToast = Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    protected void showMessage(@StringRes Integer resId) {
        showMessage(getString(resId));
    }


}

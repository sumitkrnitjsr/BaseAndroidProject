package com.designdev.baseandroidapplication.base.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.designdev.baseandroidapplication.base.BaseApplication;
import com.designdev.baseandroidapplication.dagger.component.ActivityComponent;
import com.designdev.baseandroidapplication.dagger.component.ApplicationComponent;
import com.designdev.baseandroidapplication.dagger.component.DaggerActivityComponent;
import com.designdev.baseandroidapplication.dagger.module.ActivityModule;

import javax.inject.Inject;

public abstract class BaseActivity<VM extends BaseViewModel> extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();

    @Inject
    public  Toast mToast;

    @Inject
    public VM mViewModel;

    @Override
    protected void onCreate(Bundle savedInstance) {
        injectDependencies(buildActivityComponent());
        super.onCreate(savedInstance);
        setContentView(provideLayoutRes());
        setUpObservers();
        setUpview(savedInstance);
        mViewModel.onCreate();
    }

    private ActivityComponent buildActivityComponent() {
                BaseApplication baseApplication = (BaseApplication) getApplication();
                ApplicationComponent applicationComponent = baseApplication.mApplicationComponent;
               return DaggerActivityComponent.builder()
                                    .applicationComponent(applicationComponent)
                                    .activityModule(new ActivityModule(this))
                                    .build();
    }

    protected abstract void injectDependencies(ActivityComponent activityComponent);
    protected abstract void setUpview(Bundle savedInstance);
    @LayoutRes
    protected abstract Integer provideLayoutRes();


    protected void setUpObservers() {
        mViewModel.mMessageString.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                    showMessage(s);
            }
        });

        mViewModel.mMessageStringid.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer id) {
                showMessage(id);
            }
        });
    }

    protected void showMessage(String message) {
            mToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
            mToast.show();
    }

    protected void showMessage(@StringRes Integer resId) {
            showMessage(getString(resId));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    final void goBack(){
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStackImmediate();
        else super.onBackPressed();
    }
}

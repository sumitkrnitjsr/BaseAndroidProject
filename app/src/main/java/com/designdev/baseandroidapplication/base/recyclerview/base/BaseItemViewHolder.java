package com.designdev.baseandroidapplication.base.recyclerview.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.designdev.baseandroidapplication.base.BaseApplication;
import com.designdev.baseandroidapplication.dagger.component.ApplicationComponent;

import javax.inject.Inject;

public abstract class BaseItemViewHolder<T, VM extends BaseItemViewModel<T>> extends RecyclerView.ViewHolder
                                                implements LifecycleOwner {

    @Inject
    protected VM mViewModel;

    @Inject
    LifecycleRegistry mLifecycleRegistry;

    @Inject
    Toast mToast;

    public BaseItemViewHolder(@LayoutRes Integer layoutId, ViewGroup parent){
        super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
        onCreate();
    }

    @Override
    public Lifecycle getLifecycle(){
        return mLifecycleRegistry;
    }

    protected void onCreate(){
        injectDependencies(buildViewHolderComponent());
        mLifecycleRegistry.setCurrentState(Lifecycle.State.INITIALIZED);
        mLifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
        setUpObservers();
        setUpview(itemView);
    }

    protected void onStart(){
        mLifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
        mLifecycleRegistry.setCurrentState(Lifecycle.State.RESUMED);
    }

    protected void onStop(){
        mLifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
        mLifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
    }

    protected void onDestroy(){
        mLifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED);
    }

    void bind(T data){
        mViewModel.updateData(data);
    }

    private ItemViewHolderComponent buildViewHolderComponent() {
        BaseApplication baseApplication = (BaseApplication) itemView.getContext().getApplicationContext();
        ApplicationComponent applicationComponent = baseApplication.mApplicationComponent;
        return DaggerItemViewHolderComponent.builder()
                .applicationComponent(applicationComponent)
                .viewHolderModule(new ViewHolderModule(this))
                .build();
    }

    protected abstract void injectDependencies(ItemViewHolderComponent viewHolderComponent);
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
        if(mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(itemView.getContext(), message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    protected void showMessage(@StringRes Integer resId) {
        showMessage(itemView.getContext().getString(resId));
    }


}

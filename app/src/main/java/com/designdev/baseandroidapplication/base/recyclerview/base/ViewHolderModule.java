package com.designdev.baseandroidapplication.base.recyclerview.base;

import androidx.lifecycle.LifecycleRegistry;

import com.designdev.baseandroidapplication.base.recyclerview.PostModel;
import com.designdev.baseandroidapplication.base.recyclerview.PostViewModel;
import com.designdev.baseandroidapplication.data.local.DatabaseService;
import com.designdev.baseandroidapplication.data.remote.NetworkService;
import com.designdev.baseandroidapplication.utils.NetworkHelper;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@Module
public class ViewHolderModule {

    private BaseItemViewHolder mItemViewHolder;

    public ViewHolderModule(BaseItemViewHolder baseItemViewHolder) {
            mItemViewHolder = baseItemViewHolder;
    }
    @ViewHolderScope
    @Provides
    LifecycleRegistry provideLifecycleRegistry(){
            return new LifecycleRegistry(mItemViewHolder);
    }

    @ViewHolderScope
    @Provides
    BaseItemViewModel<PostModel> providebaseItemViewModel(CompositeDisposable compositeDisposable, NetworkHelper networkHelper,
                                                          DatabaseService databaseService, NetworkService networkService) {
            return new PostViewModel(compositeDisposable,networkHelper,databaseService,networkService );
    }

}

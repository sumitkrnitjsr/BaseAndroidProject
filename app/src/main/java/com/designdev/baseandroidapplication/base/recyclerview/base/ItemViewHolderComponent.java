package com.designdev.baseandroidapplication.base.recyclerview.base;

import com.designdev.baseandroidapplication.base.recyclerview.PostViewHolder;
import com.designdev.baseandroidapplication.dagger.component.ApplicationComponent;

import dagger.Component;

@ViewHolderScope
@Component(dependencies = {ApplicationComponent.class}, modules = {ViewHolderModule.class})
public interface ItemViewHolderComponent {

    void inject(PostViewHolder viewHolder);
}

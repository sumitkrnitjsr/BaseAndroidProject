package com.designdev.baseandroidapplication.base.recyclerview;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;

import com.designdev.baseandroidapplication.base.recyclerview.base.BaseAdapter;

import java.util.ArrayList;

public class PostAdapter extends BaseAdapter<PostModel, PostViewHolder> {

    public PostAdapter(Lifecycle parentLifecycle, ArrayList<PostModel> postList){
            super(postList, parentLifecycle);
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(parent);
    }
}

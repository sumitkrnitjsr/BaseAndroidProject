package com.designdev.baseandroidapplication.base.recyclerview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.Observer;

import com.designdev.baseandroidapplication.R;
import com.designdev.baseandroidapplication.base.recyclerview.base.BaseItemViewHolder;
import com.designdev.baseandroidapplication.base.recyclerview.base.BaseItemViewModel;
import com.designdev.baseandroidapplication.base.recyclerview.base.ItemViewHolderComponent;

public class PostViewHolder extends BaseItemViewHolder<PostModel, BaseItemViewModel<PostModel>> {

    TextView mTextView;

    public PostViewHolder(ViewGroup parent){
            super(R.layout.item_view_post, parent);
    }

    @Override
    protected void injectDependencies(ItemViewHolderComponent viewHolderComponent) {
            viewHolderComponent.inject(this);
    }

    @Override
    protected void setUpview(View view) {
        mTextView = (TextView)itemView.findViewById(R.id.tv_message);
    }

    @Override
    protected void setUpObservers(){
        super.setUpObservers();
        mViewModel.data.observe(this, new Observer<PostModel>() {
            @Override
            public void onChanged(PostModel postModel) {
                mTextView.setText(postModel.text);
            }
        });

        itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                showMessage(getAdapterPosition() + " clicked ");
            }
        });
    }

    @Override
    protected Integer provideLayoutRes() {
        return null;
    }
}

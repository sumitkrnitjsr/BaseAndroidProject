package com.designdev.baseandroidapplication.base.recyclerview.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public abstract class BaseAdapter<T,VH extends BaseItemViewHolder<T, BaseItemViewModel<T>>>
                                        extends RecyclerView.Adapter<VH> {

    private ArrayList<T> mDataList;
    private Lifecycle mParentLifecycle;
    private RecyclerView mRecyclerView;

    public BaseAdapter(ArrayList<T> dataList, Lifecycle parentLifecycle){
        mDataList = dataList;
        mParentLifecycle = parentLifecycle;
        mParentLifecycle.addObserver(new LifecycleObserver() {

                                         @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                                         void onParentDestroy() {
                                             if (mRecyclerView != null) {
                                                 for (int i = 0; i < mRecyclerView.getChildCount(); i++) {
                                                     if (mRecyclerView.getChildAt(i) != null) {
                                                         BaseItemViewHolder holder = (BaseItemViewHolder) mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(i));
                                                         holder.onDestroy();
                                                         holder.mViewModel.onManualCleared();
                                                     }
                                                 }
                                             }
                                         }


                                         @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
                                         void onParentStop() {
                                             if (mRecyclerView != null) {
                                                 for (int i = 0; i < mRecyclerView.getChildCount(); i++) {
                                                     if (mRecyclerView.getChildAt(i) != null) {
                                                         BaseItemViewHolder holder = (BaseItemViewHolder) mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(i));
                                                         holder.onStop();
                                                     }
                                                 }
                                             }
                                         }

                                        @OnLifecycleEvent(Lifecycle.Event.ON_START)
                                        void onParentStart() {
                                             if (mRecyclerView != null && mRecyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                                                    LinearLayoutManager layoutManager =  (LinearLayoutManager)mRecyclerView.getLayoutManager();
                                                    Integer firstPos = layoutManager.findFirstVisibleItemPosition();
                                                    Integer lastPos = layoutManager.findLastVisibleItemPosition();

                                                    if(firstPos >= 0 && firstPos <= lastPos) {
                                                        for(int i = firstPos;i <= lastPos;i++) {
                                                            RecyclerView.ViewHolder viewHolder = mRecyclerView.findViewHolderForAdapterPosition(i);
                                                            if(viewHolder != null) {
                                                                ((BaseItemViewHolder)viewHolder).onStart();
                                                            }
                                                        }
                                                    }
                                             }
                                        }

        }
        );

    }

    @Override
    public void onViewAttachedToWindow(@NonNull VH holder) {
        super.onViewAttachedToWindow(holder);
        holder.onStart();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull VH holder) {
        super.onViewDetachedFromWindow(holder);
        holder.onStop();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecyclerView = null;

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
                holder.bind(mDataList.get(position));
    }

    public void appendData(ArrayList<T> data) {
        int itemCount = getItemCount();
        this.mDataList.addAll(data);
        int newCount = getItemCount();
        if(itemCount == 0 && newCount > 0) {
                notifyDataSetChanged();
        }else if(itemCount > 0 && newCount  > itemCount){
                notifyItemRangeChanged(itemCount - 1, newCount - itemCount);
        }
    }


}

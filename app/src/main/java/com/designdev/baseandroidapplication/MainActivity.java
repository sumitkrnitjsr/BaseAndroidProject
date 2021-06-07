package com.designdev.baseandroidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.designdev.baseandroidapplication.base.recyclerview.PostAdapter;
import com.designdev.baseandroidapplication.base.recyclerview.PostModel;
import com.designdev.baseandroidapplication.base.ui.BaseActivity;
import com.designdev.baseandroidapplication.dagger.component.ActivityComponent;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainViewModel> {


    TextView txtvw;

    @Inject
    LinearLayoutManager mLinearlayoutManager;

    @Inject
    PostAdapter mPostAdapter;

    RecyclerView mRecyclerView;

    @Override
    protected void injectDependencies(ActivityComponent activityComponent) {
            activityComponent.inject(this);
    }

    @Override
    protected void setUpview(Bundle savedInstance) {
        txtvw = (TextView)(findViewById(R.id.txtvw));
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(mLinearlayoutManager);
        mRecyclerView.setAdapter(mPostAdapter);
    }

    @Override
    protected void setUpObservers(){
        super.setUpObservers();
        mViewModel.testData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtvw.setText(s);
            }
        });

        mViewModel.testData2.observe(this, new Observer<ArrayList<PostModel>>() {
            @Override
            public void onChanged(ArrayList<PostModel> s) {
                mPostAdapter.appendData(s);
            }
        });

    }

    @Override
    protected Integer provideLayoutRes() {
        return R.layout.activity_main;
    }
}
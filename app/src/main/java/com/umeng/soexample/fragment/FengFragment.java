package com.umeng.soexample.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.soexample.R;
import com.umeng.soexample.adapter.LeftAdapter;
import com.umeng.soexample.adapter.RightAdapter;
import com.umeng.soexample.bean.Main;
import com.umeng.soexample.bean.MainLeft;
import com.umeng.soexample.bean.MeaseBean;
import com.umeng.soexample.feng.presenter.FengPresenter;
import com.umeng.soexample.feng.view.FengView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shinelon on 2018/10/25.
 */

public class FengFragment extends Fragment implements FengView{
    private RecyclerView mainLeft,mainRight;
    private List<MainLeft> leftList;
    private List<Main> rightList;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private FengPresenter presenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.feng_item, container, false);
        mainLeft=v.findViewById(R.id.main_left);
        mainRight=v.findViewById(R.id.main_right);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        presenter=new FengPresenter();
        presenter.ackath(this);
        presenter.getLeft();
        presenter.getRight(1);
    }

    private void initData() {
        leftList=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager1=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        mainLeft.setLayoutManager(layoutManager1);
        leftAdapter=new LeftAdapter(getActivity(),leftList);
        mainLeft.setAdapter(leftAdapter);

        rightList=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager2=new GridLayoutManager(getActivity(),3);
        mainRight.setLayoutManager(layoutManager2);
        rightAdapter=new RightAdapter(getActivity(),rightList);
        mainRight.setAdapter(rightAdapter);

        leftAdapter.setonClickListener(new LeftAdapter.onClickListener() {
            @Override
            public void onClick(int position) {
                presenter.getRight(position);
            }
        });
    }

    @Override
    public void getMainLeft(MeaseBean<List<MainLeft>> dataLeft) {
        if (dataLeft!=null){
            leftList.clear();
            leftList.addAll(dataLeft.getData());
            leftAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getMainRight(MeaseBean<List<Main>> dataRight) {
         if (dataRight!=null){
             List<Main> data = dataRight.getData();
             if (data!=null){
                 rightList.clear();
                 rightList.addAll(data);
                 rightAdapter.notifyDataSetChanged();
             }
         }
    }

    @Override
    public void failed(Exception e) {

    }
}

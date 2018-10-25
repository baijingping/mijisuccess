package com.umeng.soexample.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.umeng.soexample.R;
import com.umeng.soexample.adapter.BannerAdapter;
import com.umeng.soexample.bean.Bannerbean;
import com.umeng.soexample.bean.MeaseBean;
import com.umeng.soexample.home.presenter.HomePresenter;
import com.umeng.soexample.home.view.HomeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shinelon on 2018/10/25.
 */

public class HomeFragment extends Fragment implements HomeView{
    private EditText txtLiu;
    private ViewPager banner;
    private HomePresenter presenter;
    private List<Bannerbean> bannerList;
    private BannerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_item, container, false);
        txtLiu=v.findViewById(R.id.txt_liu);
        banner=v.findViewById(R.id.banner);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter=new HomePresenter();
        presenter.attach(this);
        bannerList=new ArrayList<>();
        adapter=new BannerAdapter(getActivity(),bannerList);
        banner.setAdapter(adapter);
        presenter.getBanner();
        handler.sendEmptyMessageDelayed(1,2000);
    }

    @Override
    public void Success(MeaseBean<List<Bannerbean>> list) {
        if (list!=null){
            List<Bannerbean> data = list.getData();
            if (data!=null){
                bannerList.clear();
                bannerList.addAll(data);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void Failed(Exception e) {

    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                int item = banner.getCurrentItem();
                if (item<bannerList.size()-1){
                    item++;
                }else {
                    item=0;
                }
                banner.setCurrentItem(item);
                handler.sendEmptyMessageDelayed(1,2000);
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
    }
}

package com.umeng.soexample.home.presenter;

import com.google.gson.reflect.TypeToken;
import com.umeng.soexample.INetCallBack;
import com.umeng.soexample.bean.Bannerbean;
import com.umeng.soexample.bean.MeaseBean;
import com.umeng.soexample.home.model.HomeModel;
import com.umeng.soexample.home.view.HomeView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Shinelon on 2018/10/25.
 */

public class HomePresenter {
    private HomeView iv;
    private HomeModel model;
    public void attach(HomeView iv){
        this.iv=iv;
        model=new HomeModel();
    }

    public void getBanner(){
        Type type=new TypeToken<MeaseBean<List<Bannerbean>>>(){}.getType();
        model.getData("http://www.zhaoapi.cn/ad/getAd", new INetCallBack() {
            @Override
            public void success(Object obj) {
                MeaseBean<List<Bannerbean>> data= (MeaseBean<List<Bannerbean>>) obj;
                iv.Success(data);
            }

            @Override
            public void failed(Exception e) {
                iv.Failed(e);
            }
        },type);
    }

    public void detach(){
        if (iv!=null){
            iv=null;
        }
    }
}

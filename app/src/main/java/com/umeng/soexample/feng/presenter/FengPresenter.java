package com.umeng.soexample.feng.presenter;

import com.google.gson.reflect.TypeToken;
import com.umeng.soexample.INetCallBack;
import com.umeng.soexample.bean.Main;
import com.umeng.soexample.bean.MainLeft;
import com.umeng.soexample.bean.MeaseBean;
import com.umeng.soexample.feng.model.FengModel;
import com.umeng.soexample.feng.view.FengView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Shinelon on 2018/10/25.
 */

public class FengPresenter {
    private FengView iv;
    private FengModel model;
    public void ackath(FengView iv){
        this.iv=iv;
        model=new FengModel();
    }

    public void getLeft(){
        Type type=new TypeToken<MeaseBean<List<MainLeft>>>(){}.getType();
        model.getData("http://www.zhaoapi.cn/product/getCatagory", new INetCallBack() {
            @Override
            public void success(Object obj) {
                MeaseBean<List<MainLeft>> data= (MeaseBean<List<MainLeft>>) obj;
                iv.getMainLeft(data);
            }

            @Override
            public void failed(Exception e) {
               iv.failed(e);
            }
        },type);
    }

    public void getRight(int position){
        Type type=new TypeToken<MeaseBean<List<Main>>>(){}.getType();
        String url="http://www.zhaoapi.cn/product/getProductCatagory?cid=";
        model.getData(url + position, new INetCallBack() {
            @Override
            public void success(Object obj) {
                MeaseBean<List<Main>> data= (MeaseBean<List<Main>>) obj;
                iv.getMainRight(data);
            }

            @Override
            public void failed(Exception e) {
               iv.failed(e);
            }
        },type);
    }

    public void detach(){
        if (iv!=null){
            iv=null;
        }
    }
}

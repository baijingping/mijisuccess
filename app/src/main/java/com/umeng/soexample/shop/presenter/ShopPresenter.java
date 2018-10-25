package com.umeng.soexample.shop.presenter;

import com.google.gson.reflect.TypeToken;
import com.umeng.soexample.INetCallBack;
import com.umeng.soexample.bean.MeaseBean;
import com.umeng.soexample.bean.Product;
import com.umeng.soexample.bean.Shops;
import com.umeng.soexample.shop.model.ShopModel;
import com.umeng.soexample.shop.view.ShopView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Shinelon on 2018/10/25.
 */

public class ShopPresenter {
    private ShopView iv;
    private ShopModel model;
    public void attach(ShopView iv){
        this.iv=iv;
        model=new ShopModel();
    }

    public void getData(){
        Type type=new TypeToken<MeaseBean<List<Shops<List<Product>>>>>(){}.getType();
        model.getData("http://www.zhaoapi.cn/product/getCarts?uid=1538", new INetCallBack() {
            @Override
            public void success(Object obj) {
                MeaseBean<List<Shops<List<Product>>>> data= (MeaseBean<List<Shops<List<Product>>>>) obj;
                iv.success(data);
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

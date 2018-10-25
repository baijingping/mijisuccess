package com.umeng.soexample.shop.view;

import com.umeng.soexample.bean.MeaseBean;
import com.umeng.soexample.bean.Product;
import com.umeng.soexample.bean.Shops;

import java.util.List;

/**
 * Created by Shinelon on 2018/10/25.
 */

public interface ShopView {
    void success(MeaseBean<List<Shops<List<Product>>>> data);
    void failed(Exception e);
}

package com.umeng.soexample.home.model;

import com.umeng.soexample.INetCallBack;
import com.umeng.soexample.utils.HttpUtils;

import java.lang.reflect.Type;

/**
 * Created by Shinelon on 2018/10/25.
 */

public class HomeModel {
    public void getData(String url, INetCallBack callBack, Type type){
        HttpUtils.getInstance().get(url,callBack,type);
    }
}

package com.umeng.soexample.home.view;

import com.umeng.soexample.bean.Bannerbean;
import com.umeng.soexample.bean.MeaseBean;

import java.util.List;

/**
 * Created by Shinelon on 2018/10/25.
 */

public interface HomeView {
    void Success(MeaseBean<List<Bannerbean>> list);
    void Failed(Exception e);
}

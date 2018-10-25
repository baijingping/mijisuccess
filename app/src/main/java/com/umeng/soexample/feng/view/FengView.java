package com.umeng.soexample.feng.view;

import com.umeng.soexample.bean.Main;
import com.umeng.soexample.bean.MainLeft;
import com.umeng.soexample.bean.MeaseBean;

import java.util.List;

/**
 * Created by Shinelon on 2018/10/25.
 */

public interface FengView {
   void getMainLeft(MeaseBean<List<MainLeft>> dataLeft);

   void getMainRight(MeaseBean<List<Main>> dataRight);

   void failed(Exception e);

}

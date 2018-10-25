package com.umeng.soexample;

/**
 * Created by eric on 2018/10/22.
 */

public interface INetCallBack {
    void success(Object obj);

    void failed(Exception e);
}

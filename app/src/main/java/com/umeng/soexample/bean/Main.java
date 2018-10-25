package com.umeng.soexample.bean;

import java.util.List;

/**
 * Created by Shinelon on 2018/10/25.
 */

public class Main {
    private String cid;
    private String name;
    private String pcid;
    private List<MainRight> list;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPcid() {
        return pcid;
    }

    public void setPcid(String pcid) {
        this.pcid = pcid;
    }

    public List<MainRight> getList() {
        return list;
    }

    public void setList(List<MainRight> list) {
        this.list = list;
    }
}

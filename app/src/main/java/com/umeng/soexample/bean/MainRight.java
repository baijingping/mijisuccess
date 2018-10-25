package com.umeng.soexample.bean;

import com.google.gson.Gson;

/**
 * Created by Shinelon on 2018/10/25.
 */

public class MainRight {
    /**
     * icon : http://120.27.23.105/images/icon.png
     * name : 月饼
     * pcid : 1
     * pscid : 1
     */

    private String icon;
    private String name;
    private int pcid;
    private int pscid;

    public static MainRight objectFromData(String str) {

        return new Gson().fromJson(str, MainRight.class);
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPcid() {
        return pcid;
    }

    public void setPcid(int pcid) {
        this.pcid = pcid;
    }

    public int getPscid() {
        return pscid;
    }

    public void setPscid(int pscid) {
        this.pscid = pscid;
    }

    /**
     * bargainPrice : 22.9
     * createtime : 2017-10-14T21:48:08
     * detailUrl : https://item.m.jd.com/product/2542855.html?utm_source=androidapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=QQfriends
     * images : https://m.360buyimg.com/n0/jfs/t1930/284/2865629620/390243/e3ade9c4/56f0a08fNbd3a1235.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t2137/336/2802996626/155915/e5e90d7a/56f0a09cN33e01bd0.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t1882/31/2772215910/389956/c8dbf370/56f0a0a2Na0c86ea6.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t2620/166/2703833710/312660/531aa913/57709035N33857877.jpg!q70.jpg
     * num : 1
     * pid : 24
     * price : 288
     * pscid : 2
     * selected : 0
     * sellerid : 1
     * subhead : 三只松鼠零食特惠，专区满99减50，满199减100，火速抢购》
     * title : 三只松鼠 坚果炒货 零食奶油味 碧根果225g/袋
     */


}

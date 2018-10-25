package com.umeng.soexample.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.adapter.ProductAdapter;
import com.umeng.soexample.adapter.ShopAdapter;
import com.umeng.soexample.bean.MeaseBean;
import com.umeng.soexample.bean.Product;
import com.umeng.soexample.bean.Shops;
import com.umeng.soexample.shop.presenter.ShopPresenter;
import com.umeng.soexample.shop.view.ShopView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shinelon on 2018/10/25.
 */

public class GoushopFragment extends Fragment implements ShopView{
    private TextView txtEditFinish;
    private CheckBox cbTotal;
    private TextView txtPrice;
    private Button btnCalu;
    private RecyclerView rvShoper;
    private List<Shops<List<Product>>> shopsList;
    private ShopPresenter presenter;
    private ShopAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.goushop_item, container, false);
        txtEditFinish = v.findViewById(R.id.txt_edit_or_finish);
        cbTotal = v.findViewById(R.id.cb_total_select);
        txtPrice = v.findViewById(R.id.txt_total_price);
        btnCalu = v.findViewById(R.id.btn_calu);
        rvShoper = v.findViewById(R.id.rv_shopper);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        shopsList=new ArrayList<>();
        presenter=new ShopPresenter();
        presenter.attach(this);
        presenter.getData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvShoper.setLayoutManager(layoutManager);
        adapter=new ShopAdapter(getActivity(),shopsList);
        adapter.setOnShopperClickListener(new ShopAdapter.OnShopperClickListener() {
            @Override
            public void onShopperClick(int position, boolean isCheck) {
                // 为了效率考虑，当点击状态变成未选中时，全选按钮肯定就不是全选了，就不用再循环一次
                if (!isCheck) {
                    cbTotal.setChecked(false);
                } else {
                    // 如果是商家变成选中状态时，需要循环遍历所有的商家是否被选中
                    // 循环遍历之前先设置一个true标志位，只要有一个是未选中就改变这个标志位为false
                    boolean isAllShopperChecked = true;
                    for (Shops<List<Product>> listShopper : shopsList) {
                        // 只要有一个商家没有被选中，全选复选框就变成未选中状态，并且结束循环
                        if (!listShopper.isChecked()) {
                            isAllShopperChecked = false;
                            break;
                        }
                    }
                    cbTotal.setChecked(isAllShopperChecked);
                }

                // 一级条目发生变化时，计算一下总价
                calculatePrice();
            }
        });

        adapter.setOnAddDecreaseProductListener(new ProductAdapter.OnAddDecreaseProductListener() {
            @Override
            public void onChange(int position, int num) {
                calculatePrice();
            }
        });
        rvShoper.setAdapter(adapter);

        cbTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = cbTotal.isChecked();
                // 遍历一级列表，和下方的全选状态一致
                for (Shops<List<Product>> listShopper : shopsList) {
                    listShopper.setChecked(isChecked);
                    // 遍历二级列表，和下方的全选状态一致
                    List<Product> products = listShopper.getList();
                    for (Product product : products) {
                        product.setChecked(isChecked);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void success(MeaseBean<List<Shops<List<Product>>>> data) {
          if (data!=null){
              List<Shops<List<Product>>> s = data.getData();
              if (s!=null){
                  shopsList.clear();
                  shopsList.addAll(s);
                  adapter.notifyDataSetChanged();
              }
          }
    }

    @Override
    public void failed(Exception e) {

    }

    // 计算商品总价
    private void calculatePrice() {
        // 遍历商家
        float totalPrice = 0;
        for (Shops<List<Product>> listShopper : shopsList) {
            // 遍历商家的商品
            List<Product> list = listShopper.getList();
            for (Product product : list) {
                // 如果商品被选中
                if (product.isChecked()) {
                    totalPrice += product.getNum() * product.getPrice();
                }
            }
        }

        txtPrice.setText("总价：" + totalPrice);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
    }
}

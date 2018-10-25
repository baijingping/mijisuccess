package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.bean.MeaseBean;
import com.umeng.soexample.bean.Product;
import com.umeng.soexample.bean.Shops;

import java.util.List;

/**
 * Created by Shinelon on 2018/10/25.
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private Context context;
    private List<Shops<List<Product>>> list;

    public ShopAdapter(Context context, List<Shops<List<Product>>> list) {
        this.context = context;
        this.list = list;
    }

    // 一级列表（商家）发生变化的接口
    public interface OnShopperClickListener {
        void onShopperClick(int position, boolean isCheck);
    }

    private OnShopperClickListener shopperClickListener;

    public void setOnShopperClickListener(OnShopperClickListener listener) {
        this.shopperClickListener = listener;
    }


    // 二级列表的加减器监听
    private ProductAdapter.OnAddDecreaseProductListener productListener;

    public void setOnAddDecreaseProductListener(ProductAdapter.OnAddDecreaseProductListener listener) {
        this.productListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_shop, null);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Shops<List<Product>> shops = list.get(position);
        holder.txtShopperName.setText(shops.getSellerName());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
        holder.rvProduct.setLayoutManager(layoutManager);
        final ProductAdapter adapter=new ProductAdapter(context,shops.getList());
        if (productListener != null) {
            adapter.setOnAddDecreaseProductListener(productListener);
        }
        // 二级条目（商品）复选框点击事件
        adapter.setOnProductClickListener(new ProductAdapter.OnProductClickListener() {
            @Override
            public void onProductClick(int position, boolean isChecked) {
                // 当前商品未选中，商家也就未选中
                if (!isChecked) {
                    shops.setChecked(false);
                    // 只要是当前条目未选中，全选复选框也就没选中
                    shopperClickListener.onShopperClick(position, false);
                } else {
                    // 当前商品如果选中，需要遍历商家所有的商品是否选中
                    // 循环遍历之前先设置一个true标志位，只要有一条商品没有被选中，商家也就选中，标志位变成false
                    boolean isAllProductSelected = true;
                    for (Product product : shops.getList()) {
                        if (!product.isChecked()) {
                            isAllProductSelected = false;
                            break;
                        }
                    }
                    shops.setChecked(isAllProductSelected);
                    // 当前商品选中时，需要循环遍历所有的商家是否被选中来确认外部全选复选框的状态
                    shopperClickListener.onShopperClick(position, true);
                }
                // 数据发生变化之后刷新适配器
                notifyDataSetChanged();
                productListener.onChange(0, 0);
            }
        });
        holder.rvProduct.setAdapter(adapter);

        holder.cbSHopper.setOnCheckedChangeListener(null);

        // 设置好初始化的状态
        holder.cbSHopper.setChecked(shops.isChecked());

        // 等设置完初始化状态之后再设置我们自己的监听
        // 商家列表中的复选框
        holder.cbSHopper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                shops.setChecked(isChecked);

                // 1.商家被选中的时候，子类所有的商品应该被选中
//                if (isChecked) {
                List<Product> productList = shops.getList();
                for (Product product : productList) {
                    product.setChecked(isChecked);
                }
                // 子类商品的适配器刷新
                adapter.notifyDataSetChanged();
//                }
                // 当点击一级条目的时候，外部的全选按钮状态发生变化
                if (shopperClickListener != null) {
                    shopperClickListener.onShopperClick(position, isChecked);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cbSHopper;
        private TextView txtShopperName;
        private RecyclerView rvProduct;

        public ViewHolder(View itemView) {
            super(itemView);
            cbSHopper = itemView.findViewById(R.id.cb_shopper);
            txtShopperName = itemView.findViewById(R.id.txt_shopper_name);
            rvProduct = itemView.findViewById(R.id.rv_product);
        }
    }
}

package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.AddDecreaseView;
import com.umeng.soexample.R;
import com.umeng.soexample.bean.Product;
import com.umeng.soexample.utils.StringUtils;

import java.util.List;

/**
 * Created by Shinelon on 2018/10/25.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<Product> list;

    // 二级条目（商品）点击监听
    public interface OnProductClickListener {
        void onProductClick(int position, boolean isChecked);
    }

    private OnProductClickListener productClickListener;

    public void setOnProductClickListener(OnProductClickListener listener) {
        this.productClickListener = listener;
    }

    // 加减器发生变化的监听
    public interface OnAddDecreaseProductListener {
        void onChange(int position, int num);
    }

    private OnAddDecreaseProductListener productListener;

    public void setOnAddDecreaseProductListener(OnAddDecreaseProductListener listener) {
        this.productListener = listener;
    }

    public ProductAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_product, null);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Product product = list.get(position);
        String images = product.getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(StringUtils.https2Http(split[0])).into(holder.imgProduct);
        holder.txtProductName.setText(product.getTitle());
        holder.txtSinglePriice.setText(String.valueOf(product.getPrice()));

        holder.advProduct.setNum(product.getNum());
        // 加减器添加点击事件
        holder.advProduct.setOnAddDecreaseClickListener(new AddDecreaseView.OnAddDecreaseClickListener() {
            @Override
            public void add(int num) {
                product.setNum(num);

                if (productListener != null) {
                    productListener.onChange(position, num);
                }
            }

            @Override
            public void decrease(int num) {
                product.setNum(num);
                if (productListener != null) {
                    productListener.onChange(position, num);
                }
            }
        });


        // 商品的复选框
        holder.cbProduct.setOnCheckedChangeListener(null);
        holder.cbProduct.setChecked(product.isChecked());
        holder.cbProduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                product.setChecked(isChecked);
                if (productClickListener != null) {
                    productClickListener.onProductClick(position, isChecked);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox cbProduct;
        private ImageView imgProduct;
        private TextView txtProductName;
        private TextView txtSinglePriice;
        private AddDecreaseView advProduct;

        public ViewHolder(View itemView) {
            super(itemView);
            cbProduct = itemView.findViewById(R.id.cb_product);
            imgProduct = itemView.findViewById(R.id.img_product);
            txtSinglePriice = itemView.findViewById(R.id.txt_single_price);
            advProduct = itemView.findViewById(R.id.adv_product);
            txtProductName = itemView.findViewById(R.id.txt_product_name);
        }
    }
}

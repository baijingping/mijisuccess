package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.bean.Main;
import com.umeng.soexample.bean.MainLeft;
import com.umeng.soexample.bean.MainRight;
import com.umeng.soexample.utils.StringUtils;

import java.util.List;

/**
 * Created by Shinelon on 2018/10/25.
 */

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {
    private Context context;
    private List<Main> list;

    public RightAdapter(Context context, List<Main> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_right, null);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<MainRight> main = this.list.get(position).getList();

        Glide.with(context).load(main.get(position).getIcon()).into(holder.img);
        holder.txtName.setText(main.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
       private TextView txtName;
       private ImageView img;
       public ViewHolder(View itemView) {
           super(itemView);
           txtName=itemView.findViewById(R.id.txt_name);
           img=itemView.findViewById(R.id.img);
       }
   }
}

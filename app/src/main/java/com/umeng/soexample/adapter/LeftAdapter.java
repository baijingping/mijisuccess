package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.bean.MainLeft;

import java.util.List;

/**
 * Created by Shinelon on 2018/10/25.
 */

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {
    private Context context;
    private List<MainLeft> list;

    public LeftAdapter(Context context, List<MainLeft> list) {
        this.context = context;
        this.list = list;
    }

    public interface onClickListener{
        void onClick(int position);
    }
    private onClickListener listener;

    public void setonClickListener(onClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_left, null);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtName.setText(list.get(position).getName());
        holder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cid = list.get(position).getCid();
                listener.onClick(cid);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
       private TextView txtName;
       public ViewHolder(View itemView) {
           super(itemView);
           txtName=itemView.findViewById(R.id.txt_name);
       }
   }
}

package com.bawei.wuruitao.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.wuruitao.R;
import com.bawei.wuruitao.model.entity.ShopByCateGoryEntity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ProjectName: WuRuitao20200106
 * PackageName: com.bawei.wuruitao.view.adapter
 * ClassName:   LeftRVAdapter
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2020/1/6_9:11
 */
public class RightRVAdapter extends RecyclerView.Adapter<RightRVAdapter.VH> {


    private final Context context;
    private List<ShopByCateGoryEntity.DataBean> category;


    public RightRVAdapter(Context context, List<ShopByCateGoryEntity.DataBean> category) {

        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(View.inflate(context, R.layout.item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        ShopByCateGoryEntity.DataBean list = category.get(position);
        Glide.with(context)
                .load(list.getGoods_thumb())
                .placeholder(R.mipmap.ic_launcher)//占位图
                .error(R.mipmap.ic_launcher)//错误图
                .into(holder.ivPic);
        holder.tvName.setText(list.getGoods_name());
        holder.tvBrief.setText(list.getGoods_brief());
        holder.tvPrice.setText(list.getCurrency_price());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addOnItemClickListener != null) {
                    addOnItemClickListener.onItemClickListener(list.getGoods_name(), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_brief)
        TextView tvBrief;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        public VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private AddOnItemClickListener addOnItemClickListener;

    public void setAddOnItemClickListener(AddOnItemClickListener addOnItemClickListener) {
        this.addOnItemClickListener = addOnItemClickListener;
    }

    public interface AddOnItemClickListener {
        void onItemClickListener(String shopName, int position);
    }


}

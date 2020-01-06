package com.bawei.wuruitao.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.wuruitao.R;

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
public class LeftRVAdapter extends RecyclerView.Adapter<LeftRVAdapter.VH> {


    private final Context context;
    private final List<String> category;


    public LeftRVAdapter(Context context, List<String> category) {

        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(View.inflate(context, R.layout.item_textview, null));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        String name = category.get(position);
        holder.tvName.setText(name);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addOnItemClickListener != null) {
                    addOnItemClickListener.onItemClickListener(name,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        public VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private AddOnItemClickListener addOnItemClickListener;

    public void setAddOnItemClickListener(AddOnItemClickListener addOnItemClickListener) {
        this.addOnItemClickListener = addOnItemClickListener;
    }

    public interface AddOnItemClickListener{
        void onItemClickListener(String shopName,int position);
    }


}

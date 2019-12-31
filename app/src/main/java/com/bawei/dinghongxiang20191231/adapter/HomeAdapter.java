package com.bawei.dinghongxiang20191231.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.dinghongxiang20191231.R;
import com.bawei.dinghongxiang20191231.entity.HomeEntity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHorlder> {



    private Context context;
    private List<HomeEntity.RankingBean> list;

    public HomeAdapter(Context context, List<HomeEntity.RankingBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHorlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final View inflate = View.inflate(context, R.layout.rev_item, null);
        final MyViewHorlder myViewHorlder = new MyViewHorlder(inflate);
        return myViewHorlder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHorlder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.ren.setText(list.get(position).getRank());
        Glide.with(context)
                .load(list.get(position).getAvatar())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .circleCrop()
                .into(holder.iv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                revCallBack.onclick(list.get(position).getName());
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHorlder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.ren)
        TextView ren;
        public MyViewHorlder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    private RevCallBack revCallBack;

    public void setRevCallBack(RevCallBack revCallBack) {
        this.revCallBack = revCallBack;
    }

    public interface RevCallBack{
        void onclick(String name);
    }
}

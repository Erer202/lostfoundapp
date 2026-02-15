package com.er.lostfoundapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.er.lostfoundapp.R;
import com.er.lostfoundapp.entity.LostItem;
import java.util.List;

/**
 * 失物/拾物列表适配器（修改版：右侧图片+用户输入时间）
 */
public class LostItemAdapter extends RecyclerView.Adapter<LostItemAdapter.ViewHolder> {
    // 上下文
    private Context mContext;
    // 数据列表
    private List<LostItem> mList;

    public LostItemAdapter(Context context, List<LostItem> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 加载修改后的列表项布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_lost_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 获取当前项数据
        LostItem item = mList.get(position);

        // 1. 设置类型标签（丢失/拾获）
        if (item.isLost()) {
            holder.tvTypeTag.setText("丢失物品");
            holder.tvTypeTag.setBackgroundColor(mContext.getResources().getColor(android.R.color.holo_blue_light));
        } else {
            holder.tvTypeTag.setText("拾获物品");
            holder.tvTypeTag.setBackgroundColor(mContext.getResources().getColor(android.R.color.holo_orange_light));
        }

        // 2. 设置物品名称
        holder.tvItemName.setText(item.getLostName());

        // 3. 设置场景+用户输入的时间字符串（核心修改）
        String userInputTime = item.getLostTimeStr() == null ? "未填写时间" : item.getLostTimeStr();
        String sceneTime = item.getLostScene() + " | " + userInputTime;
        holder.tvSceneTime.setText(sceneTime);

        // 4. 设置核心特征
        holder.tvFeature.setText(item.getLostFeature());

        // 5. 设置免打扰标签（仅拾获且开启免打扰时显示）
        if (!item.isLost() && item.getVerifyQuestion() != null && !item.getVerifyQuestion().isEmpty()) {
            holder.tvDisturbTag.setVisibility(View.VISIBLE);
        } else {
            holder.tvDisturbTag.setVisibility(View.GONE);
        }

        // 6. 图片暂用默认占位图（后续可扩展：根据item.getImagePath()加载图片）
        // 示例：if (!TextUtils.isEmpty(item.getImagePath())) { Glide.with(mContext).load(item.getImagePath()).into(holder.ivItemImage); }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 视图持有者（新增ImageView控件）
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTypeTag;
        TextView tvItemName;
        TextView tvSceneTime;
        TextView tvFeature;
        TextView tvDisturbTag;
        ImageView ivItemImage; // 新增：右侧图片控件

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTypeTag = itemView.findViewById(R.id.tv_type_tag);
            tvItemName = itemView.findViewById(R.id.tv_item_name);
            tvSceneTime = itemView.findViewById(R.id.tv_scene_time);
            tvFeature = itemView.findViewById(R.id.tv_feature);
            tvDisturbTag = itemView.findViewById(R.id.tv_disturb_tag);
            ivItemImage = itemView.findViewById(R.id.iv_item_image); // 绑定图片控件
        }
    }
}
package com.cmcorg20230301.teamup.activity.home.contact;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.model.entity.SysImSessionDO;

import java.util.Date;
import java.util.List;

import cn.hutool.core.date.DateUtil;

public class HomeContactRecycleAdapter extends RecyclerView.Adapter<HomeContactRecycleAdapter.ViewHolder> {

    private Context context;

    private List<SysImSessionDO> dataList;

    public HomeContactRecycleAdapter(Context context, List<SysImSessionDO> dataList) {

        this.context = context;
        this.dataList = dataList;

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // 创建自定义布局
        View itemView = View.inflate(context, R.layout.home_chat_session_item, null);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        itemView.setLayoutParams(layoutParams);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SysImSessionDO sysImSessionDO = dataList.get(position);

        Glide.with(context).load("https://gw.alipayobjects.com/zos/antfincdn/efFD%24IOql2/weixintupian_20170331104822.jpg").into(holder.homeChatSessionItemAvatar);

        holder.homeChatSessionItemUserName.setText(sysImSessionDO.getName());

        holder.homeChatSessionItemContent.setText(sysImSessionDO.getLastContent());

        holder.homeChatSessionItemTime.setText(DateUtil.formatDateTime(new Date(sysImSessionDO.getLastContentCreateTs())));

    }

    @Override
    public int getItemCount() {

        return dataList.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView homeChatSessionItemAvatar;

        TextView homeChatSessionItemUserName;

        TextView homeChatSessionItemContent;

        TextView homeChatSessionItemTime;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            homeChatSessionItemAvatar = itemView.findViewById(R.id.homeChatSessionItemAvatar);

            homeChatSessionItemUserName = itemView.findViewById(R.id.homeChatSessionItemUserName);

            homeChatSessionItemContent = itemView.findViewById(R.id.homeChatSessionItemContent);

            homeChatSessionItemTime = itemView.findViewById(R.id.homeChatSessionItemTime);

            // 在adapter中设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v);
                    }

                }

            });

        }

    }

    /**
     * 设置item的监听事件的接口
     */
    public interface OnItemClickListener {

        void onItemClick(View view);

    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}

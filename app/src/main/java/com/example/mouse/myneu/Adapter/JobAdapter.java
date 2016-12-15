package com.example.mouse.myneu.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mouse.myneu.R;
import com.example.mouse.myneu.bean.Position;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by mouse on 2016/9/23.
 */
public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Position> mData;

    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public JobAdapter(Context context, List<Position> data) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_job_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Position bean = mData.get(position);
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(holder.itemView, position); //如果用holder.getAdapterPosition()则在装饰后计算含有头部时的位置
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnItemClickListener.onItemLongClick(holder.itemView, holder.getAdapterPosition());
                    return false;
                }
            });
        }

        holder.itemPostName.setText(bean.getPostName());
        holder.itemPersonNums.setText("已有"+bean.getPersonNums()+"人学习本课程");
        holder.itemCourseNums.setText("总课程："+bean.getCourseNums()+"门");
        holder.itemCourseHours.setText("总课时："+bean.getCourseHours()+"h");
        ImageLoader.getInstance().displayImage(bean.getPostUrl(), holder.itemPostImg);
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout itemLayout;
        private ImageView itemPostImg;
        private TextView itemPostName;
        private TextView itemPersonNums;
        private TextView itemCourseNums;
        private TextView itemCourseHours;

        public ViewHolder(View itemView) {
            super(itemView);
            itemLayout = (LinearLayout) itemView.findViewById(R.id.item_position_layout);
            itemPostImg = (ImageView) itemView.findViewById(R.id.item_postImg);
            itemPostName = (TextView) itemView.findViewById(R.id.item_postName);
            itemPersonNums = (TextView) itemView.findViewById(R.id.item_personNum);
            itemCourseNums = (TextView) itemView.findViewById(R.id.item_courseNum);
            itemCourseHours = (TextView) itemView.findViewById(R.id.item_courseHours);

        }
    }
}

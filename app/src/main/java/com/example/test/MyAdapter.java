package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<InfoResponse> videoList;
    // 使用此Adapter的Activity
    Context context;


    // Holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView author;
        public TextView likecount;
        public TextView time;
        public ImageView cover;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.videoTitle);
            author = itemView.findViewById(R.id.videoAuthor);
            likecount = itemView.findViewById(R.id.likeCount);
            time = itemView.findViewById(R.id.videoTime);
            cover = itemView.findViewById(R.id.videoCover);
        }

    }


    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    // onBind
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.title.setText("标题：".concat(videoList.get(position).description));
        holder.author.setText("作者：".concat(videoList.get(position).nickname));
        holder.likecount.setText("点赞：".concat(videoList.get(position).likeCount));
        holder.time.setText("视频时间：".concat(""));
        Glide.with(context)
                .load(videoList.get(position).avatarUrl)
                .transition(withCrossFade())
                .into(holder.cover);

    }


    @Override
    public int getItemCount() {
        if (videoList == null) {
            return 0;
        }
        else return videoList.size();
    }


    // 更新数据
    public void setVideoList(List<InfoResponse> newList) {
        videoList = newList;
    }


    // 为使用Glide加载图片，创建Adapter时需传入context
    public MyAdapter(Context context) {
        this.context = context;
    }
}

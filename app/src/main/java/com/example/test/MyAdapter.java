package com.example.test;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<InfoResponse> infoList;
    private final ListItemClickListener listItemClickListener;
    Context context;// 使用此Adapter的Activity


    // Holder
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // view
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
            // click
            itemView.setOnClickListener(this);
        }

        // click
        @Override
        public void onClick(View itemView) {
            int clickedPosition = getAdapterPosition();
            if (listItemClickListener != null) {
                listItemClickListener.onListItemClick(clickedPosition);
            }
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
        holder.title.setText("标题：".concat(infoList.get(position).description));
        holder.author.setText("作者：".concat(infoList.get(position).nickname));
        holder.likecount.setText("点赞：".concat(infoList.get(position).likeCount));
        // 通过mediaPlayer获取视频时间
        MediaPlayer mediaPlayer = new MediaPlayer();
        int videoTime = 0;
        try {
            mediaPlayer.setDataSource(String.valueOf(Uri.parse(infoList.get(position).feedUrl)));
            mediaPlayer.prepare();
            videoTime = mediaPlayer.getDuration();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int totalS = videoTime / 1000;
        int M = totalS / 60;
        int S = totalS % 60;

        holder.time.setText("视频时间：".concat(String.valueOf(M).concat(":").concat(String.valueOf(S))));
        // 加载图片
        Glide.with(context)
                .load(infoList.get(position).avatarUrl)
                .transition(withCrossFade())
                .into(holder.cover);
    }


    @Override
    public int getItemCount() {
        if (infoList == null) {
            return 0;
        }
        else return infoList.size();
    }


    // 更新数据
    public void setVideoList(List<InfoResponse> newList) {
        infoList = newList;
    }


    // 为使用Glide加载图片，创建Adapter时需传入context
    public MyAdapter(ListItemClickListener listener, Context context) {
        this.listItemClickListener = listener;
        this.context = context;
    }


    // 点击
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}

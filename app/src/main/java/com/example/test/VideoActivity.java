package com.example.test;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    private Uri videoUri;// 视频Uri
    private VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        // 隐藏标题栏
        getSupportActionBar().hide();

        // 获取Uri
        videoUri = Uri.parse((String) getIntent().getSerializableExtra("videoUri"));

        // 初始化VideoView,MediaController及相关设置
        videoView = findViewById(R.id.videoView);
        videoView.setVideoURI(videoUri);

        MediaController mediaController = new MediaController(this);

        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt("progress", videoView.getCurrentPosition());
        savedInstanceState.putBoolean("playing", videoView.isPlaying());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int position = savedInstanceState.getInt("progress");
        boolean playing = savedInstanceState.getBoolean("playing");

        videoView.seekTo(position);
        if (playing)
            videoView.start();

    }

}

package com.example.test;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private RecyclerView videoList;
    private LinearLayoutManager layoutManager;
    private MyAdapter mainAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 隐藏标题栏
        getSupportActionBar().hide();
        // 初始化视频列表
        initialVideoList();
    }

    private void initialVideoList() {
        // 获取videoList控件
        videoList = findViewById(R.id.videoList);
        // 为videoList设置布局管理器
        layoutManager = new LinearLayoutManager(this);
        videoList.setLayoutManager(layoutManager);
        // 为recycler设置数据适配器
        // 为使用Glide加载图片，需将activity传入
        mainAdapter = new MyAdapter(this);
        videoList.setAdapter(mainAdapter);
        // 获取视频信息
        getInfo();
    }

    private void getInfo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://beiyou.bytedance.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getArticles().enqueue(new Callback<List<InfoResponse>>() {
            @Override
            public void onResponse(Call<List<InfoResponse>> call, Response<List<InfoResponse>> response) {
                if (response.body() != null) {
                    Log.d("retrofit", Integer.toString(response.body().size()));
                    List<InfoResponse> videoInfos = response.body();
                    Log.d("retrofit", videoInfos.toString());
                    if (videoInfos.size() != 0) {
                        mainAdapter.setVideoList(videoInfos);
                        mainAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<InfoResponse>> call, Throwable t) {
                Log.d("retrofit Fail", t.getMessage());
            }
        });
    }
}

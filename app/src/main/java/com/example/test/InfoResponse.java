package com.example.test;

import com.google.gson.annotations.SerializedName;

public class InfoResponse {

    @SerializedName("_id")
    public String _id;
    @SerializedName("feedurl")
    public String feedUrl;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("description")
    public String description;
    @SerializedName("likecount")
    public String likeCount;
    @SerializedName("avatar")
    public String avatarUrl;

    @Override
    public String toString() {
        return "VideoInfo{" +
                "id=" + _id +
                ", feedUrl='" + feedUrl +
                ", nickname=" + nickname +
                ", description=" + description +
                ", likeCount=" + likeCount +
                ", avatar=" + avatarUrl +
                '}';
    }
}

package com.example.nina.test.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "pinned")
public class PinnedNews {

    @PrimaryKey
    @NonNull
    public final String id;
    private String newsUrl;
    private String house;
    private String date;

    public PinnedNews(String id, String newsUrl, String house, String date) {
        this.id = id;
        this.newsUrl = newsUrl;
        this.house = house;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public String getHouse() {
        return house;
    }

    public String getDate() {
        return date;
    }
}

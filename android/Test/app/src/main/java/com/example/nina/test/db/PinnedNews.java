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

    public PinnedNews(String id, String newsUrl) {
        this.id = id;
        this.newsUrl = newsUrl;
    }

    public String getId() {
        return id;
    }

    public String getNewsUrl() {
        return newsUrl;
    }
}

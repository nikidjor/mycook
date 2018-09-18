package com.example.nina.test.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PinnedNewsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPinnedNews(PinnedNews news);

    @Query("select * from pinned")
    List<PinnedNews> getAllPinnedNews();


     @Query("delete from pinned")
     void removeAllPinnedNews();
}

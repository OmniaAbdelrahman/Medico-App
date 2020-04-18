package com.example.medicalapp.API.NewsModel;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface newsDao {
    @Insert
    public void addnews(List<ArticlesItem> articlesItems);
    @Update
    public void updatenews(List<ArticlesItem> articlesItems);
    @Query("select * from ArticlesItem")
    List<ArticlesItem>ARTICLES_ITEMS();
}

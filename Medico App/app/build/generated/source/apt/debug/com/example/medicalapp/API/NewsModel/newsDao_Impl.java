package com.example.medicalapp.API.NewsModel;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class newsDao_Impl implements newsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfArticlesItem;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfArticlesItem;

  public newsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfArticlesItem = new EntityInsertionAdapter<ArticlesItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `ArticlesItem`(`id_db`,`publishedAt`,`author`,`urlToImage`,`description`,`title`,`url`,`content`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ArticlesItem value) {
        stmt.bindLong(1, value.id_db);
        if (value.getPublishedAt() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPublishedAt());
        }
        if (value.getAuthor() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAuthor());
        }
        if (value.getUrlToImage() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getUrlToImage());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDescription());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getTitle());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getUrl());
        }
        if (value.getContent() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getContent());
        }
      }
    };
    this.__updateAdapterOfArticlesItem = new EntityDeletionOrUpdateAdapter<ArticlesItem>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `ArticlesItem` SET `id_db` = ?,`publishedAt` = ?,`author` = ?,`urlToImage` = ?,`description` = ?,`title` = ?,`url` = ?,`content` = ? WHERE `id_db` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ArticlesItem value) {
        stmt.bindLong(1, value.id_db);
        if (value.getPublishedAt() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPublishedAt());
        }
        if (value.getAuthor() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAuthor());
        }
        if (value.getUrlToImage() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getUrlToImage());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDescription());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getTitle());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getUrl());
        }
        if (value.getContent() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getContent());
        }
        stmt.bindLong(9, value.id_db);
      }
    };
  }

  @Override
  public void addnews(List<ArticlesItem> articlesItems) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfArticlesItem.insert(articlesItems);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updatenews(List<ArticlesItem> articlesItems) {
    __db.beginTransaction();
    try {
      __updateAdapterOfArticlesItem.handleMultiple(articlesItems);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<ArticlesItem> ARTICLES_ITEMS() {
    final String _sql = "select * from ArticlesItem";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfIdDb = _cursor.getColumnIndexOrThrow("id_db");
      final int _cursorIndexOfPublishedAt = _cursor.getColumnIndexOrThrow("publishedAt");
      final int _cursorIndexOfAuthor = _cursor.getColumnIndexOrThrow("author");
      final int _cursorIndexOfUrlToImage = _cursor.getColumnIndexOrThrow("urlToImage");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
      final int _cursorIndexOfContent = _cursor.getColumnIndexOrThrow("content");
      final List<ArticlesItem> _result = new ArrayList<ArticlesItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ArticlesItem _item;
        _item = new ArticlesItem();
        _item.id_db = _cursor.getInt(_cursorIndexOfIdDb);
        final String _tmpPublishedAt;
        _tmpPublishedAt = _cursor.getString(_cursorIndexOfPublishedAt);
        _item.setPublishedAt(_tmpPublishedAt);
        final String _tmpAuthor;
        _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
        _item.setAuthor(_tmpAuthor);
        final String _tmpUrlToImage;
        _tmpUrlToImage = _cursor.getString(_cursorIndexOfUrlToImage);
        _item.setUrlToImage(_tmpUrlToImage);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _item.setDescription(_tmpDescription);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        _item.setTitle(_tmpTitle);
        final String _tmpUrl;
        _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
        _item.setUrl(_tmpUrl);
        final String _tmpContent;
        _tmpContent = _cursor.getString(_cursorIndexOfContent);
        _item.setContent(_tmpContent);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}

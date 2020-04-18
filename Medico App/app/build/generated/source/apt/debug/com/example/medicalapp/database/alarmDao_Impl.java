package com.example.medicalapp.database;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class alarmDao_Impl implements alarmDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfalarmmodel;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfalarmmodel;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfalarmmodel;

  private final SharedSQLiteStatement __preparedStmtOfDeleteall;

  public alarmDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfalarmmodel = new EntityInsertionAdapter<alarmmodel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `alarmmodel`(`id`,`Title`,`description`,`hours`,`minutes`,`isworking`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, alarmmodel value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        stmt.bindLong(4, value.getHours());
        stmt.bindLong(5, value.getMinutes());
        final Integer _tmp;
        _tmp = value.getIsworking() == null ? null : (value.getIsworking() ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, _tmp);
        }
      }
    };
    this.__deletionAdapterOfalarmmodel = new EntityDeletionOrUpdateAdapter<alarmmodel>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `alarmmodel` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, alarmmodel value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfalarmmodel = new EntityDeletionOrUpdateAdapter<alarmmodel>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `alarmmodel` SET `id` = ?,`Title` = ?,`description` = ?,`hours` = ?,`minutes` = ?,`isworking` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, alarmmodel value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        stmt.bindLong(4, value.getHours());
        stmt.bindLong(5, value.getMinutes());
        final Integer _tmp;
        _tmp = value.getIsworking() == null ? null : (value.getIsworking() ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, _tmp);
        }
        stmt.bindLong(7, value.getId());
      }
    };
    this.__preparedStmtOfDeleteall = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from alarmmodel";
        return _query;
      }
    };
  }

  @Override
  public void insertalarm(alarmmodel alarm) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfalarmmodel.insert(alarm);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deletealarm(alarmmodel alarmmodel) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfalarmmodel.handle(alarmmodel);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updatealarm(alarmmodel alarmmodel) {
    __db.beginTransaction();
    try {
      __updateAdapterOfalarmmodel.handle(alarmmodel);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteall() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteall.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteall.release(_stmt);
    }
  }

  @Override
  public List<alarmmodel> showalarms() {
    final String _sql = "select * from alarmmodel";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("Title");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final int _cursorIndexOfHours = _cursor.getColumnIndexOrThrow("hours");
      final int _cursorIndexOfMinutes = _cursor.getColumnIndexOrThrow("minutes");
      final int _cursorIndexOfIsworking = _cursor.getColumnIndexOrThrow("isworking");
      final List<alarmmodel> _result = new ArrayList<alarmmodel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final alarmmodel _item;
        _item = new alarmmodel();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        _item.setTitle(_tmpTitle);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _item.setDescription(_tmpDescription);
        final int _tmpHours;
        _tmpHours = _cursor.getInt(_cursorIndexOfHours);
        _item.setHours(_tmpHours);
        final int _tmpMinutes;
        _tmpMinutes = _cursor.getInt(_cursorIndexOfMinutes);
        _item.setMinutes(_tmpMinutes);
        final Boolean _tmpIsworking;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfIsworking)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfIsworking);
        }
        _tmpIsworking = _tmp == null ? null : _tmp != 0;
        _item.setIsworking(_tmpIsworking);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}

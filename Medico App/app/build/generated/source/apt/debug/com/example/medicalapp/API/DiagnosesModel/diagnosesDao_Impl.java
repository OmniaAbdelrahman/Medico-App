package com.example.medicalapp.API.DiagnosesModel;

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
public final class diagnosesDao_Impl implements diagnosesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfIssue;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfIssue;

  public diagnosesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfIssue = new EntityInsertionAdapter<Issue>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Issue`(`issue_id`,`accuracy`,`ranking`,`profName`,`icdName`,`iD`,`icd`,`name`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Issue value) {
        stmt.bindLong(1, value.issue_id);
        stmt.bindDouble(2, value.getAccuracy());
        stmt.bindLong(3, value.getRanking());
        if (value.getProfName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getProfName());
        }
        if (value.getIcdName() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getIcdName());
        }
        stmt.bindLong(6, value.getID());
        if (value.getIcd() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getIcd());
        }
        if (value.getName() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getName());
        }
      }
    };
    this.__updateAdapterOfIssue = new EntityDeletionOrUpdateAdapter<Issue>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Issue` SET `issue_id` = ?,`accuracy` = ?,`ranking` = ?,`profName` = ?,`icdName` = ?,`iD` = ?,`icd` = ?,`name` = ? WHERE `issue_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Issue value) {
        stmt.bindLong(1, value.issue_id);
        stmt.bindDouble(2, value.getAccuracy());
        stmt.bindLong(3, value.getRanking());
        if (value.getProfName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getProfName());
        }
        if (value.getIcdName() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getIcdName());
        }
        stmt.bindLong(6, value.getID());
        if (value.getIcd() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getIcd());
        }
        if (value.getName() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getName());
        }
        stmt.bindLong(9, value.issue_id);
      }
    };
  }

  @Override
  public void add_diagnose(List<Issue> issues) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfIssue.insert(issues);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update_diagnose(List<Issue> issues) {
    __db.beginTransaction();
    try {
      __updateAdapterOfIssue.handleMultiple(issues);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Issue> ISSUES() {
    final String _sql = "select * from Issue";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfIssueId = _cursor.getColumnIndexOrThrow("issue_id");
      final int _cursorIndexOfAccuracy = _cursor.getColumnIndexOrThrow("accuracy");
      final int _cursorIndexOfRanking = _cursor.getColumnIndexOrThrow("ranking");
      final int _cursorIndexOfProfName = _cursor.getColumnIndexOrThrow("profName");
      final int _cursorIndexOfIcdName = _cursor.getColumnIndexOrThrow("icdName");
      final int _cursorIndexOfID = _cursor.getColumnIndexOrThrow("iD");
      final int _cursorIndexOfIcd = _cursor.getColumnIndexOrThrow("icd");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final List<Issue> _result = new ArrayList<Issue>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Issue _item;
        _item = new Issue();
        _item.issue_id = _cursor.getInt(_cursorIndexOfIssueId);
        final double _tmpAccuracy;
        _tmpAccuracy = _cursor.getDouble(_cursorIndexOfAccuracy);
        _item.setAccuracy(_tmpAccuracy);
        final int _tmpRanking;
        _tmpRanking = _cursor.getInt(_cursorIndexOfRanking);
        _item.setRanking(_tmpRanking);
        final String _tmpProfName;
        _tmpProfName = _cursor.getString(_cursorIndexOfProfName);
        _item.setProfName(_tmpProfName);
        final String _tmpIcdName;
        _tmpIcdName = _cursor.getString(_cursorIndexOfIcdName);
        _item.setIcdName(_tmpIcdName);
        final int _tmpID;
        _tmpID = _cursor.getInt(_cursorIndexOfID);
        _item.setID(_tmpID);
        final String _tmpIcd;
        _tmpIcd = _cursor.getString(_cursorIndexOfIcd);
        _item.setIcd(_tmpIcd);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}

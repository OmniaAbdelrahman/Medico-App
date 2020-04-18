package com.example.medicalapp.API.SymptomModel;

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
public final class symptomDao_Impl implements symptomDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfSymptomResponse;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfSymptomResponse;

  public symptomDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSymptomResponse = new EntityInsertionAdapter<SymptomResponse>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `SymptomResponse`(`symp_id`,`iD`,`name`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SymptomResponse value) {
        stmt.bindLong(1, value.symp_id);
        stmt.bindLong(2, value.getID());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
      }
    };
    this.__updateAdapterOfSymptomResponse = new EntityDeletionOrUpdateAdapter<SymptomResponse>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `SymptomResponse` SET `symp_id` = ?,`iD` = ?,`name` = ? WHERE `symp_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SymptomResponse value) {
        stmt.bindLong(1, value.symp_id);
        stmt.bindLong(2, value.getID());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        stmt.bindLong(4, value.symp_id);
      }
    };
  }

  @Override
  public void add_symptoms(List<SymptomResponse> symptomResponses) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfSymptomResponse.insert(symptomResponses);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update_symptoms(List<SymptomResponse> symptomDaos) {
    __db.beginTransaction();
    try {
      __updateAdapterOfSymptomResponse.handleMultiple(symptomDaos);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<SymptomResponse> SYMPTOM_RESPONSES() {
    final String _sql = "select * from SymptomResponse";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfSympId = _cursor.getColumnIndexOrThrow("symp_id");
      final int _cursorIndexOfID = _cursor.getColumnIndexOrThrow("iD");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final List<SymptomResponse> _result = new ArrayList<SymptomResponse>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final SymptomResponse _item;
        _item = new SymptomResponse();
        _item.symp_id = _cursor.getInt(_cursorIndexOfSympId);
        final int _tmpID;
        _tmpID = _cursor.getInt(_cursorIndexOfID);
        _item.setID(_tmpID);
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

package com.example.medicalapp.API.DoctorsModel;

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
public final class DoctorDao_Impl implements DoctorDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfDataItem;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfDataItem;

  public DoctorDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDataItem = new EntityInsertionAdapter<DataItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `DataItem`(`idd`,`uid`,`npi`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DataItem value) {
        stmt.bindLong(1, value.idd);
        if (value.getUid() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUid());
        }
        if (value.getNpi() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getNpi());
        }
      }
    };
    this.__updateAdapterOfDataItem = new EntityDeletionOrUpdateAdapter<DataItem>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `DataItem` SET `idd` = ?,`uid` = ?,`npi` = ? WHERE `idd` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DataItem value) {
        stmt.bindLong(1, value.idd);
        if (value.getUid() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUid());
        }
        if (value.getNpi() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getNpi());
        }
        stmt.bindLong(4, value.idd);
      }
    };
  }

  @Override
  public void add_docotr(List<DataItem> issues) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDataItem.insert(issues);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update_doctor(List<DataItem> issues) {
    __db.beginTransaction();
    try {
      __updateAdapterOfDataItem.handleMultiple(issues);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<DataItem> Doctors() {
    final String _sql = "select * from DataItem";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfIdd = _cursor.getColumnIndexOrThrow("idd");
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfNpi = _cursor.getColumnIndexOrThrow("npi");
      final List<DataItem> _result = new ArrayList<DataItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DataItem _item;
        _item = new DataItem();
        _item.idd = _cursor.getInt(_cursorIndexOfIdd);
        final String _tmpUid;
        _tmpUid = _cursor.getString(_cursorIndexOfUid);
        _item.setUid(_tmpUid);
        final String _tmpNpi;
        _tmpNpi = _cursor.getString(_cursorIndexOfNpi);
        _item.setNpi(_tmpNpi);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}

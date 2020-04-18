package com.example.medicalapp.API.DrugModel;

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
public final class drugDAo_Impl implements drugDAo {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfRecordsItem;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfRecordsItem;

  public drugDAo_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRecordsItem = new EntityInsertionAdapter<RecordsItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `RecordsItem`(`iddrug`,`immunotherapy`,`name`,`antiNeoplastic`,`chemblId`,`fdaApproved`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RecordsItem value) {
        stmt.bindLong(1, value.iddrug);
        final int _tmp;
        _tmp = value.isImmunotherapy() ? 1 : 0;
        stmt.bindLong(2, _tmp);
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        final int _tmp_1;
        _tmp_1 = value.isAntiNeoplastic() ? 1 : 0;
        stmt.bindLong(4, _tmp_1);
        if (value.getChemblId() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getChemblId());
        }
        final int _tmp_2;
        _tmp_2 = value.isFdaApproved() ? 1 : 0;
        stmt.bindLong(6, _tmp_2);
      }
    };
    this.__updateAdapterOfRecordsItem = new EntityDeletionOrUpdateAdapter<RecordsItem>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `RecordsItem` SET `iddrug` = ?,`immunotherapy` = ?,`name` = ?,`antiNeoplastic` = ?,`chemblId` = ?,`fdaApproved` = ? WHERE `iddrug` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RecordsItem value) {
        stmt.bindLong(1, value.iddrug);
        final int _tmp;
        _tmp = value.isImmunotherapy() ? 1 : 0;
        stmt.bindLong(2, _tmp);
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        final int _tmp_1;
        _tmp_1 = value.isAntiNeoplastic() ? 1 : 0;
        stmt.bindLong(4, _tmp_1);
        if (value.getChemblId() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getChemblId());
        }
        final int _tmp_2;
        _tmp_2 = value.isFdaApproved() ? 1 : 0;
        stmt.bindLong(6, _tmp_2);
        stmt.bindLong(7, value.iddrug);
      }
    };
  }

  @Override
  public void add_drug(List<RecordsItem> recordsItems) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfRecordsItem.insert(recordsItems);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update_drug(List<RecordsItem> recordsItems) {
    __db.beginTransaction();
    try {
      __updateAdapterOfRecordsItem.handleMultiple(recordsItems);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<RecordsItem> RECORDS_ITEMS() {
    final String _sql = "select * from RecordsItem";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfIddrug = _cursor.getColumnIndexOrThrow("iddrug");
      final int _cursorIndexOfImmunotherapy = _cursor.getColumnIndexOrThrow("immunotherapy");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfAntiNeoplastic = _cursor.getColumnIndexOrThrow("antiNeoplastic");
      final int _cursorIndexOfChemblId = _cursor.getColumnIndexOrThrow("chemblId");
      final int _cursorIndexOfFdaApproved = _cursor.getColumnIndexOrThrow("fdaApproved");
      final List<RecordsItem> _result = new ArrayList<RecordsItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final RecordsItem _item;
        _item = new RecordsItem();
        _item.iddrug = _cursor.getInt(_cursorIndexOfIddrug);
        final boolean _tmpImmunotherapy;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfImmunotherapy);
        _tmpImmunotherapy = _tmp != 0;
        _item.setImmunotherapy(_tmpImmunotherapy);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final boolean _tmpAntiNeoplastic;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfAntiNeoplastic);
        _tmpAntiNeoplastic = _tmp_1 != 0;
        _item.setAntiNeoplastic(_tmpAntiNeoplastic);
        final String _tmpChemblId;
        _tmpChemblId = _cursor.getString(_cursorIndexOfChemblId);
        _item.setChemblId(_tmpChemblId);
        final boolean _tmpFdaApproved;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfFdaApproved);
        _tmpFdaApproved = _tmp_2 != 0;
        _item.setFdaApproved(_tmpFdaApproved);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}

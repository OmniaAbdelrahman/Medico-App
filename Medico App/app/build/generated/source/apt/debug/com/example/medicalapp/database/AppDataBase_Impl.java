package com.example.medicalapp.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.medicalapp.API.DiagnosesModel.diagnosesDao;
import com.example.medicalapp.API.DiagnosesModel.diagnosesDao_Impl;
import com.example.medicalapp.API.DoctorsModel.DoctorDao;
import com.example.medicalapp.API.DoctorsModel.DoctorDao_Impl;
import com.example.medicalapp.API.SymptomModel.symptomDao;
import com.example.medicalapp.API.SymptomModel.symptomDao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class AppDataBase_Impl extends AppDataBase {
  private volatile diagnosesDao _diagnosesDao;

  private volatile DoctorDao _doctorDao;

  private volatile symptomDao _symptomDao;

  private volatile alarmDao _alarmDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `alarmmodel` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Title` TEXT, `description` TEXT, `hours` INTEGER NOT NULL, `minutes` INTEGER NOT NULL, `isworking` INTEGER)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `SymptomResponse` (`symp_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `iD` INTEGER NOT NULL, `name` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Issue` (`issue_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `accuracy` REAL NOT NULL, `ranking` INTEGER NOT NULL, `profName` TEXT, `icdName` TEXT, `iD` INTEGER NOT NULL, `icd` TEXT, `name` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DataItem` (`idd` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `uid` TEXT, `npi` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"5c7b1ea4ecd03c68b9098f765d3d2f9a\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `alarmmodel`");
        _db.execSQL("DROP TABLE IF EXISTS `SymptomResponse`");
        _db.execSQL("DROP TABLE IF EXISTS `Issue`");
        _db.execSQL("DROP TABLE IF EXISTS `DataItem`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsAlarmmodel = new HashMap<String, TableInfo.Column>(6);
        _columnsAlarmmodel.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsAlarmmodel.put("Title", new TableInfo.Column("Title", "TEXT", false, 0));
        _columnsAlarmmodel.put("description", new TableInfo.Column("description", "TEXT", false, 0));
        _columnsAlarmmodel.put("hours", new TableInfo.Column("hours", "INTEGER", true, 0));
        _columnsAlarmmodel.put("minutes", new TableInfo.Column("minutes", "INTEGER", true, 0));
        _columnsAlarmmodel.put("isworking", new TableInfo.Column("isworking", "INTEGER", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAlarmmodel = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAlarmmodel = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAlarmmodel = new TableInfo("alarmmodel", _columnsAlarmmodel, _foreignKeysAlarmmodel, _indicesAlarmmodel);
        final TableInfo _existingAlarmmodel = TableInfo.read(_db, "alarmmodel");
        if (! _infoAlarmmodel.equals(_existingAlarmmodel)) {
          throw new IllegalStateException("Migration didn't properly handle alarmmodel(com.example.medicalapp.database.alarmmodel).\n"
                  + " Expected:\n" + _infoAlarmmodel + "\n"
                  + " Found:\n" + _existingAlarmmodel);
        }
        final HashMap<String, TableInfo.Column> _columnsSymptomResponse = new HashMap<String, TableInfo.Column>(3);
        _columnsSymptomResponse.put("symp_id", new TableInfo.Column("symp_id", "INTEGER", true, 1));
        _columnsSymptomResponse.put("iD", new TableInfo.Column("iD", "INTEGER", true, 0));
        _columnsSymptomResponse.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSymptomResponse = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSymptomResponse = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSymptomResponse = new TableInfo("SymptomResponse", _columnsSymptomResponse, _foreignKeysSymptomResponse, _indicesSymptomResponse);
        final TableInfo _existingSymptomResponse = TableInfo.read(_db, "SymptomResponse");
        if (! _infoSymptomResponse.equals(_existingSymptomResponse)) {
          throw new IllegalStateException("Migration didn't properly handle SymptomResponse(com.example.medicalapp.API.SymptomModel.SymptomResponse).\n"
                  + " Expected:\n" + _infoSymptomResponse + "\n"
                  + " Found:\n" + _existingSymptomResponse);
        }
        final HashMap<String, TableInfo.Column> _columnsIssue = new HashMap<String, TableInfo.Column>(8);
        _columnsIssue.put("issue_id", new TableInfo.Column("issue_id", "INTEGER", true, 1));
        _columnsIssue.put("accuracy", new TableInfo.Column("accuracy", "REAL", true, 0));
        _columnsIssue.put("ranking", new TableInfo.Column("ranking", "INTEGER", true, 0));
        _columnsIssue.put("profName", new TableInfo.Column("profName", "TEXT", false, 0));
        _columnsIssue.put("icdName", new TableInfo.Column("icdName", "TEXT", false, 0));
        _columnsIssue.put("iD", new TableInfo.Column("iD", "INTEGER", true, 0));
        _columnsIssue.put("icd", new TableInfo.Column("icd", "TEXT", false, 0));
        _columnsIssue.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysIssue = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesIssue = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoIssue = new TableInfo("Issue", _columnsIssue, _foreignKeysIssue, _indicesIssue);
        final TableInfo _existingIssue = TableInfo.read(_db, "Issue");
        if (! _infoIssue.equals(_existingIssue)) {
          throw new IllegalStateException("Migration didn't properly handle Issue(com.example.medicalapp.API.DiagnosesModel.Issue).\n"
                  + " Expected:\n" + _infoIssue + "\n"
                  + " Found:\n" + _existingIssue);
        }
        final HashMap<String, TableInfo.Column> _columnsDataItem = new HashMap<String, TableInfo.Column>(3);
        _columnsDataItem.put("idd", new TableInfo.Column("idd", "INTEGER", true, 1));
        _columnsDataItem.put("uid", new TableInfo.Column("uid", "TEXT", false, 0));
        _columnsDataItem.put("npi", new TableInfo.Column("npi", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDataItem = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDataItem = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDataItem = new TableInfo("DataItem", _columnsDataItem, _foreignKeysDataItem, _indicesDataItem);
        final TableInfo _existingDataItem = TableInfo.read(_db, "DataItem");
        if (! _infoDataItem.equals(_existingDataItem)) {
          throw new IllegalStateException("Migration didn't properly handle DataItem(com.example.medicalapp.API.DoctorsModel.DataItem).\n"
                  + " Expected:\n" + _infoDataItem + "\n"
                  + " Found:\n" + _existingDataItem);
        }
      }
    }, "5c7b1ea4ecd03c68b9098f765d3d2f9a", "57e713e917cedf36d4fdc5943249bb38");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "alarmmodel","SymptomResponse","Issue","DataItem");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `alarmmodel`");
      _db.execSQL("DELETE FROM `SymptomResponse`");
      _db.execSQL("DELETE FROM `Issue`");
      _db.execSQL("DELETE FROM `DataItem`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public diagnosesDao diagnosesDao() {
    if (_diagnosesDao != null) {
      return _diagnosesDao;
    } else {
      synchronized(this) {
        if(_diagnosesDao == null) {
          _diagnosesDao = new diagnosesDao_Impl(this);
        }
        return _diagnosesDao;
      }
    }
  }

  @Override
  public DoctorDao doctorDao() {
    if (_doctorDao != null) {
      return _doctorDao;
    } else {
      synchronized(this) {
        if(_doctorDao == null) {
          _doctorDao = new DoctorDao_Impl(this);
        }
        return _doctorDao;
      }
    }
  }

  @Override
  public symptomDao symptomDao() {
    if (_symptomDao != null) {
      return _symptomDao;
    } else {
      synchronized(this) {
        if(_symptomDao == null) {
          _symptomDao = new symptomDao_Impl(this);
        }
        return _symptomDao;
      }
    }
  }

  @Override
  public alarmDao alarmDao() {
    if (_alarmDao != null) {
      return _alarmDao;
    } else {
      synchronized(this) {
        if(_alarmDao == null) {
          _alarmDao = new alarmDao_Impl(this);
        }
        return _alarmDao;
      }
    }
  }
}

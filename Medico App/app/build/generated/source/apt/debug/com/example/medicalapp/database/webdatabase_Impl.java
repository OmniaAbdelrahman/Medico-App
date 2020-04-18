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
import com.example.medicalapp.API.DrugModel.drugDAo;
import com.example.medicalapp.API.DrugModel.drugDAo_Impl;
import com.example.medicalapp.API.NewsModel.newsDao;
import com.example.medicalapp.API.NewsModel.newsDao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class webdatabase_Impl extends webdatabase {
  private volatile drugDAo _drugDAo;

  private volatile newsDao _newsDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Issue` (`issue_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `accuracy` REAL NOT NULL, `ranking` INTEGER NOT NULL, `profName` TEXT, `icdName` TEXT, `iD` INTEGER NOT NULL, `icd` TEXT, `name` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `RecordsItem` (`iddrug` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `immunotherapy` INTEGER NOT NULL, `name` TEXT, `antiNeoplastic` INTEGER NOT NULL, `chemblId` TEXT, `fdaApproved` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ArticlesItem` (`id_db` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `publishedAt` TEXT, `author` TEXT, `urlToImage` TEXT, `description` TEXT, `title` TEXT, `url` TEXT, `content` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DataItem` (`idd` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `uid` TEXT, `npi` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"59c31d0e9fcd7c113348f0ca9ba26375\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Issue`");
        _db.execSQL("DROP TABLE IF EXISTS `RecordsItem`");
        _db.execSQL("DROP TABLE IF EXISTS `ArticlesItem`");
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
        final HashMap<String, TableInfo.Column> _columnsRecordsItem = new HashMap<String, TableInfo.Column>(6);
        _columnsRecordsItem.put("iddrug", new TableInfo.Column("iddrug", "INTEGER", true, 1));
        _columnsRecordsItem.put("immunotherapy", new TableInfo.Column("immunotherapy", "INTEGER", true, 0));
        _columnsRecordsItem.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsRecordsItem.put("antiNeoplastic", new TableInfo.Column("antiNeoplastic", "INTEGER", true, 0));
        _columnsRecordsItem.put("chemblId", new TableInfo.Column("chemblId", "TEXT", false, 0));
        _columnsRecordsItem.put("fdaApproved", new TableInfo.Column("fdaApproved", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRecordsItem = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRecordsItem = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRecordsItem = new TableInfo("RecordsItem", _columnsRecordsItem, _foreignKeysRecordsItem, _indicesRecordsItem);
        final TableInfo _existingRecordsItem = TableInfo.read(_db, "RecordsItem");
        if (! _infoRecordsItem.equals(_existingRecordsItem)) {
          throw new IllegalStateException("Migration didn't properly handle RecordsItem(com.example.medicalapp.API.DrugModel.RecordsItem).\n"
                  + " Expected:\n" + _infoRecordsItem + "\n"
                  + " Found:\n" + _existingRecordsItem);
        }
        final HashMap<String, TableInfo.Column> _columnsArticlesItem = new HashMap<String, TableInfo.Column>(8);
        _columnsArticlesItem.put("id_db", new TableInfo.Column("id_db", "INTEGER", true, 1));
        _columnsArticlesItem.put("publishedAt", new TableInfo.Column("publishedAt", "TEXT", false, 0));
        _columnsArticlesItem.put("author", new TableInfo.Column("author", "TEXT", false, 0));
        _columnsArticlesItem.put("urlToImage", new TableInfo.Column("urlToImage", "TEXT", false, 0));
        _columnsArticlesItem.put("description", new TableInfo.Column("description", "TEXT", false, 0));
        _columnsArticlesItem.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsArticlesItem.put("url", new TableInfo.Column("url", "TEXT", false, 0));
        _columnsArticlesItem.put("content", new TableInfo.Column("content", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysArticlesItem = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesArticlesItem = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoArticlesItem = new TableInfo("ArticlesItem", _columnsArticlesItem, _foreignKeysArticlesItem, _indicesArticlesItem);
        final TableInfo _existingArticlesItem = TableInfo.read(_db, "ArticlesItem");
        if (! _infoArticlesItem.equals(_existingArticlesItem)) {
          throw new IllegalStateException("Migration didn't properly handle ArticlesItem(com.example.medicalapp.API.NewsModel.ArticlesItem).\n"
                  + " Expected:\n" + _infoArticlesItem + "\n"
                  + " Found:\n" + _existingArticlesItem);
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
    }, "59c31d0e9fcd7c113348f0ca9ba26375", "9090644167d9fdd47ef44d8ae877394c");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Issue","RecordsItem","ArticlesItem","DataItem");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Issue`");
      _db.execSQL("DELETE FROM `RecordsItem`");
      _db.execSQL("DELETE FROM `ArticlesItem`");
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
  public drugDAo drugDAo() {
    if (_drugDAo != null) {
      return _drugDAo;
    } else {
      synchronized(this) {
        if(_drugDAo == null) {
          _drugDAo = new drugDAo_Impl(this);
        }
        return _drugDAo;
      }
    }
  }

  @Override
  public newsDao newsDao() {
    if (_newsDao != null) {
      return _newsDao;
    } else {
      synchronized(this) {
        if(_newsDao == null) {
          _newsDao = new newsDao_Impl(this);
        }
        return _newsDao;
      }
    }
  }
}

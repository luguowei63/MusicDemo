package com.iflytek.kotline.kotlin.musicdemo.provider

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

/**
 *Created by lgw on 2020/10/15
 */
class RecentStore(context: Context) {

    var mMusicDB: MusicDB;


    init {
        mMusicDB = MusicDB.getInstance(context)
    }

    companion object {
        const val MAX_ITEMS_IN_DB = 100
        lateinit var sInstance: RecentStore

        @Synchronized
        fun getInstacnce(context: Context): RecentStore {
            if (sInstance == null) {
                sInstance = RecentStore(context.applicationContext);
            }
            return sInstance
        }
    }

    fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS " + RecentStore.RecentStoreColumns.NAME + " ("
                    + RecentStore.RecentStoreColumns.ID + " LONG NOT NULL," + RecentStore.RecentStoreColumns.TIMEPLAYED
                    + " LONG NOT NULL);"
        )
    }

    fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + RecentStore.RecentStoreColumns.NAME)
        onCreate(db)
    }


    @Synchronized
    fun addSongId(songId: Long) {

        var database: SQLiteDatabase = mMusicDB.writableDatabase
        database.beginTransaction()

        try {

            var mostRecentItem : Cursor

            try {

                mostRecentItem


            } finally {

            }


        }finally {

        }




    }

    @Synchronized
    fun queryRecentIds(limit: String?): Cursor? {
        val database: SQLiteDatabase = mMusicDB.getReadableDatabase()
        return database.query(
            RecentStoreColumns.NAME, arrayOf(RecentStoreColumns.ID), null, null, null, null,
            RecentStoreColumns.TIMEPLAYED + " DESC", limit
        )
    }
    interface RecentStoreColumns {
        companion object {
            /* Table name */
            const val NAME = "recenthistory"

            /* Album IDs column */
            const val ID = "songid"

            /* Time played column */
            const val TIMEPLAYED = "timeplayed"
        }
    }


}
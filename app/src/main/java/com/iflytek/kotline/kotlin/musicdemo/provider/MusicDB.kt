package com.iflytek.kotline.kotlin.musicdemo.provider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi

/**
 *Created by lgw on 2020/10/14
 */
@RequiresApi(Build.VERSION_CODES.P)
class MusicDB(
    context: Context?,
    name: String?,
    version: Int,
    factory: SQLiteDatabase.CursorFactory?
) : SQLiteOpenHelper(context, name, factory, version) {

    lateinit var mContext: Context

    constructor(context: Context) : this(context, DATA_BASE_NAME, VERSION, null) {
        this.mContext = context
    }

    companion object {
        const val DATA_BASE_NAME: String = "musicdb.db"
        const val VERSION: Int = 4
        lateinit var sInstance: MusicDB
        @Synchronized
        fun getInstance(context: Context): MusicDB {
            if (sInstance == null) {
                sInstance = MusicDB(context.applicationContext)
            }
            return sInstance
        }


    }


    override fun onCreate(db: SQLiteDatabase?) {
        MusicPlaybackState.getInstance(mContext).onCreate(db)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


}
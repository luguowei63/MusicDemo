package com.iflytek.kotline.kotlin.musicdemo.provider

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.iflytek.kotline.kotlin.musicdemo.service.MusicTrack
import java.util.*

/**
 *Created by lgw on 2020/10/15
 */
class MusicPlaybackState(context: Context) {
    lateinit var mMusicDatabase: MusicDB

    init {
        mMusicDatabase = MusicDB.getInstance(context)
    }

    companion object {
        lateinit var sInstance: MusicPlaybackState

        @Synchronized
        fun getInstance(context: Context): MusicPlaybackState {
            if (sInstance == null) {
                sInstance = MusicPlaybackState(context)
            }
            return sInstance
        }
    }


    fun onCreate(db: SQLiteDatabase?) {
        var builder: StringBuilder = StringBuilder()
        builder.append("CREATE TABLE IF NOT EXISTS ")
        builder.append(PlaybackQueueColumns.NAME)
        builder.append("(")

        builder.append(PlaybackQueueColumns.TRACK_ID)
        builder.append(" LONG NOT NULL,")

        builder.append(PlaybackQueueColumns.SOURCE_POSITION)
        builder.append("INT NOT NULL ")

        db?.execSQL(builder.toString())

        builder = StringBuilder()
        builder.append("CREATE TABLE IF NOT EXISTS ")
        builder.append(PlaybackHistoryColumns.NAME)
        builder.append("(")

        builder.append(PlaybackHistoryColumns.POSITION)
        builder.append(" INT NOT NULL);")

        db?.execSQL(builder.toString())

        builder = StringBuilder()
        builder.append("CREATE TABLE IF NOT EXISTS ")
        builder.append(PlaybackHistoryColumns.NAME)
        builder.append("(")

        builder.append(PlaybackHistoryColumns.POSITION)
        builder.append(" INT NOT NULL);")

        db?.execSQL(builder.toString())

    }


    fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        // this table was created in version 2 so call the onCreate method if we hit that scenario
        if (oldVersion < 2 && newVersion >= 2) {
            onCreate(db)
        }

    }

    fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + PlaybackQueueColumns.NAME)
        db.execSQL("DROP TABLE IF EXISTS " + PlaybackHistoryColumns.NAME)
        onCreate(db)
    }


    @Synchronized
    fun Insert(track: MusicTrack) {
        var database: SQLiteDatabase = mMusicDatabase.writableDatabase
        database.beginTransaction()

        try {
            var values: ContentValues = ContentValues(2)
            values.put(PlaybackQueueColumns.TRACK_ID, track.mId)
            values.put(PlaybackQueueColumns.SOURCE_POSITION, track.mSourcePosition)
            database.insert(PlaybackQueueColumns.NAME, null, values)
            database.setTransactionSuccessful()
        } finally {
            database.endTransaction()
        }


    }


    @Synchronized
    fun clearQueue() {
        val database = mMusicDatabase.writableDatabase
        try {
            database.delete(PlaybackQueueColumns.NAME, null, null)
            database.setTransactionSuccessful()
        } catch (e: Exception) {
        }
    }

    @Synchronized
    fun saveState(queue: ArrayList<MusicTrack>, history: LinkedList<Int?>?) {
        val database = mMusicDatabase.writableDatabase
        database.beginTransaction()
        try {
            database.delete(PlaybackQueueColumns.NAME, null, null)
            database.delete(PlaybackHistoryColumns.NAME, null, null)
            database.setTransactionSuccessful()
        } finally {
            database.endTransaction()
        }
        val NUM_PROCESS = 20
        var position = 0
        while (position < queue.size) {
            database.beginTransaction()
            try {
                var i = position
                while (i < queue.size && i < position + NUM_PROCESS) {
                    val track = queue[i]
                    val values = ContentValues(2)
                    values.put(PlaybackQueueColumns.TRACK_ID, track.mId)
                    values.put(PlaybackQueueColumns.SOURCE_POSITION, track.mSourcePosition)
                    database.insert(PlaybackQueueColumns.NAME, null, values)
                    i++
                }
                database.setTransactionSuccessful()
            } finally {
                database.endTransaction()
                position += NUM_PROCESS
            }
        }
        if (history != null) {
            val iter: Iterator<Int?> = history.iterator()
            while (iter.hasNext()) {
                database.beginTransaction()
                try {
                    var i = 0
                    while (iter.hasNext() && i < NUM_PROCESS) {
                        val values = ContentValues(1)
                        values.put(PlaybackHistoryColumns.POSITION, iter.next())
                        database.insert(PlaybackHistoryColumns.NAME, null, values)
                        i++
                    }
                    database.setTransactionSuccessful()
                } finally {
                    database.endTransaction()
                }
            }
        }
    }

    fun getQueue(): ArrayList<MusicTrack>? {
        val results = ArrayList<MusicTrack>()
        var cursor: Cursor? = null
        return try {
            cursor = mMusicDatabase.readableDatabase.query(
                PlaybackQueueColumns.NAME, null,
                null, null, null, null, null
            )
            if (cursor != null && cursor.moveToFirst()) {
                results.ensureCapacity(cursor.count)
                do {
                    results.add(MusicTrack(cursor.getLong(0), cursor.getInt(1)))
                } while (cursor.moveToNext())
            }
            results
        } finally {
            if (cursor != null) {
                cursor.close()
                cursor = null
            }
        }
    }

    fun getHistory(playlistSize: Int): LinkedList<Int>? {
        val results = LinkedList<Int>()
        var cursor: Cursor? = null
        return try {
            cursor = mMusicDatabase.readableDatabase.query(
                PlaybackHistoryColumns.NAME, null,
                null, null, null, null, null
            )
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    val pos = cursor.getInt(0)
                    if (pos >= 0 && pos < playlistSize) {
                        results.add(pos)
                    }
                } while (cursor.moveToNext())
            }
            results
        } finally {
            if (cursor != null) {
                cursor.close()
                cursor = null
            }
        }
    }

    class PlaybackQueueColumns {
        companion object {
            const val NAME = "playbacklist"
            const val TRACK_ID = "trackid"
            const val SOURCE_POSITION = "sourceposition"
        }
    }


    class PlaybackHistoryColumns {
        companion object {
            const val NAME = "playbackhistory"
            const val POSITION = "position"
        }
    }


}
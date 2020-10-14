package com.iflytek.kotline.kotlin.musicdemo.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class PreferencesUtility(context: Context) {
    var mContext: Context = context

    companion object {
        const val ARTIST_SORT_ORDER = "artist_sort_order"
        const val ARTIST_SONG_SORT_ORDER = "artist_song_sort_order"
        const val ARTIST_ALBUM_SORT_ORDER = "artist_album_sort_order"
        const val ALBUM_SORT_ORDER = "album_sort_order"
        const val ALBUM_SONG_SORT_ORDER = "album_song_sort_order"
        const val SONG_SORT_ORDER = "song_sort_order"
        private const val NOW_PLAYING_SELECTOR = "now_paying_selector"
        private const val TOGGLE_ANIMATIONS = "toggle_animations"
        private const val TOGGLE_SYSTEM_ANIMATIONS = "toggle_system_animations"
        private const val TOGGLE_ARTIST_GRID = "toggle_artist_grid"
        private const val TOGGLE_ALBUM_GRID = "toggle_album_grid"
        private const val TOGGLE_HEADPHONE_PAUSE = "toggle_headphone_pause"
        private const val THEME_PREFERNCE = "theme_preference"
        private const val START_PAGE_INDEX = "start_page_index"
        private const val START_PAGE_PREFERENCE_LASTOPENED = "start_page_preference_latopened"
        private const val NOW_PLAYNG_THEME_VALUE = "now_playing_theme_value"
        private const val FAVRIATE_MUSIC_PLAYLIST = "favirate_music_playlist"
        private const val DOWNMUSIC_BIT = "down_music_bit"
        private const val CURRENT_DATE = "currentdate"

        private var sInstance: PreferencesUtility? = null

        private var mPreferences: SharedPreferences? = null


        fun getInstance(mContext: Context) :PreferencesUtility{
            if (sInstance==null){
                sInstance= PreferencesUtility(mContext.applicationContext)
            }
            return sInstance as PreferencesUtility
        }



    }

    init {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun setDownMusicBit(bit: Int) {
        val editor = mPreferences!!.edit()
        editor.putInt(DOWNMUSIC_BIT, bit)
        editor.apply()
    }

    fun getDownMusicBit(): Int {
        return mPreferences!!.getInt(DOWNMUSIC_BIT, 192)
    }





}
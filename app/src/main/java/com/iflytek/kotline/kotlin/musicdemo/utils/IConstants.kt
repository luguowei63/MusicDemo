package com.iflytek.kotline.kotlin.musicdemo.utils

class IConstants {

    var MUSIC_COUNT_CHANGED = "com.wm.remusic.musiccountchanged"
    var PLAYLIST_ITEM_MOVED = "com.wm.remusic.mmoved"
    var PLAYLIST_COUNT_CHANGED = "com.wm.remusic.playlistcountchanged"
    var CHANGE_THEME = "com.wm.remusic.themechange"
    var EMPTY_LIST = "com.wm.remusic.emptyplaylist"
    var PACKAGE = "com.wm.remusic"
    var MUSICOVERFLOW = 0
    var ARTISTOVERFLOW = 1
    var ALBUMOVERFLOW = 2
    var FOLDEROVERFLOW = 3

    //歌手和专辑列表点击都会进入MyMusic 此时要传递参数表明是从哪里进入的
    var START_FROM_ARTIST = 1
    var START_FROM_ALBUM = 2
    var START_FROM_LOCAL = 3
    var START_FROM_FOLDER = 4
    var START_FROM_FAVORITE = 5

    var FAV_PLAYLIST = 10


    var NAVIGATE_NOWPLAYING = "navigate_nowplaying"

}
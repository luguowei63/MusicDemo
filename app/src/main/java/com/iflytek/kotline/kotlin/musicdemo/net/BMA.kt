package com.iflytek.kotline.kotlin.musicdemo.net

class BMA {
    companion object {
        val FORMATE = "json"
        val BASE =
            "https://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.6.5.6&format=$FORMATE"
    }

   class Song {
       companion object {

           /**
            * 歌曲基本信息
            */
           fun songBaseInfo(songId: String): String {
               var sb: StringBuffer = StringBuffer(BASE)
               sb.append("&method=").append("baidu.ting.song.baseInfos").append("&song_id=").append(
                   songId
               )
               return sb.toString()
           }

           /**
            * 歌曲信息和下载地址
            *
            * @param songid
            * @return
            */
           fun songInfo(songid: String): String? {
               val sb: StringBuffer = StringBuffer(BASE)
               val str = "songid=" + songid + "&ts=" + System.currentTimeMillis()
               val e: String = AESTools.encrpty(str)
               sb.append("&method=").append("baidu.ting.song.getInfos")
                   .append("&").append(str)
                   .append("&e=").append(e)
               return sb.toString()
           }
       }
   }


}
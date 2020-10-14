package com.iflytek.kotline.kotlin.musicdemo.downmusic

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import com.google.gson.JsonArray
import com.iflytek.kotline.kotlin.musicdemo.App
import com.iflytek.kotline.kotlin.musicdemo.json.MusicFileDownInfo
import com.iflytek.kotline.kotlin.musicdemo.net.BMA
import com.iflytek.kotline.kotlin.musicdemo.net.HttpUtil
import com.iflytek.kotline.kotlin.musicdemo.utils.PreferencesUtility

class Down {


       companion object{
             fun downMusic(context: Context, id: String, name: String, artist: String){
                object: AsyncTask<String, String, MusicFileDownInfo>(){
                    override fun doInBackground(vararg params: String?): MusicFileDownInfo? {
                        try {
                            var jsonArray: JsonArray? =
                                HttpUtil.getResposeJsonObject(BMA.Song.songInfo(id)?.trim())
                                    ?.get("songurl")?.getAsJsonObject()?.get("url")
                                    ?.getAsJsonArray()
                            var len = jsonArray?.size()
                            var downloadBit =
                                PreferencesUtility.getInstance(context).getDownMusicBit()
                            var musicFileDownInfo: MusicFileDownInfo;
                            if (len != null) {
                                for (index in (len - 1)..-1) {
                                    val bit =
                                        jsonArray!![index].asJsonObject["file_bitrate"].toString()
                                            .toInt()
                                    if (bit == downloadBit) {
                                        musicFileDownInfo = App.gsonInstance().fromJson(
                                            jsonArray[index],
                                            MusicFileDownInfo::class.java
                                        )
                                        return musicFileDownInfo
                                    } else if (bit < downloadBit && bit >= 64) {
                                        musicFileDownInfo = App.gsonInstance().fromJson(
                                            jsonArray[index],
                                            MusicFileDownInfo::class.java
                                        )
                                        return musicFileDownInfo
                                    }
                                }
                            }

                        } catch ( e :Exception){
                            e.printStackTrace()
                        }
                        return null
                    }

                    override fun onPostExecute(result: MusicFileDownInfo?) {







                    }

                }







             }
       }

}
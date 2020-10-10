package com.iflytek.kotline.kotlin.musicdemo.downmusic

import android.content.Context
import android.os.AsyncTask
import com.iflytek.kotline.kotlin.musicdemo.json.MusicFileDownInfo

class Down {


       companion object{
             fun downMusic( context: Context,id:String ,name:String ,artist:String){
                object: AsyncTask<String, String, MusicFileDownInfo>(){
                    override fun doInBackground(vararg params: String?): MusicFileDownInfo {
                        TODO("Not yet implemented")
                    }


                }
             }
       }

}
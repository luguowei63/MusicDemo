package com.iflytek.kotline.kotlin.musicdemo.net

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

class HttpUtil {

    companion object{
        val mOkHttpClient :OkHttpClient = OkHttpClient()

        fun getOut(url: String){
            try {
                mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS)
                mOkHttpClient.setReadTimeout(10, TimeUnit.SECONDS)
                var request :Request = Request.Builder().url(url).build()
                var response = mOkHttpClient.newCall(request).execute()
                if (response.isSuccessful) {
                    val fo = FileOutputStream("/storage/emulated/0/" + "gedangein" + ".json")
                    val c = ByteArray(1024)
                    while (response.body().source().read(c) != -1) {
                        fo.write(c)
                    }
                    fo.close()
                }

            }catch (e: Exception){

            }

        }
        fun getResposeJsonObject(action1: String?): JsonObject? {
            try {
                mOkHttpClient.setConnectTimeout(3000, TimeUnit.MINUTES)
                mOkHttpClient.setReadTimeout(3000, TimeUnit.MINUTES)
                val request = Request.Builder()
                    .url(action1) //                    .addHeader("Referer","http://music.163.com/")
                    //                    .addHeader("Cookie", "appver=1.5.0.75771")
                    .build()
                val response = mOkHttpClient.newCall(request).execute()
                if (response.isSuccessful) {
                    val c = response.body().string()
                    //                FileOutputStream fileOutputStream = new FileOutputStream("/sdcard/" + System.currentTimeMillis() + ".txt");
//                fileOutputStream.write(c.getBytes());
//                fileOutputStream.close();
                    val parser = JsonParser()
                    val el = parser.parse(c)
                    return el.asJsonObject
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }

//       mOkHttpClient.setCookieHandler(new CookieManager(
//                new PersistentCookieStore(getContext().getApplicationContext()),
//                CookiePolicy.ACCEPT_ALL));
            return null
        }
    }


}
package com.iflytek.kotline.kotlin.musicdemo.handler

import android.app.Application
import android.os.Looper
import android.widget.Toast
import com.iflytek.kotline.kotlin.musicdemo.App
import java.io.File
import java.io.PrintWriter

class UnceHandler(application: Application) : Thread.UncaughtExceptionHandler {

    lateinit var mDefaultHandler: Thread.UncaughtExceptionHandler
    val TAG: String = "CatchExcep"
    lateinit var application: App

    override fun uncaughtException(t: Thread, e: Throwable) {
    }


    /**
     * 自定义错误处理，收集错误信息，发送错误报告等操作均在此完成
     */
    fun handleException(ex: Throwable): Boolean {
        //使用Toast来显示异常
       Thread{
           Looper.prepare()
           Toast.makeText(application.applicationContext, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_SHORT)
               .show()
           Looper.loop()

       }.start()


        var file: File= File(application.cacheDir.absolutePath + "/err/" + System.currentTimeMillis() + ".log")
          if (!file.exists()){
              file.mkdir()
          }
        try {
        var writer:PrintWriter = PrintWriter(application.cacheDir.absolutePath + "/err/" + System.currentTimeMillis() + ".log")
            ex.printStackTrace(writer)
            writer.close()

        }catch (e : Exception ){
            e.printStackTrace()
        }
       return true

    }


}
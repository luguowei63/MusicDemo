package com.iflytek.kotline.kotlin.musicdemo

import android.app.Application
import android.content.Context
import com.iflytek.kotline.kotlin.musicdemo.utils.ThemeUtils

class App : Application(), ThemeUtils.SwitchColor {
    companion object {
        lateinit var context: Context
        val MAX_MEM: Int = (Runtime.getRuntime().maxMemory() / 4).toInt()
    }


    override fun onCreate() {
        super.onCreate()
    }

    override fun replaceColorById(context: Context, colorId: Int): Int {
        TODO("Not yet implemented")
    }

    override fun replaceColor(context: Context, color: Int): Int {
        TODO("Not yet implemented")
    }
}
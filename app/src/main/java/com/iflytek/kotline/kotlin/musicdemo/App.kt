package com.iflytek.kotline.kotlin.musicdemo

import android.app.Application
import android.content.Context
import androidx.annotation.ColorRes
import com.facebook.common.internal.Supplier
import com.facebook.imagepipeline.cache.MemoryCacheParams
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.google.gson.Gson

import com.iflytek.kotline.kotlin.musicdemo.utils.ThemeHelper
import com.iflytek.kotline.kotlin.musicdemo.utils.ThemeUtils

class App : Application(), ThemeUtils.SwitchColor {
    companion object {
        lateinit var context: Context
        val MAX_MEM: Int = (Runtime.getRuntime().maxMemory() / 4).toInt()
        lateinit  var gson : Gson

        fun gsonInstance() :Gson {
            if (gson==null){
                gson= Gson()
            }
            return gson
        }
    }

    fun getConfigureCaches(context: Context) :ImagePipelineConfig{
        val bitmapCacheParams : MemoryCacheParams = MemoryCacheParams(
            MAX_MEM,// 内存缓存中总图片的最大大小,以字节为单位。
            Int.MAX_VALUE,// 内存缓存中图片的最大数量。
            // 内存缓存中图片的最大数量。
            MAX_MEM,// 内存缓存中准备清除但尚未被删除的总图片的最大大小,以字节为单位。
            Int.MAX_VALUE,// 内存缓存中准备清除的总图片的最大数量。
            // 内存缓存中准备清除的总图片的最大数量。
            Int.MAX_VALUE / 10
        )
        val mSupplierMemoryCacheParams: Supplier<MemoryCacheParams> = Supplier { bitmapCacheParams }

    }









    override fun onLowMemory() {
        super.onLowMemory()







    }




    override fun onCreate() {
        super.onCreate()
    }

    override fun replaceColorById(context: Context, @ColorRes colorId: Int): Int {
        if (ThemeHelper.isDefaultTheme(context)) {
            return context?.resources.getColor(colorId)
        }
        var theme: String =

    }


    fun getTheme(context: Context): String? {
        when (ThemeHelper.getTheme(context)) {
            ThemeHelper.CARD_STORM -> return "blue"
            ThemeHelper.CARD_HOPE -> return "purple"
            ThemeHelper.CARD_WOOD -> return "green"
            ThemeHelper.CARD_LIGHT -> return "green_light"
            ThemeHelper.CARD_THUNDER -> return "yellow"
            ThemeHelper.CARD_SAND -> return "orange"
            ThemeHelper.CARD_FIREY -> return "red"

        }
        return null
    }


    @ColorRes
    override fun replaceColor(context: Context, @ColorRes originColor: Int): Int {
        if(ThemeHelper.isDefaultTheme(context))
            return originColor
        var theme =getTheme(context)
        var colorId =-1
        if (theme != null) {
            colorId = getThemeColor(context, originColor, theme)
        }
        return if (colorId != -1) resources.getColor(colorId) else originColor

    }

    @ColorRes
    private fun getThemeColorId(context: Context, colorId: Int, theme: String): Int {
        when (colorId) {
            R.color.theme_color_primary -> return context.resources.getIdentifier(
                theme, "color",
                packageName
            )
            R.color.theme_color_primary_dark -> return context.resources.getIdentifier(
                theme + "_dark", "color",
                packageName
            )
            R.color.playbarProgressColor -> return context.resources.getIdentifier(
                theme + "_trans", "color",
                packageName
            )
        }
        return colorId
    }

    @ColorRes
    private fun getThemeColor(context: Context, color: Int, theme: String): Int {
        when (color) {
            0xd20000 -> return context.resources.getIdentifier(theme, "color", packageName)
        }
        return -1
    }





}
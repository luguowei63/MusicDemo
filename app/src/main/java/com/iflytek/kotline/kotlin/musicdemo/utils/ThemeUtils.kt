package com.iflytek.kotline.kotlin.musicdemo.utils

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.graphics.drawable.DrawableCompat

class ThemeUtils {
    val TL_TYPED_VALUE: ThreadLocal<TypedValue> = ThreadLocal()
    val DISABLED_STATE_SET = intArrayOf(R.attr.state_enabled)
    val ENABLED_STATE_SET = intArrayOf(R.attr.state_enabled)
    val FOCUSED_STATE_SET = intArrayOf(R.attr.state_focused)
    val ACTIVATED_STATE_SET = intArrayOf(R.attr.state_activated)
    val PRESSED_STATE_SET = intArrayOf(R.attr.state_pressed)
    val CHECKED_STATE_SET = intArrayOf(R.attr.state_checked)
    val SELECTED_STATE_SET = intArrayOf(R.attr.state_selected)
    val EMPTY_STATE_SET = IntArray(0)

    private val TEMP_ARRAY = IntArray(1)


    fun tintDrawable(drawable: Drawable?, color: Int, mode: PorterDuff.Mode): Drawable? {
        if (drawable == null) return null
        var wrapper: Drawable = DrawableCompat.wrap(drawable.mutate())
        DrawableCompat.setTint(wrapper, color)
        DrawableCompat.setTintMode(drawable, mode)
        return wrapper
    }


    interface SwitchColor {
        @ColorInt
        fun replaceColorById(context: Context, @ColorRes colorId: Int): Int


        @ColorInt
        fun replaceColor(context: Context,@ColorInt color:Int) :Int


    }


}
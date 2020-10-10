package com.iflytek.kotline.kotlin.musicdemo.handler

import android.content.Context
import android.os.Handler
import java.lang.ref.WeakReference

class HandlerUtil(context: Context): Handler() {
    private var instance: HandlerUtil? = null
    var mActivityReference: WeakReference<Context>? = null

    init {
        mActivityReference = WeakReference(context)

    }
    fun getInstance(context: Context): HandlerUtil? {
        if (instance == null) {
            instance = HandlerUtil(context.applicationContext)
        }
        return instance
    }


}
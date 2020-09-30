package com.iflytek.kotline.kotlin.musicdemo.view

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity


 class AbsActivity: AppCompatActivity() ,ServiceConnection{
    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        TODO("Not yet implemented")
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        TODO("Not yet implemented")
    }


}
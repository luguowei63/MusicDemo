package com.iflytek.kotline.kotlin.musicdemo.downmusic

import android.app.Service
import android.content.Intent
import android.os.IBinder

class DownService : Service() {

    companion object{
        const val ADD_DOWNTASK = "com.wm.remusic.downtaskadd"
        const val ADD_MULTI_DOWNTASK = "com.wm.remusic.multidowntaskadd"
        const val CANCLE_DOWNTASK = "com.wm.remusic.cacletask"
        const val CANCLE_ALL_DOWNTASK = "com.wm.remusic.caclealltask"
        const val START_ALL_DOWNTASK = "com.wm.remusic.startalltask"
        const val RESUME_START_DOWNTASK = "com.wm.remusic.resumestarttask"
        const val PAUSE_TASK = "com.wm.remusic.pausetask"
        const val PAUSE_ALLTASK = "com.wm.remusic.pausealltask"
        const val UPDATE_DOWNSTAUS = "com.wm.remusic.updatedown"
        const val TASK_STARTDOWN = "com.wm.remusic.taskstart"
        const val TASKS_CHANGED = "com.wm.remusic.taskchanges"
    }







    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}
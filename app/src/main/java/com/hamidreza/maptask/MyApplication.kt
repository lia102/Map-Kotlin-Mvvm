package com.hamidreza.maptask

import android.app.Application
import com.hamidreza.maptask.utils.Conts.MAP_BOX_TOKEN
import dagger.hilt.android.HiltAndroidApp
import ir.map.sdk_map.Mapir

@HiltAndroidApp
class MyApplication : Application() {
    @Suppress("DEPRECATION")
    override fun onCreate() {
        super.onCreate()
        Mapir.getInstance(this,MAP_BOX_TOKEN)
    }
}
package com.example.pinterest

import android.app.Application


class MyApplication : Application() {
    companion object{
        val TAG = MyApplication::class.java.simpleName
        var instance: MyApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
package com.lcz.cpa

import android.content.Context
import androidx.startup.Initializer
import timber.log.Timber

/**
 *
 * desc: https://developer.android.com/topic/libraries/app-startup
 * App Startup 允许您定义共享单个内容提供程序的组件初始化程序，
 * 而不是为您需要初始化的每个组件定义单独的内容提供程序。这可以显着缩短应用程序启动时间。
 *
 * create by lcz on 2023-03-28
 */
class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("TimberInitializer is initialized.")
        }

    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
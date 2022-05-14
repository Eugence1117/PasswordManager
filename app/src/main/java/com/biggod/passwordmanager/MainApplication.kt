package com.biggod.passwordmanager

import android.app.Application
import android.content.Context

class MainApplication : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        ThemeSelector.checkTheme(applicationContext)
    }

}
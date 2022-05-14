package com.biggod.passwordmanager

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager

class ThemeSelector {
    companion object{
        fun checkTheme(context:Context){
            try {
                val pref = PreferenceManager.getDefaultSharedPreferences(context)
                if(pref.contains(Constant.DARK_MODE)){
                    var isDark = pref.getBoolean(Constant.DARK_MODE,false)
                    if(isDark){
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                    else{
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
            }catch(e:Exception){
                Log.e(javaClass.name,e.stackTraceToString())
            }
        }
    }
}
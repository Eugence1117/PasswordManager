package com.biggod.passwordmanager

import android.util.Log
import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class AccountViewModel: ViewModel(){

    val id = UUID.randomUUID().toString()
    val platform = MutableLiveData<String>()
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val authenticator = MutableLiveData<Boolean>()

    fun onSubmit(){
        Log.i("LOG",platform.value.toString())
    }

    fun toMap():Map<String,Any?>{
        return mapOf(
            "id" to id,
            "platform" to platform.value,
            "username" to username.value,
            "password" to password.value,
            "description" to description.value,
            "authenticator" to authenticator.value
        )
    }
}
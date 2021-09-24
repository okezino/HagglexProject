package com.example.hagglexproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {



    private var _id : MutableLiveData<String> = MutableLiveData()
    val id : LiveData<String>
        get() = _id

    private var _userName : MutableLiveData<String> = MutableLiveData("SV")
    val userName : LiveData<String>
        get() = _userName

    private var _loginToken : MutableLiveData<String> = MutableLiveData()
    val loginToken : LiveData<String>
        get() = _loginToken

    private var _registerToken : MutableLiveData<String> = MutableLiveData()
    val registerToken : LiveData<String>
        get() = _registerToken



    fun updateId(id : String){
        _id.value = id
    }


    fun updateLoginToken(token: String){
        _loginToken.value = token
    }

    fun updateRegistrationToken(token:String){
        _registerToken.value = token
    }

    fun updateUserName(userName:String){
        _userName.value = userName
    }

}
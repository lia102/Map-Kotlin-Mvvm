package com.hamidreza.maptask.utils

sealed class ResultWrapper<out R>{
    data class Success<T>(val data:T) : ResultWrapper<T>()
    data class Error(val msg:String) : ResultWrapper<Nothing>()
}

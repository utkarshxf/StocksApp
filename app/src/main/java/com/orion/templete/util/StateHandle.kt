package com.orion.newsapp.util

sealed  class StateHandle<T>(val data:T?=null, val message:String?=null){

    class  Success<T>(data:T?): StateHandle<T>(data = data)

    class Loading<T>(message: String?) : StateHandle<T>()

    class Error<T>(message:String?) : StateHandle<T>(message= message)

}
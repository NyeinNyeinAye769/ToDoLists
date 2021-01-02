package com.example.todolists.data

sealed class MyResult<out R> {

    data class Success<out T>(val data: T) : MyResult<T>()
    data class Error(val exception: Exception) : MyResult<Nothing>()
    object Loading : MyResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

///**
// * `true` if [Result] is of type [Success] & holds non-null [Success.data].
// */
//val Result<*>.succeeded
//    get() = this is MyResult.Success<*> && data != null
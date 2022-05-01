package com.example.model.result

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Resource<out R>(
    val data: R? = null,
    val error: Throwable? = null
) {

    class Success<out T>(data: T) : Resource<T>(data)
    class Loading<out T>(data: T? = null) : Resource<T>(data)
    class Error(throwable: Throwable) : Resource<Nothing>(error = throwable)

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data]"
            is Error -> "Error[exception=$error]"
            is Loading -> "Loading"
            else -> "Not found type: ${this.javaClass.simpleName}"
        }
    }

    companion object {
        fun <T> success(data: T): Resource<T> = Success(data)
        fun <T> loading(data: T? = null): Resource<T> = Loading(data)
        fun <T> error(throwable: Throwable): Resource<T> = Error(throwable)
    }

}

val <T> Resource<T>.isSuccess
    get() = this is Resource.Success && data != null
package com.example.scratchcard.data

sealed class ApiResult<T> {

    abstract val data: T?

    companion object {
        public fun <T> created(data: T? = null): Created<T> {
            return Created(data)
        }

        fun <T> loading(data: T? = null): Loading<T> {
            return Loading(data)
        }

        fun <T> fail(data: T? = null, error: String): Fail<T> {
            return Fail(data, error)
        }

        fun <T> success(data: T): Success<T> {
            return Success(data = data)
        }
    }

    data class Created<T> internal constructor(public override val data: T?) :
        ApiResult<T>()

    data class Loading<T> internal constructor(
        override val data: T?
    ) : ApiResult<T>()

    data class Fail<T> internal constructor(
        override val data: T?,
        val error: String
    ) : ApiResult<T>()

    data class Success<T> internal constructor(
        override val data: T,
    ) : ApiResult<T>()
}

package com.example.scratchcard.network

import com.example.scratchcard.data.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

abstract class NetworkObserver<Inp : Any, Ext : Any, Dom : Any>(
    protected val converter: (Ext) -> Dom,
    private val apiCall: suspend (Inp) -> Ext,
) {

    protected open val initialValue: ApiResult<Dom> = ApiResult.created()

    private val values: MutableStateFlow<ApiResult<Dom>> by lazy { MutableStateFlow(initialValue) }
    fun observe(): Flow<ApiResult<Dom>> = values

    suspend fun request(
        input: Inp,
    ) {
        values.emit(
            ApiResult.loading(data = null)
        )
        try {
            apiCall(input).let { result ->
                values.emit(ApiResult.success(converter(result)))
            }
        } catch (e: Throwable) {
            values.emit(ApiResult.fail(null, ""))
        }
    }
}

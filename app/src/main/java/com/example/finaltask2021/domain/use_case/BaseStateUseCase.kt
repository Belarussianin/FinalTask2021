package com.example.finaltask2021.domain.use_case

import com.example.finaltask2021.common.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseStateUseCase<Type, in Params> where Type : Any? {

    abstract suspend fun run(params: Params): Type

    fun execute(params: Params): Flow<out DataState<Type>> = flow {
        emit(DataState.Loading())
        try {
            emit(DataState.Ready(run(params)))
        } catch (ex: Exception) {
            emit(
                DataState.Error(
                    ex.cause?.localizedMessage ?: ex.cause?.message
                    ?: ex.localizedMessage ?: ex.message ?: "Unexpected error"
                )
            )
        }
    }
}
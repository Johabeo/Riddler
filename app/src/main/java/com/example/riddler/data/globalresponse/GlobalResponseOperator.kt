package com.example.riddler.data.globalresponse

import android.app.Application
import android.widget.Toast
import com.example.riddler.data.mapper.ErrorResponseMapper
import com.example.riddler.data.model.QuizErrorResponse
import com.skydoves.sandwich.*
import com.skydoves.sandwich.operators.ApiResponseSuspendOperator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class GlobalResponseOperator<T> constructor(
    private val application: Application
) : ApiResponseSuspendOperator<T>() {

    // The body is empty, because we will handle the success case manually.
    override suspend fun onSuccess(apiResponse: ApiResponse.Success<T>) { }

    // handles error cases when the API request gets an error response.
    // e.g., internal server error.
    override suspend fun onError(apiResponse: ApiResponse.Failure.Error<T>) {
        withContext(Dispatchers.Main) {
            apiResponse.run {
                Timber.d(message())

                // handling error based on status code.
                when (statusCode) {
                    StatusCode.InternalServerError -> toast("InternalServerError")
                    StatusCode.BadGateway -> toast("BadGateway")
                    else -> toast("$statusCode(${statusCode.code}): ${message()}")
                }

                // map the ApiResponse.Failure.Error to a customized error model using the mapper.
                map(ErrorResponseMapper) {
                    Timber.d(message())
                }
            }
        }
    }

    // handles exceptional cases when the API request gets an exception response.
    // e.g., network connection error, timeout.
    override suspend fun onException(apiResponse: ApiResponse.Failure.Exception<T>) {
        withContext(Dispatchers.Main) {
            apiResponse.run {
                Timber.d(message())
                toast(message())
            }
        }
    }

    private fun toast(message: String) {
        Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
    }



}
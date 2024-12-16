package com.romakost.core.network.adaptors

import com.romakost.core.network.NetworkResult
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

/**
 * A custom Retrofit `Call` implementation that wraps the API response in a `NetworkResult` type.
 *
 * The `ResultCall` class intercepts the API response to handle success, error, or exception cases
 * uniformly. It uses the delegate pattern to work with the original Retrofit `Call<T>` object
 * and transforms its results into a `NetworkResult<T>` for cleaner API response handling.
 *
 * @param T The type of the API response body.
 * @property delegate The original Retrofit `Call<T>` instance.
 */

class ResultCall<T : Any>(
    private val delegate: Call<T>
) : Call<NetworkResult<T>> {

    /**
     *  enqueue(callback: Callback<NetworkResult<T>>)**:
     * - Asynchronously executes the API call.
     * - Wraps the response in a `NetworkResult` and forwards it to the provided callback.
     * - Handles HTTP errors and unexpected exceptions (e.g., timeouts, network issues).
     *
     */
    override fun enqueue(callback: Callback<NetworkResult<T>>) {
        delegate.enqueue(
            object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val networkResponse = try {
                        val body = response.body()
                        if (response.isSuccessful && body != null) {
                            NetworkResult.Success(body)
                        } else {
                            NetworkResult.Error(
                                code = response.code(),
                                message = response.message()
                            )
                        }
                    } catch (e: HttpException) {
                        NetworkResult.Error(code = e.code(), message = e.message())
                    } catch (e: Throwable) {
                        NetworkResult.Exception(e)
                    }

                    callback.onResponse(this@ResultCall, Response.success(networkResponse))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    callback.onResponse(
                        this@ResultCall,
                        Response.success(NetworkResult.Exception(t))
                    )
                }
            }
        )
    }

    override fun isExecuted() = delegate.isExecuted

    override fun execute(): Response<NetworkResult<T>> = Response.success(
        NetworkResult.Success(
            delegate.execute().body()!!
        )
    )

    override fun cancel() = delegate.cancel()

    override fun isCanceled() = delegate.isCanceled

    override fun clone(): Call<NetworkResult<T>> = ResultCall(delegate.clone())

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

}
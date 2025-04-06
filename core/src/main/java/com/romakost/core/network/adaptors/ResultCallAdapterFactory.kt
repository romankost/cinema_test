package com.romakost.core.network.adaptors

import com.romakost.core.network.NetworkResult
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * A custom Retrofit CallAdapter.Factory implementation to convert API responses into
 * a sealed class result type `NetworkResult<T>` for handling success and error cases uniformly.
 *
 * This factory and its associated adapter (`NetworkResultCallAdapter`) ensure that:
 * - API responses are wrapped in a `NetworkResult` type.
 * - Developers can handle success, error, or loading states in a clean and consistent manner.
 *
 * Classes Explanation:
 *
 * 1. **ResultCallAdapterFactory**:
 *    - A factory for creating `NetworkResultCallAdapter` instances.
 *    - Verifies that the return type of a Retrofit service method is of the form `Call<NetworkResult<T>>`.
 *    - Extracts the type `T` from `NetworkResult<T>` to pass to the adapter.
 *
 * 2. **NetworkResultCallAdapter**:
 *    - A Retrofit `CallAdapter` that adapts the original Retrofit `Call<T>` into a `Call<NetworkResult<T>>`.
 *    - Ensures that API responses are wrapped in the `NetworkResult` type for easier error and success handling.
 *
 * Key Points:
 * - The `get` method in `ResultCallAdapterFactory` performs type checks to validate
 *   that the return type matches the expected structure (`Call<NetworkResult<T>>`).
 * - The `NetworkResultCallAdapter` processes the response and wraps it in `NetworkResult`.
 *
 * Usage:
 * 1. Add the factory to Retrofit during its initialization:
 *    ```
 *    val retrofit = Retrofit.Builder()
 *        .baseUrl(BASE_URL)
 *        .addCallAdapterFactory(ResultCallAdapterFactory.create())
 *        .build()
 *    ```
 *
 * 2. Define your API service interface with methods returning `Call<NetworkResult<T>>`:
 *    ```
 *    interface ApiService {
 *        @GET("endpoint")
 *        fun fetchData(): Call<NetworkResult<MyData>>
 *    }
 *    ```
 */

class ResultCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) {
            return null
        }

        val callType = getParameterUpperBound(0, returnType as ParameterizedType)
        if (getRawType(callType) != NetworkResult::class.java) {
            return null
        }

        val resultType = getParameterUpperBound(0, callType as ParameterizedType)
        return NetworkResultCallAdapter(resultType)
    }

    companion object {
        fun create(): ResultCallAdapterFactory = ResultCallAdapterFactory()
    }
}

class NetworkResultCallAdapter(
    private val resultType: Type
) : CallAdapter<Type, Call<NetworkResult<Type>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>): Call<NetworkResult<Type>> {
        return ResultCall(call)
    }
}

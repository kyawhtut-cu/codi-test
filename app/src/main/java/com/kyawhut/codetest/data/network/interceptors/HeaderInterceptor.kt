package com.kyawhut.codetest.data.network.interceptors

import com.kyawhut.codetest.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * @author kyawhtut
 * @date 10/16/21
 */
class HeaderInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + BuildConfig.API_BEARER_TOKEN)
                .build()
        )
    }
}

package com.example.hagglexproject


import android.os.Looper
import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.subscription.WebSocketSubscriptionTransport
import com.example.hagglexproject.ui.CreateAccountPage
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

private var instance : ApolloClient? = null

fun apolloClient(authorizationHeader : String = " "): ApolloClient{

    check(Looper.myLooper() == Looper.getMainLooper()){
        "Only the main thread can get apolloClient instance"
    }

    var okHttpClient = OkHttpClient.Builder()
       .addInterceptor(AuthorizationInterceptor(authorizationHeader))
        .build()

    instance?.let { return it }

    instance = ApolloClient.builder()
        .serverUrl("https://api-staging.hagglex.com/graphql")
        .okHttpClient(okHttpClient)
        .build()

    return instance!!


}

   class AuthorizationInterceptor(val string : String) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${CreateAccountPage.userToken}")
            .build()

        return chain.proceed(request)
    }

       init {
           Log.d("Authorization", "Bearer $string")
       }

}
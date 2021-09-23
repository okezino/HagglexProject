package com.example.hagglexproject

import android.content.Context
import android.os.Looper
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.subscription.WebSocketSubscriptionTransport
import okhttp3.OkHttpClient

private var instance : ApolloClient? = null

fun apolloClient(context: Context): ApolloClient{

    check(Looper.myLooper() == Looper.getMainLooper()){
        "Only the main thread can get apolloClient instance"
    }

    var okHttpClient = OkHttpClient.Builder()
       // .addInterceptor()

    instance?.let { return it }

    instance = ApolloClient.builder()
        .serverUrl("https://api-staging.hagglex.com/graphql")
        .build()

    return instance!!


}
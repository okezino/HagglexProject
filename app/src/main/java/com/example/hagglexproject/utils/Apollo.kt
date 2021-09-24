package com.example.hagglexproject


import android.app.Activity
import android.os.Looper
import android.util.Log
import android.view.View
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.subscription.WebSocketSubscriptionTransport
import com.example.hagglexproject.ui.CreateAccountPage
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import com.google.android.material.snackbar.Snackbar
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

fun showSnackbar(view : View, message : String){
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

fun emailValidator(email: String) : Boolean{

    val regex = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    return email.matches(regex)
}

fun validateRegisteredField(email: String, password: String, userName : String, number : String) : String{
    var result = "success"

    if(number.isEmpty()) result  = "Phone number"

    if(userName.isEmpty()) result = "userName"

    if(password.isEmpty() ) result = "Password"

    if(email.isEmpty() || !emailValidator(email)) result = "Email"

    return result

}

fun showErrorMessage(view: View , string: String){
    if(string == "Email"){
        Snackbar.make(view, "$string is Empty or Invalid $string", Snackbar.LENGTH_SHORT).show()
    }else {
        Snackbar.make(view, "$string is Empty", Snackbar.LENGTH_SHORT).show()
    }

}

fun generateMaterialDialog(
    context:Activity, title : String,message:String,positiveBtnTitle :String,negativeBtnTitle:String = " ",
    positiveAction : (() -> Unit)?,negativeAction : (()->Unit)
){
    MaterialAlertDialogBuilder(context)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveBtnTitle){dialogInterface, _ ->
            dialogInterface.dismiss()
            positiveAction?.invoke()
        }.setNegativeButton(negativeBtnTitle){ dialogInterface, _->
            dialogInterface.dismiss()
            negativeAction?.invoke()
        }.setCancelable(true)
        .show()

}
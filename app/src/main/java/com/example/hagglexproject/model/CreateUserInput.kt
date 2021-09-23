package com.example.hagglexproject.model

data class CreateUserInput(
    val country: String,
    val currency: String,
    val email: String,
    val password: String,
    val phoneNumberDetails: PhoneNumberDetails,
    val phonenumber: String,
    val referralCode: String? = null,
    val username: String
)
package com.example.hagglexproject.model

data class PhoneNumberDetails(
    val callingCode: String,
    val flag: String = "NGN",
    val phoneNumber: String
)
package com.example.assignment07mariamichakhvadze

interface CustomCallback {
    fun onSuccess(response: String) {}
    fun onFailed(errorMessage: String) {}
}
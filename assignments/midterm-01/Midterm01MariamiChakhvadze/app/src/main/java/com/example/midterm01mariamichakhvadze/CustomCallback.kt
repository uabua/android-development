package com.example.midterm01mariamichakhvadze

interface CustomCallback {
    fun onSuccess(response: String) {}
    fun onFailed(errorMessage: String) {}
}
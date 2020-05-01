package com.example.assignment04mariamichakhvadze

interface CustomCallback {
    fun onSuccess(response: String) {}
    fun onFailed(errorMessage: String) {}
}
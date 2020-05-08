package com.example.assignment05mariamichakhvadze

interface CustomCallback {
    fun onSuccess(response: String) {}
    fun onFailed(errorMessage: String) {}
}
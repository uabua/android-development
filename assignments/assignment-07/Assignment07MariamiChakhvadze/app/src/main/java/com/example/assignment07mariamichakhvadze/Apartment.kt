package com.example.assignment07mariamichakhvadze

import com.google.gson.annotations.SerializedName

data class Apartment(
    @SerializedName("titleEN")
    val title: String,
    @SerializedName("descriptionEN")
    val description: String,
    val cover: String
)


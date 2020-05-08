package com.example.assignment05mariamichakhvadze

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        init()
    }

    private fun init() {
        val intent = intent
        val user = intent.getParcelableExtra<UserModel.Data>("user")
        intent.removeExtra("user")

        Glide.with(this).load(user?.avatar).into(avatarImageView)

        userFullNameTextView.text = String.format("%s %s", user?.firstName, user?.lastName)
        emailTextView.text = user?.email
    }
}

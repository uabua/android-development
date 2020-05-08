package com.example.assignment05mariamichakhvadze

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var users = mutableListOf<UserModel.Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {

        DataLoader.getRequest("users", object : CustomCallback {
            override fun onSuccess(response: String) {
                val userModel = Gson().fromJson(response, UserModel::class.java)

                users.addAll(userModel.data)

                viewPager.adapter = ViewPagerAdapter(supportFragmentManager, users, this@MainActivity)

                viewPager.offscreenPageLimit = 6
            }
        })
    }


    fun openUserDetailsPage(id: Int) {
        val intent = Intent(this, UserDetailsActivity::class.java)

        for (user in users) {
            if (user.id == id) {
                DataLoader.getRequest("users", id.toString(), object : CustomCallback {
                    override fun onSuccess(response: String) {
                        val userModel = Gson().fromJson(response, UserModel.User::class.java)
                        intent.putExtra("user", userModel.data)
                        startActivity(intent)
                    }
                })

                break
            }
        }
    }
}

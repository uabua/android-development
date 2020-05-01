package com.example.assignment04mariamichakhvadze

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserRecyclerViewAdapter
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
                userRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = UserRecyclerViewAdapter(users, this@MainActivity)
                userRecyclerView.adapter = adapter
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

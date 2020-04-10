package com.example.assignment03mariamichakhvadze

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    val items = ArrayList<ItemModel>()
    private lateinit var adapter: RecyclerViewAdapter
    private val REQUEST_CODE = 21

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setItems()
        init()
    }

    private fun init() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerViewAdapter(items, this)
        recyclerView.adapter = adapter

        addItemButton.setOnClickListener {
            openSecondActivity()
        }
    }

    private fun setItems() {
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

        items.add((ItemModel(R.drawable.avatar, "Bunny", "Bunny wants to say 'Hello!'", date)))
        items.add((ItemModel(R.drawable.avatar, "Bunny2", "Bunny wants to say 'Hello!'", date)))
        items.add((ItemModel(R.drawable.avatar, "Bunny3", "Bunny wants to say 'Hello!'", date)))
        items.add((ItemModel(R.drawable.avatar, "Bunny4", "Bunny wants to say 'Hello!'", date)))
        items.add((ItemModel(R.drawable.avatar, "Bunny5", "Bunny wants to say 'Hello!'", date)))
    }

    private fun openSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            val userModel = data?.extras?.getParcelable<UserModel>("userModel")

            items.add(
                0,
                ItemModel(userModel!!.image, userModel.title, userModel.description, userModel.date)
            )

            adapter.notifyItemInserted(0)
            recyclerView.scrollToPosition(0)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}

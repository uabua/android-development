package com.example.assignment07mariamichakhvadze

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment07mariamichakhvadze.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: RecyclerViewAdapter
    private var apartments = ArrayList<Apartment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        ) as ActivityMainBinding

        init()
    }

    private fun init() {
        setData()
    }

    private fun setData() {
        DataLoader.getRequest(
            "5edb4d643200002a005d26f0", object : CustomCallback {
                override fun onSuccess(response: String) {
                    val data = Gson().fromJson(response, Array<Apartment>::class.java).toList()

                    apartments.addAll(data)

                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = RecyclerViewAdapter(apartments)
                    recyclerView.adapter = adapter

                }

                override fun onFailed(errorMessage: String) {
                    d("error", errorMessage)
                }

            }
        )
    }
}
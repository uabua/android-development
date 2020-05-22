package com.example.midterm01mariamichakhvadze

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var adapter: RecyclerViewAdapter
    private var data = mutableListOf<CharacterModel.Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        init()
    }

    private fun init() {
        setData()
        authStatus()
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun authStatus() {
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Already logged in", Toast.LENGTH_LONG).show()
        }
    }

    private fun setData() {
        DataLoader.getRequest("character", object : CustomCallback {
            override fun onSuccess(response: String) {
                val characterModel = Gson().fromJson(response, CharacterModel::class.java)

                data.addAll(characterModel.results)

                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = RecyclerViewAdapter(data, this@MainActivity)
                recyclerView.adapter = adapter
            }

            override fun onFailed(errorMessage: String) {
                d("error", errorMessage)
            }
        })
    }

    fun openCharacterDetails(id: Int) {
        val intent = Intent(this, CharacterDetailsActivity::class.java)

        for (character in data) {
            if (character.id == id) {
                DataLoader.getRequest(String.format("character/%d", id), object : CustomCallback {
                    override fun onSuccess(response: String) {
                        val characterModel =
                            Gson().fromJson(response, CharacterModel.Data::class.java)
                        intent.putExtra("character", characterModel)
                        startActivity(intent)
                    }

                    override fun onFailed(errorMessage: String) {
                        d("error", errorMessage)
                    }
                })

                break
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemView = item.itemId

        when (itemView) {
            R.id.logout -> logout()
        }

        return false
    }
}

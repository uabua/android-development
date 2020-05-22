package com.example.midterm01mariamichakhvadze

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_character_details.*

class CharacterDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)
        setSupportActionBar(mainCharacterToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        init()
    }

    private fun init() {
        val intent = intent
        val character = intent.getParcelableExtra<CharacterModel.Data>("character")
        intent.removeExtra("character")

        Glide.with(this).load(character?.image).into(characterImageView)

        characterNameTextView.text = String.format("Name: %s", character?.name)
        characterStatusTextView.text = String.format("Status: %s", character?.status)
        characterSpeciesTextView.text = String.format("Species: %s", character?.species)
        characterTypeTextView.text = String.format("Type: %s", character?.type)
        characterGenderTextView.text = String.format("Gender: %s", character?.gender)

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

    private fun logout() {
        FirebaseAuth.getInstance().signOut()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}

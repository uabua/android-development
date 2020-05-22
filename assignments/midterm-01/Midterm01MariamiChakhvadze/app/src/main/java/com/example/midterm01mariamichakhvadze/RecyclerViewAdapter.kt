package com.example.midterm01mariamichakhvadze

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.character_recyclerview_layout.view.*

class RecyclerViewAdapter(
    private val characters: MutableList<CharacterModel.Data>,
    private val activity: MainActivity
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.character_recyclerview_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var character: CharacterModel.Data

        fun onBind() {
            character = characters[adapterPosition]

            itemView.nameTextView.text = character.name
            Glide.with(activity).load(character.image).into(itemView.avatarImageView)

            itemView.setOnClickListener {
                activity.openCharacterDetails(character.id)
            }
        }
    }

}
package com.example.assignment07mariamichakhvadze

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment07mariamichakhvadze.databinding.ApartmentRecyclerviewLayoutBinding

class RecyclerViewAdapter(
    private val apartments: ArrayList<Apartment>
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ApartmentRecyclerviewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            binding.apartment = apartments[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ApartmentRecyclerviewLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = apartments.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }
}
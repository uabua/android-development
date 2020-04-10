package com.example.assignment03mariamichakhvadze

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recyclerview_layout.view.*

class RecyclerViewAdapter(
    private val items: ArrayList<ItemModel>,
    private val activity: MainActivity
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var model: ItemModel

        fun onBind() {
            model = items[adapterPosition]
            itemView.imageView.setImageResource(model.image)
            itemView.titleTextView.text = model.title
            itemView.descriptionTextView.text = model.description
            itemView.dateTextView.text = model.date

            itemView.setOnLongClickListener {
                activity.items.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_recyclerview_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }
}
package com.example.assignment05mariamichakhvadze

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_user.view.*

class UserFragment(private val activity: MainActivity) : Fragment() {

    private lateinit var itemView: View
    lateinit var model: UserModel.Data

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        itemView = inflater.inflate(R.layout.fragment_user, container, false)
        init()
        return itemView
    }

    private fun init() {
        Glide.with(this).load(model.avatar).into(itemView.avatar)
        itemView.fullNameTextView.text = String.format("%s %s", model.firstName, model.lastName)

        itemView.avatar.setOnClickListener {
            activity.openUserDetailsPage(model.id)
        }
    }

}

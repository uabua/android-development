package com.example.assignment05mariamichakhvadze

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(
    fm: FragmentManager, private val users: MutableList<UserModel.Data>,
    private val activity: MainActivity
) :
    FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        val fragment = UserFragment(activity)
        fragment.model = users[position]
        return fragment
    }

    override fun getCount() = users.size
}
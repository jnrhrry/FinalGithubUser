package com.januar.finalgithubuser.ui.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.januar.finalgithubuser.ui.detail.fragment.followers.FollowersFragment
import com.januar.finalgithubuser.ui.detail.fragment.following.FollowingFragment

class SectionPageAdapter (fragment: FragmentActivity?): FragmentStateAdapter(fragment!!){

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FollowersFragment()
            1 -> FollowingFragment()
            else -> FollowersFragment()
        }
    }

}
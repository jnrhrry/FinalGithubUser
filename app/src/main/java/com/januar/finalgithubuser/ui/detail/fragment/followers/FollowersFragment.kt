package com.januar.finalgithubuser.ui.detail.fragment.followers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.januar.finalgithubuser.R
import com.januar.finalgithubuser.utils.Value
import com.januar.finalgithubuser.viewmodel.FollowersViewModel
import kotlinx.android.synthetic.main.fragment_followers.*


class FollowersFragment : Fragment() {

    lateinit var followersVM: FollowersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val username = activity?.intent?.getStringExtra(Value.mainData).toString()
        followersVM = ViewModelProvider(this).get(FollowersViewModel::class.java)
        followersVM.setFollowers(username)
        getFollowersData()
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    private fun getFollowersData(){
        followersVM.getFollowers().observe(this, Observer {

            if (it.isNotEmpty()){
                progressbar_followers.visibility = View.INVISIBLE
                zero_followers.visibility = View.INVISIBLE
                recycler_followers.adapter = FollowersAdapter(it)
            } else{
                progressbar_followers.visibility = View.INVISIBLE
                zero_followers.visibility = View.VISIBLE
                recycler_followers.adapter = FollowersAdapter(it)
            }
        })
    }

    override fun onResume() {
        super.onResume()

        getFollowersData()
        recycler_followers.setHasFixedSize(true)
    }
}


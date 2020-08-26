package com.januar.finalgithubuser.ui.detail.fragment.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.januar.finalgithubuser.R
import com.januar.finalgithubuser.utils.Value
import com.januar.finalgithubuser.viewmodel.FollowingViewModel
import kotlinx.android.synthetic.main.fragment_following.*


class FollowingFragment : Fragment() {
    private lateinit var followingVM: FollowingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = activity?.intent?.getStringExtra(Value.mainData).toString()

        followingVM = ViewModelProvider(this).get(FollowingViewModel::class.java)
        followingVM.setFollowing(user)
        getFollowingData()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onResume() {
        super.onResume()

        recycler_following.setHasFixedSize(true)
        getFollowingData()
    }

    private fun getFollowingData(){
        followingVM.getFollowing().observe(this, Observer {
            if (it.isNotEmpty()){
                progressbar_following.visibility = View.INVISIBLE
                zero_following.visibility = View.INVISIBLE
                recycler_following.adapter = FollowingAdapter(it)
            } else{
                progressbar_following.visibility = View.INVISIBLE
                zero_following.visibility = View.VISIBLE
                recycler_following.adapter = FollowingAdapter(it)
            }
        })
    }

}
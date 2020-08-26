package com.januar.finalgithubuser.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.januar.finalgithubuser.R
import com.januar.finalgithubuser.utils.Value
import com.januar.finalgithubuser.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailVM: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        view_pager_detail.adapter = SectionPageAdapter(this)
        view_pager_detail.isUserInputEnabled = false

        val user = intent.getStringExtra(Value.mainData).toString()
        detailVM = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailVM.setDetail(user)
        getData()
        navBar()
    }

    private fun getData(){
        detailVM.getDetail().observe(this, Observer {
            Glide.with(this).load(it.avatar_url).into(avatar_detail)
            supportActionBar?.title = it.login
            full_name_detail.text = it.name
            location_detail.text = it.location
            follow_repo_detail.text = resources.getString(R.string.follow_repo, it.followers, it.following, it.public_repos)
            company_detail.text = it.company

            user_detail.visibility = View.VISIBLE
            progressbar_detail.visibility = View.INVISIBLE

        })
    }

    private fun navBar(){
        follow_tab_navigation_detail.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.followers_tab -> {
                    view_pager_detail.currentItem = 0
                }
                R.id.following_tab ->{
                    view_pager_detail.currentItem = 1
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

}
package com.zappcompany.githubex

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.zappcompany.githubex.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() ,RepositoryRecyclerViewAdapter.OnItemClickListener{


    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding :ActivityMainBinding
    private val repositoryRecyclerViewAdapter = RepositoryRecyclerViewAdapter(arrayListOf(), this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(MainActivityViewModel::class.java)

        binding.viewModel=viewModel
        binding.executePendingBindings()
        binding.repositoryRv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.repositoryRv.adapter = repositoryRecyclerViewAdapter
        viewModel.repositories.observe(this, Observer { it ->
            it?.let { repositoryRecyclerViewAdapter.addData(it)
        } })

    }
    override fun onItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

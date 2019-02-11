package com.zappcompany.githubex

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.zappcompany.githubex.model.OnDataReadyCallback
import com.zappcompany.githubex.model.OnRepositoryReadyCallback
import com.zappcompany.githubex.model.RepoModel

class MainViewModel : ViewModel() {
    var repoModel: RepoModel = RepoModel()

    val text = ObservableField<String>("old data")
    var repositories = MutableLiveData<ArrayList<Repository>>()

    val isLoading = ObservableField<Boolean>(false)

    fun refresh(){
        isLoading.set(true)
        repoModel.refreshData(object : OnDataReadyCallback {
            override fun onDataReady(data: String) {
                isLoading.set(false)
                text.set(data)
            }
        })
    }
    fun loadRepositories(){
        isLoading.set(true)
        repoModel.getRepositories(object :OnRepositoryReadyCallback{
            override fun onDataReady(data: ArrayList<Repository>) {
                isLoading.set(false)
                repositories.value = data
            }
        })
    }
}
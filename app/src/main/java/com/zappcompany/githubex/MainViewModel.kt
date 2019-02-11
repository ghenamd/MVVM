package com.zappcompany.githubex

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.zappcompany.githubex.model.GitRepoRepository
import com.zappcompany.githubex.model.OnRepositoryReadyCallback
import com.zappcompany.githubex.utils.NetManager

class MainViewModel : AndroidViewModel {
    constructor(application: Application) : super(application)

    var gitRepoRepository: GitRepoRepository = GitRepoRepository(NetManager(getApplication()))

    var repositories = MutableLiveData<ArrayList<Repository>>()

    val isLoading = ObservableField<Boolean>(false)

    fun loadRepositories(){
        isLoading.set(true)
        gitRepoRepository.getRepositories(object :OnRepositoryReadyCallback{
            override fun onDataReady(data: ArrayList<Repository>) {
                isLoading.set(false)
                repositories.value = data
            }
        })
    }
}
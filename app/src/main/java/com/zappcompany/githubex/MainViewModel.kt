package com.zappcompany.githubex

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.zappcompany.githubex.model.GitRepoRepository
import com.zappcompany.githubex.utils.NetManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {


    var gitRepoRepository: GitRepoRepository = GitRepoRepository(NetManager(getApplication()))

    var repositories = MutableLiveData<ArrayList<Repository>>()

    val isLoading = ObservableField<Boolean>(false)

    private lateinit var disposable: CompositeDisposable

    fun loadRepositories() {
        isLoading.set(true)
       disposable.add( gitRepoRepository.getRepositories()
           .subscribeOn(Schedulers.newThread())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribeWith(object : DisposableObserver<ArrayList<Repository>>() {
            override fun onComplete() {
                isLoading.set(false)
            }


            override fun onNext(data: ArrayList<Repository>) {
                repositories.value = data
            }

            override fun onError(e: Throwable) {

            }
        }))
    }

    override fun onCleared() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}
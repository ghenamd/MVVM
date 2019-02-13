package com.zappcompany.githubex

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.zappcompany.githubex.model.GitRepoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor( var gitRepoRepository: GitRepoRepository) : ViewModel() {

    var repositories = MutableLiveData<ArrayList<Repository>>()

    val isLoading = ObservableField<Boolean>(false)

    private  var disposable: CompositeDisposable = CompositeDisposable()

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
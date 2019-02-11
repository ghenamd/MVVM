package com.zappcompany.githubex.model

import com.zappcompany.githubex.Repository
import com.zappcompany.githubex.utils.NetManager
import io.reactivex.Observable

class GitRepoRepository(val netManager: NetManager) {

    val localDataSource = GitRepoLocalDataSource()
    val remoteDataSource = GitRepoRemoteDataSource()

    fun getRepositories(): Observable<ArrayList<Repository>> {
           // operator let checks nullability and returns a value inside it
        netManager.isConnectedToInternet?.let {
            if (it) {
                return remoteDataSource.getRepositories().flatMap {
                    return@flatMap localDataSource.saveRepositories(it)
                        .toSingleDefault(it)
                        .toObservable()

                }
            }
        }

        return localDataSource.getRepositories()
    }
}

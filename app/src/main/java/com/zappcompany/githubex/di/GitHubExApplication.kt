package com.zappcompany.githubex.di

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class GitHubExApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}
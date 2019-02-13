package com.zappcompany.githubex.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun providesAppContext(application: GitHubExApplication):Context{
        return application.applicationContext
    }
}
package com.zappcompany.githubex.di

import com.zappcompany.githubex.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * The ActivityModule generates AndroidInjector(this is the new dagger-android
 * class which exist in dagger-android framework)
 * for Activities defined in this class. This allows us to inject things into
 * Activities using AndroidInjection.inject(this) in the onCreate() method.
 */
@Module
internal abstract class MainActivityModule {

    @ContributesAndroidInjector
    internal abstract fun mainActivity():MainActivity

}
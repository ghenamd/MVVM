package com.zappcompany.githubex.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class,
    MainActivityModule::class,
    ViewModelBuilder::class])
interface AppComponent : AndroidInjector<GitHubExApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<GitHubExApplication>()
}
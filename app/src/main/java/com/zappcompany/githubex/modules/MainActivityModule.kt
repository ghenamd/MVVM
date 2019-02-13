package com.zappcompany.githubex.modules

import android.arch.lifecycle.ViewModel
import com.zappcompany.githubex.MainActivity
import com.zappcompany.githubex.MainViewModel
import com.zappcompany.githubex.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class MainActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @ContributesAndroidInjector
    internal abstract fun mainActivity():MainActivity

}
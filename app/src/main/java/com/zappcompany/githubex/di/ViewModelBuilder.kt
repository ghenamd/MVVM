package com.zappcompany.githubex.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.zappcompany.githubex.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * The ViewModelModule is used to provide a map of ViewModels through dagger
 * that is used by the ViewModelFactory class.
 */
@Module
internal abstract class ViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainViewModel(activityViewModel: MainActivityViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: DaggerAwareViewModelFactory):
            ViewModelProvider.Factory
}
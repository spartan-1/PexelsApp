package com.pnr.pexelsapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pnr.pexelsapp.ui.viewmodels.SearchViewModel
import com.pnr.pexelsapp.util.vmutil.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    /**
     * SearchViewModel provider
     *
     * @param viewModel
     * @return
     */
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun postSearchViewModel(viewModel: SearchViewModel): ViewModel

}
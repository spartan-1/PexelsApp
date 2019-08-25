package com.pnr.pexelsapp.di

import com.pnr.pexelsapp.app.PexelsApp
import com.pnr.pexelsapp.di.module.PexelAppModule
import com.pnr.pexelsapp.di.module.RestApiUtilProvider
import com.pnr.pexelsapp.di.module.ViewModelModule
import com.pnr.pexelsapp.restclient.webservices.PexelsWebService
import com.pnr.pexelsapp.ui.activities.SearchActivity
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        PexelAppModule::class,
        RestApiUtilProvider::class,
        ViewModelModule::class
    ]
)

interface PexelAppComponent : AndroidInjector<PexelsApp> {

    /**
     * interface to inject in PexelsWebService
     *
     * @param pexelsWebService
     */
    fun inject(pexelsWebService: PexelsWebService)

    /**
     * interface to inject in SearchActivity
     *
     * @param searchActivity
     */
    fun inject(searchActivity: SearchActivity)

}
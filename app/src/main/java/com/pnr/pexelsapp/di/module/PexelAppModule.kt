package com.pnr.pexelsapp.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PexelAppModule(application: Application) {
    private val pexelAppContext: Context

    init {
        pexelAppContext = application
    }

    /**
     * function to get context
     *
     * @return
     */
    @Provides
    @Singleton
    fun providePexelShowcaseAppContext(): Context {
        return pexelAppContext
    }
}
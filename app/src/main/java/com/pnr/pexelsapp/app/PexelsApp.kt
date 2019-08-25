package com.pnr.pexelsapp.app

import android.app.Application
import com.pnr.pexelsapp.BuildConfig
import com.pnr.pexelsapp.di.DaggerPexelAppComponent
import com.pnr.pexelsapp.di.PexelAppComponent
import com.pnr.pexelsapp.di.module.PexelAppModule
import timber.log.Timber

/**
 * PexelsApp Application class
 *
 */
class PexelsApp : Application() {
    private lateinit var pexelAppComponent: PexelAppComponent

    override fun onCreate() {
        super.onCreate()
        pexelAppComponent = DaggerPexelAppComponent.builder()
            .pexelAppModule(PexelAppModule(this))
            .build()

        //Starting Timber to log in DEBUG mode
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    /**
     * function to get pexelAppComponent
     *
     * @return
     */
    fun getApplicationComponent(): PexelAppComponent {
        return pexelAppComponent
    }
}
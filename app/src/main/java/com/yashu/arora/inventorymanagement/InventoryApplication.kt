package com.yashu.arora.inventorymanagement

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.poi.core.ComponentContainer
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class InventoryApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var mDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    var poiPreference: SharedPreferences? = null
    override fun onCreate() {
        super.onCreate()
        val appComponent: AppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        ComponentContainer.init(appComponent)
        ComponentContainer.getComponent<AppComponent>().inject(this)
        poiPreference = getSharedPreferences("POI_PREFRENCE", Context.MODE_PRIVATE)
    }
    override fun androidInjector(): AndroidInjector<Any?>? {
        return mDispatchingAndroidInjector
    }

}
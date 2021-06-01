package com.yashu.arora.inventorymanagement


import com.yashu.arora.inventorymanagement.AppModule
import com.yashu.arora.inventorymanagement.InventoryApplication
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, AppBuilder::class])
interface AppComponent {
    fun inject(application: InventoryApplication?)
}
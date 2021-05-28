package com.yashu.arora.inventorymanagement

import androidx.room.Room
import com.yashu.arora.inventorymanagement.data.InventoryDatabase
import dagger.Module
import dagger.Provides

@Module
class AppModule(inventoryApplication: InventoryApplication) {

    var poiDb: InventoryDatabase = Room
        .databaseBuilder<InventoryDatabase>(inventoryApplication, InventoryDatabase::class.java, "POI.db")
        .fallbackToDestructiveMigration().allowMainThreadQueries()
        .build()

    @Provides
    fun provideOneTvDb(): InventoryDatabase {
        return poiDb
    }
}
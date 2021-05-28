package com.poi.data

import android.content.Context
import androidx.room.Room
import com.yashu.arora.inventorymanagement.data.InventoryDatabase


class DatabaseClient(mCtx: Context) {
    //our app database object
    private var appDatabase: InventoryDatabase? = null

    init {
        this.appDatabase =
            Room.databaseBuilder<InventoryDatabase>(mCtx, InventoryDatabase::class.java, "PoiDatabase")
                .allowMainThreadQueries().build()
    }

    companion object {

        @Synchronized
        fun getInstance(mCtx: Context): DatabaseClient? {
            var mInstance: DatabaseClient? = null
            if (mInstance == null) {
                mInstance = DatabaseClient(mCtx)
            }
            return mInstance
        }
    }

    fun getPoiDatabase(): InventoryDatabase? {
        return appDatabase
    }
}
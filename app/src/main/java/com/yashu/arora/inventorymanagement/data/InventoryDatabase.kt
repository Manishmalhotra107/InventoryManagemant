package com.yashu.arora.inventorymanagement.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.poi.data.Converters


@Database(
    entities = [Product::class,StockIn::class, StockOut::class],
    version = 2
)
@TypeConverters(Converters::class)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
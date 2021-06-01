package com.yashu.arora.inventorymanagement.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "stockIn")
@TypeConverters(DateConverter::class)
data class StockIn(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "stockIn_id") val stockInId: Int? = null,
    @ColumnInfo(name = "date") val Date: String,
    @ColumnInfo(name = "quanity_in") val quanityIn: Int,
    @ColumnInfo(name = "product_id") val productId: Int
)
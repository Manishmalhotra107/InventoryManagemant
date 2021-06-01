package com.yashu.arora.inventorymanagement.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "stockOut")
@TypeConverters(DateConverter::class)
data class StockOut(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "stockOut_id") val stockOutId: Int? = null,
    @ColumnInfo(name = "out_date") val Date: String,
    @ColumnInfo(name = "quanity_out") val quanityOut: Int,
    @NonNull @ColumnInfo(name = "product_id") val productId: Int

)
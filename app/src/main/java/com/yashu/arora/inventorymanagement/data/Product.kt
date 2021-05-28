package com.yashu.arora.inventorymanagement.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "product_id") val productId: Int? = null,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "brand") val brand: String,
    @ColumnInfo(name = "quantity") val quantity: Int
)
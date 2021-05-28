package com.yashu.arora.inventorymanagement.data

import androidx.room.Embedded
import androidx.room.Relation

data class ProductWithStockIn(
    @Embedded
    val product: Product? = null,
    @Relation(
        parentColumn = "product_id",
        entityColumn = "product_id",
        entity = StockIn::class
    )
    val stockInList: List<StockIn> = emptyList()

)
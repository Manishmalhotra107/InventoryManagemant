package com.yashu.arora.inventorymanagement.data

import androidx.room.ColumnInfo
import androidx.room.Ignore
import androidx.room.PrimaryKey

class ProductEntity ( var productId: Int? = null,
                      var name: String? = null,
                      var price: String? = null,
                      var brand: String? = null,
                     var quantity: Int? = 0,
                      var date: Long? = 0L)
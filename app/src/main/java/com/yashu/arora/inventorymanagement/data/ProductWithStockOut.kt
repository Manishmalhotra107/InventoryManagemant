package com.yashu.arora.inventorymanagement.data

import androidx.room.Embedded
import androidx.room.Relation
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function

data class ProductWithStockOut(
    @Embedded
    val product: Product? = null,
    @Relation(
        parentColumn = "product_id",
        entityColumn = "product_id",
        entity = StockOut::class
    )
    val stockOutList: List<StockOut> = emptyList()
) {

    companion object {

        fun getProductStockOutList(list: List<ProductWithStockOut>): Observable<List<ProductEntity>>? {
            val productWithStockOutList = list.filter {
                it.stockOutList.isNotEmpty()
            }
           return Observable.fromIterable(productWithStockOutList)
                .map(Function<ProductWithStockOut, List<ProductEntity>> {
                    val list: MutableList<ProductEntity> = arrayListOf()
                    for (i in it.stockOutList) {
                        val productEntity = ProductEntity()
                        productEntity.productId = it.product?.productId
                        productEntity.price = it.product?.price
                        productEntity.quantity = i.quanityOut
                        productEntity.brand = it.product?.brand
                        productEntity.name = it.product?.name
                        productEntity.date = i.Date.toLongOrNull() ?: 0L
                        list.add(productEntity)
                    }
                    return@Function list
                })
        }
    }

}
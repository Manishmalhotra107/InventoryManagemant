package com.yashu.arora.inventorymanagement.data

import androidx.room.Embedded
import androidx.room.Relation
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.functions.Function
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

data class ProductWithStockIn(
    @Embedded
    val product: Product? = null,
    @Relation(
        parentColumn = "product_id",
        entityColumn = "product_id",
        entity = StockIn::class
    )
    val stockInList: List<StockIn> = emptyList()

){

    companion object {
        fun getProductStockIn(list: List<ProductWithStockIn>): Flowable<List<ProductEntity>>? {
            val productWithStockInList = list.filter {
                it.stockInList.isNotEmpty()
            }
           return Observable.fromIterable(productWithStockInList)
                .map(Function<ProductWithStockIn, List<ProductEntity>> {
                    val list: MutableList<ProductEntity> = arrayListOf()
                    for (i in it.stockInList) {
                        val productEntity = ProductEntity()
                        productEntity.productId = it.product?.productId
                        productEntity.price = it.product?.price
                        productEntity.quantity = i.quanityIn
                        productEntity.brand = it.product?.brand
                        productEntity.name = it.product?.name
                        productEntity.date = i.Date.toLongOrNull()?:0L
                        list.add(productEntity)
                    }
                    return@Function list
                }).toFlowable(BackpressureStrategy.BUFFER)
        }
    }
}
package com.yashu.arora.inventorymanagement.data

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
@TypeConverters(DateConverter::class)
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getProduct(): List<Product>

    @Query("SELECT count(*) FROM product")
    fun getProductCount(): Int

    @Transaction
    @Query("SELECT * FROM product")
    fun getProductWithStockIn(): List<ProductWithStockIn>

    @Transaction
    @Query("SELECT * FROM product")
    fun getProductWithStockOut(): List<ProductWithStockOut>

    /*  @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProduct(tenant: Product?)
*/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProduct(product: Product): Completable

    @Query("UPDATE product SET quantity=:quantity WHERE product_id = :productId ")
    fun updateProduct(productId: Int, quantity:Int)

    @Query("SELECT * FROM product WHERE product_id = :productId")
    fun getProductByProductId(productId: Int?): Product?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStockIn(stockIn: StockIn)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStockOut(stockOut: StockOut)


    @Query("SELECT count(*) FROM stockOut")
    fun getStockOutCount(): Int

    @Query("SELECT count(*) FROM stockIn")
    fun getStockInCount(): Int
}


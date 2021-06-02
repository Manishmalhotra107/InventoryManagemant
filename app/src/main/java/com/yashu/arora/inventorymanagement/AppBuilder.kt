package com.yashu.arora.inventorymanagement

import com.yashu.arora.inventorymanagement.dashboard.AddProduct
import com.yashu.arora.inventorymanagement.dashboard.StockInActivity
import com.yashu.arora.inventorymanagement.dashboard.UpdateProductActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppBuilder{
    @ContributesAndroidInjector()
    abstract fun contributeAddProductActivity(): AddProduct

    @ContributesAndroidInjector()
    abstract fun contributeUpdateProductActivity(): UpdateProductActivity

    @ContributesAndroidInjector()
    abstract fun contributeUpdateStockActivity(): StockInActivity
}
package com.yashu.arora.inventorymanagement.dashboard

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yashu.arora.inventorymanagement.R
import com.yashu.arora.inventorymanagement.data.*
import dagger.android.AndroidInjection
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_update_product.*
import javax.inject.Inject


class StockInActivity : AppCompatActivity() {
    @Inject
    lateinit var inventoryDatabase: InventoryDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_product)
        AndroidInjection.inject(this)
    }

    private fun updateList(lisOfProduct: List<ProductEntity>) {
        val recyclerView =
            findViewById<View>(R.id.recyclerView) as RecyclerView
        val adapter = StockAdapter(lisOfProduct)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        var lisOfProduct :Observable<List<ProductEntity>>? = null
        if(intent.getStringExtra("via").equals(STOCK_IN,true)) {
            heading_add_product.setText(R.string.stock_in)
             lisOfProduct = ProductWithStockIn.getProductStockIn(
                inventoryDatabase.productDao().getProductWithStockIn()
            )
        }else if(intent.getStringExtra("via").equals(STOCK_OUT,true)){
            heading_add_product.setText(R.string.stock_out)
            lisOfProduct = ProductWithStockOut.getProductStockOutList(
                inventoryDatabase.productDao().getProductWithStockOut())
        }
        lisOfProduct?.subscribe ({ lisOfProduct->
            if (lisOfProduct.isNullOrEmpty()) {
                error_text.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE

            } else {
                recyclerView.visibility = View.VISIBLE
                error_text.visibility = View.GONE
                updateList(lisOfProduct)
            }
        },{
            error_text.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        })

    }
}
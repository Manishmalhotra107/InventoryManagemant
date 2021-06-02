package com.yashu.arora.inventorymanagement.dashboard

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yashu.arora.inventorymanagement.R
import com.yashu.arora.inventorymanagement.data.InventoryDatabase
import com.yashu.arora.inventorymanagement.data.Product
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_update_product.*
import javax.inject.Inject


class UpdateProductActivity : AppCompatActivity() {
    @Inject
    lateinit var inventoryDatabase: InventoryDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_product)
        AndroidInjection.inject(this)
        if (intent.getStringExtra("via").equals(UPDATE_PRODUCT, true)) {
            heading_add_product.setText(R.string.update_product)
        } else if(intent.getStringExtra("via").equals(STOCK_ON_HAND, true)) {
            heading_add_product.setText(R.string.stock_on_hand)
        }
    }

    private fun updateList(lisOfProduct: List<Product>) {
        val recyclerView =
            findViewById<View>(R.id.recyclerView) as RecyclerView
        val adapter = MyListAdapter(lisOfProduct,intent.getStringExtra("via")?: UPDATE_PRODUCT)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        val lisOfProduct = inventoryDatabase.productDao().getProduct()
        if (lisOfProduct.isNullOrEmpty()) {
            error_text.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE

        } else {
            recyclerView.visibility = View.VISIBLE
            error_text.visibility = View.GONE

            updateList(lisOfProduct)
        }


    }
}
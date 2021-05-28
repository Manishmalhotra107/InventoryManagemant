package com.yashu.arora.inventorymanagement.dashboard

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yashu.arora.inventorymanagement.R
import com.yashu.arora.inventorymanagement.data.InventoryDatabase
import dagger.android.AndroidInjection
import javax.inject.Inject


class UpdateProductActivity : AppCompatActivity() {
    @Inject
    lateinit var inventoryDatabase: InventoryDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_product)
        AndroidInjection.inject(this)
    }

    private fun updateList() {
        val recyclerView =
            findViewById<View>(R.id.recyclerView) as RecyclerView
        val productList = inventoryDatabase.productDao().getProduct()
        val adapter = MyListAdapter(productList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        updateList()
    }
}
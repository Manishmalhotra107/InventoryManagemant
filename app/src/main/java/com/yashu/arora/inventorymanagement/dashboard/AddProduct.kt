package com.yashu.arora.inventorymanagement.dashboard

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yashu.arora.inventorymanagement.R
import com.yashu.arora.inventorymanagement.data.*
import dagger.android.AndroidInjection
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_product.*
import java.util.*
import javax.inject.Inject

const val ADD_PRODUCT = "add_product"
const val UPDATE_PRODUCT = "update_product"

class AddProduct : AppCompatActivity() {

    @Inject
    lateinit var inventoryDatabase: InventoryDatabase

    var lisOfProduct: ArrayList<Product>? = arrayListOf()
    var product: Product? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        AndroidInjection.inject(this)
        lisOfProduct = inventoryDatabase.productDao().getProduct() as? ArrayList<Product>
        if (intent.getStringExtra("via").equals(ADD_PRODUCT)) {
            addProduct_button.setText(R.string.add_product)
            heading_add_product.setText(R.string.add_product_new)
        } else {
            addProduct_button.setText(R.string.update_quantity)
            heading_add_product.setText(R.string.update_product_exit)
            val productId = intent.getIntExtra("productId", 1)
            product = inventoryDatabase.productDao().getProductByProductId(productId)
            productid_label.visibility = View.VISIBLE
            productid_editText.visibility = View.VISIBLE
            productid_editText.text = product?.productId.toString()
            firstname_editText.setText(product?.name.toString())
            firstname_editText.isEnabled = false
            price_editText.setText(product?.price.toString())
            price_editText.isEnabled = false
            quanity_editText.setText(product?.quantity.toString())
            brand_editText.setText(product?.brand.toString())
            brand_editText.isEnabled = false

        }
        addProduct_button.setOnClickListener {
            if (isValidProduct()) {
                if (intent.getStringExtra("via").equals(ADD_PRODUCT)) {
                    if (!isProductAlreadyAdd()) {
                        inventoryDatabase.productDao().insertProduct(
                            Product(
                                name = firstname_editText.text.toString(),
                                price = price_editText.text.toString(),
                                quantity = quanity_editText.text.toString().toInt(),
                                brand = brand_editText.text.toString()
                            )
                        ).observeOn(Schedulers.newThread()).subscribeOn(Schedulers.io()).subscribe({
                            finish()
                        }, {
                            println(it)
                        })
                    } else {
                        Toast.makeText(
                            this,
                            "Product is Already Added please visit update Product section",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    inventoryDatabase.productDao().updateProduct(
                        productid_editText.text.toString().toInt(),
                        quanity_editText.text.toString().toIntOrNull()?:0
                    )
                    val quantity = product?.quantity ?: 0

                    if (quantity > quanity_editText.text.toString().toInt()) {
                        val sellQuantity = quantity - (quanity_editText.text.toString().toIntOrNull()?:0)
                        inventoryDatabase.productDao().insertStockOut(
                            StockOut(
                                Date = Calendar.getInstance().timeInMillis.toString(),
                                quanityOut = sellQuantity,
                                productId = product?.productId ?: 0
                            )
                        )
                    } else {
                        val buyQuantity =  (quanity_editText.text.toString().toIntOrNull()?:0) - quantity
                        inventoryDatabase.productDao().insertStockIn(
                            StockIn(
                                Date = Calendar.getInstance().timeInMillis.toString(),
                                quanityIn = buyQuantity,
                                productId = product?.productId ?: 0
                            )
                        )
                    }
                    finish()
                }

            }
        }

    }

    private fun isProductAlreadyAdd(): Boolean {
        if (!lisOfProduct.isNullOrEmpty()) {
            val b = lisOfProduct?.filter {
                it.name.equals(firstname_editText.text.toString())
            }
            return !b.isNullOrEmpty()
        }
        return false
    }

    private fun isValidProduct(): Boolean {
        return !TextUtils.isEmpty(firstname_editText.text) &&
                !TextUtils.isEmpty(price_editText.text) &&
                !TextUtils.isEmpty(quanity_editText.text) &&
                !TextUtils.isEmpty(brand_editText.text)
    }
}
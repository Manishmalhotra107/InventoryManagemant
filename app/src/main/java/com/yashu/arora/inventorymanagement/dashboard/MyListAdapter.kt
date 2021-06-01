package com.yashu.arora.inventorymanagement.dashboard

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.yashu.arora.inventorymanagement.R
import com.yashu.arora.inventorymanagement.data.Product


internal class MyListAdapter(val listdata: List<Product>) : RecyclerView.Adapter<MyListAdapter.ViewHolder?>() {
    private var context: Context?= null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.adapter_product_item, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val myListData: Product = listdata[position]
        val name = "Name: "+listdata[position].name
        holder.name.text = name
        val quantity = "Quantity: "+ listdata[position].quantity
        holder.quantity.text = quantity
        holder.rightArrow.visibility = View.VISIBLE
        holder.root.setOnClickListener {
            val intent = Intent(context, AddProduct::class.java)
            intent.putExtra("via", UPDATE_PRODUCT)
            intent.putExtra("productId", myListData.productId)
            context?.startActivity(intent)
        }

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: AppCompatTextView
        var quantity: AppCompatTextView
        var rightArrow:AppCompatImageView
        var root:CardView

        init {
            name =
                itemView.findViewById<AppCompatTextView>(R.id.tv_name)
            quantity = itemView.findViewById<AppCompatTextView>(R.id.tv_quantity)
            rightArrow = itemView.findViewById<AppCompatImageView>(R.id.tv_rightArrow)
            root = itemView.findViewById<CardView>(R.id.card_view)

        }
    }


    override fun getItemCount(): Int {
        return listdata.size
    }
}
package com.yashu.arora.inventorymanagement.dashboard

import android.content.Context
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.yashu.arora.inventorymanagement.R
import com.yashu.arora.inventorymanagement.data.ProductEntity
import java.util.*
import kotlin.coroutines.EmptyCoroutineContext.plus


class StockAdapter(var listdata: MutableList<ProductEntity>) : RecyclerView.Adapter<StockAdapter.ViewHolder?>() {
    private var context: Context?= null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.adapter_product_item_stock, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val myListData: ProductEntity = listdata[position]
        val name = "Name: "+listdata[position].name
        holder.name.text = name
        val quantity = "Quantity: "+ listdata[position].quantity
        holder.quantity.text = quantity
        holder.tv_date.setText(getDate(myListData.date?.toLong()?:0L))
      /*  holder.root.setOnClickListener {
            val intent = Intent(context, AddProduct::class.java)
            intent.putExtra("via", UPDATE_PRODUCT)
            intent.putExtra("productId", myListData.productId)
            context?.startActivity(intent)
        }*/

    }
    private fun getDate(milliSeconds: Long): String? {
        val cal = Calendar.getInstance(Locale.ENGLISH)
        cal.timeInMillis = milliSeconds
        return DateFormat.format("dd-MM-yyyy", cal).toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: AppCompatTextView
        var quantity: AppCompatTextView
        var rightArrow:AppCompatImageView
        var root:CardView
        var tv_date:AppCompatTextView

        init {
            name =
                itemView.findViewById<AppCompatTextView>(R.id.tv_name)
            quantity = itemView.findViewById<AppCompatTextView>(R.id.tv_quantity)
            tv_date = itemView.findViewById<AppCompatTextView>(R.id.tv_date)
            rightArrow = itemView.findViewById<AppCompatImageView>(R.id.tv_rightArrow)
            root = itemView.findViewById<CardView>(R.id.card_view)

        }
    }


    override fun getItemCount(): Int {
        return listdata.size
    }

    fun updateList(lisOfProduct: MutableList<ProductEntity>) {
        this.listdata = (this.listdata.plus(lisOfProduct)).toMutableList()//this.listdata.plus(lisOfProduct).toMutableList()
        notifyDataSetChanged()
    }
}
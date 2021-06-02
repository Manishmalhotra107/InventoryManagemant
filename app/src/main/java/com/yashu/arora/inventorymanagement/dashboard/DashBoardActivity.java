package com.yashu.arora.inventorymanagement.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.yashu.arora.inventorymanagement.R;
import com.yashu.arora.inventorymanagement.data.StockOut;

import java.util.ArrayList;

import static com.yashu.arora.inventorymanagement.dashboard.AddProductKt.ADD_PRODUCT;
import static com.yashu.arora.inventorymanagement.dashboard.AddProductKt.STOCK_IN;
import static com.yashu.arora.inventorymanagement.dashboard.AddProductKt.STOCK_ON_HAND;
import static com.yashu.arora.inventorymanagement.dashboard.AddProductKt.STOCK_OUT;
import static com.yashu.arora.inventorymanagement.dashboard.AddProductKt.UPDATE_PRODUCT;

public class DashBoardActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemListener {


    RecyclerView recyclerView;
    ArrayList<DataModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);


        recyclerView = findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        arrayList.add(new DataModel("Add Product", R.drawable.battle, "#09A9FF"));
        arrayList.add(new DataModel("Update Product", R.drawable.beer, "#3E51B1"));
        arrayList.add(new DataModel("Stock on Hand", R.drawable.ferrari, "#673BB7"));
        arrayList.add(new DataModel("Stock In", R.drawable.jetpack_joyride, "#4BAA50"));
        arrayList.add(new DataModel("Stock Out", R.drawable.three_d, "#F94336"));
        arrayList.add(new DataModel("Review", R.drawable.terraria, "#0A9B88"));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, arrayList, this);
        recyclerView.setAdapter(adapter);


        /**
         AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         **/

        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 500);
        recyclerView.setLayoutManager(layoutManager);


        /**
         Simple GridLayoutManager that spans two columns
         **/
        /*GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);*/
    }

    @Override
    public void onItemClick(DataModel item) {
            if(item.text.equalsIgnoreCase("Add Product")){
                Intent intent = new Intent(this,AddProduct.class);
                intent.putExtra("via",ADD_PRODUCT);
                startActivity(intent);
            }else if(item.text.equalsIgnoreCase("Update Product")){
                Intent intent = new Intent(this,UpdateProductActivity.class);
                intent.putExtra("via",UPDATE_PRODUCT);
                startActivity(intent);
            }else if(item.text.equalsIgnoreCase("Stock In")){
                Intent intent = new Intent(this,StockInActivity.class);
                intent.putExtra("via",STOCK_IN);
                startActivity(intent);
            }else if(item.text.equalsIgnoreCase("Stock Out")){
                Intent intent = new Intent(this,StockInActivity.class);
                intent.putExtra("via", STOCK_OUT);
                startActivity(intent);
            }else if(item.text.equalsIgnoreCase("Stock on Hand")){
                Intent intent = new Intent(this,UpdateProductActivity.class);
                intent.putExtra("via",STOCK_ON_HAND);
                startActivity(intent);
            }

    }
}

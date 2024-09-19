package com.example.recyclerviewheader

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val itemList = listOf(
            ListItem.Header("Fruits"),
            ListItem.Product("Apple"),
            ListItem.Product("Banana"),
            ListItem.Product("Orange"),

            ListItem.Header("Vegetables"),
            ListItem.Product("Carrot"),
            ListItem.Product("Broccoli"),
            ListItem.Product("Spinach"),

            ListItem.Header("Dairy"),
            ListItem.Product("Milk"),
            ListItem.Product("Cheese"),
            ListItem.Product("Yogurt"),

            ListItem.Header("Meat"),
            ListItem.Product("Fish"),
            ListItem.Product("Chicken"),


        )

        // Set up RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GroceryAdapter(itemList)
    }
}

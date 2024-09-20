package com.example.recyclerviewheader

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ConcatAdapter
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

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)



        val fruitsHeader = "Fruits"
        val fruits = listOf("Apple", "Banana", "Orange", "Pear", "Fig", "Grapes", "Melon")

        val veggiesHeader = "Vegetables"
        val vegetables = listOf("Carrot", "Broccoli", "Lettuce", "Onion", "Tomato", "Cucumber")

        val dairyHeader = "Dairy"
        val dairy = listOf("Milk", "Cheese", "Yogurt")

        // Create adapters for each section
        val fruitsHeaderAdapter = HeaderAdapter(fruitsHeader)
        val fruitsAdapter = ProductAdapter(fruits)

        val veggiesHeaderAdapter = HeaderAdapter(veggiesHeader)
        val veggiesAdapter = ProductAdapter(vegetables)

        val dairyHeaderAdapter = HeaderAdapter(dairyHeader)
        val dairyAdapter = ProductAdapter(dairy)

        // Combine them using ConcatAdapter
        val concatAdapter = ConcatAdapter(fruitsHeaderAdapter, fruitsAdapter, veggiesHeaderAdapter, veggiesAdapter, dairyHeaderAdapter, dairyAdapter)

        // Set up RecyclerView with StickyLayoutManager
        val stickyLayoutManager = LinearLayoutManager(this)  // Assuming you're using LinearLayoutManager
        recyclerView.layoutManager = stickyLayoutManager

        // Add the concat adapter to RecyclerView
        recyclerView.adapter = concatAdapter



    }
}

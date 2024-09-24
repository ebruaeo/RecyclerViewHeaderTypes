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

        val items = listOf(
            ListItem.Header("Fruits"),
            ListItem.Product("Apple"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Pineapple"),
            ListItem.Product("Pear"),
            ListItem.Product("Orange"),
            ListItem.Product("Lemon"),
            ListItem.Product("Lemon"),
            ListItem.Product("Lemon"),
            ListItem.Product("Lemon"),
            ListItem.Product("Lemon"),
            ListItem.Product("Grapes"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Fig"),
            ListItem.Product("Papaya"),
            ListItem.Header("Vegetables"),
            ListItem.Product("Carrot"),
            ListItem.Product("Onion"),
            ListItem.Product("Spinach"),
            ListItem.Product("Leek"),
            ListItem.Product("cauliflower"),
            ListItem.Product("Broccoli"),
            ListItem.Product("Spinach"),
            ListItem.Header("Dairy"),
            ListItem.Product("Milk"),
            ListItem.Product("Cheese"),
            ListItem.Product("Cheese"),
            ListItem.Product("Cheese"),
            ListItem.Product("Cheese"),
            ListItem.Product("Cheese"),
            ListItem.Product("Cheese"),
            ListItem.Product("Yogurt")
        )

        val adapter = ItemAdapter(items)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(StickyHeaderItemDecoration(adapter))



    }
}

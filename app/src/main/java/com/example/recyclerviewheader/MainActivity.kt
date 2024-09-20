package com.example.recyclerviewheader

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var floatingHeader: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
        floatingHeader = findViewById(R.id.floatingHeader)

        val items = listOf(
            ListItem.Header("Fruits"),
            ListItem.Product("Apple"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Banana"),
            ListItem.Product("Orange"),

            ListItem.Header("Vegetables"),
            ListItem.Product("Carrot"),
            ListItem.Product("Carrot"),
            ListItem.Product("Carrot"),
            ListItem.Product("Carrot"),
            ListItem.Product("Carrot"),
            ListItem.Product("Carrot"),
            ListItem.Product("Broccoli"),
            ListItem.Product("Broccoli"),
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
            ListItem.Product("Cheese"),
            ListItem.Product("Cheese"),
            ListItem.Product("Yogurt"),

            ListItem.Header("Meat"),
            ListItem.Product("Fish"),
            ListItem.Product("Fish"),
            ListItem.Product("Fish"),
            ListItem.Product("Fish"),
            ListItem.Product("Fish"),
            ListItem.Product("Fish"),
            ListItem.Product("Fish"),
            ListItem.Product("Chicken"),


            )
        val adapter = ItemAdapter(items)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Add scroll listener to update floating header
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                updateFloatingHeader()
            }
        })
    }


    private fun updateFloatingHeader() {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()

        // Check if the first visible item is a header
        val firstVisibleItem = recyclerView.adapter?.getItemViewType(firstVisiblePosition)
        if (firstVisibleItem == VIEW_TYPE_HEADER) {
            // Show the floating header and set its text
            val header =
                recyclerView.findViewHolderForAdapterPosition(firstVisiblePosition) as? ItemAdapter.HeaderViewHolder
            header?.let {
                floatingHeader.text = it.headerTitle.text
                floatingHeader.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_PRODUCT = 1
    }

}


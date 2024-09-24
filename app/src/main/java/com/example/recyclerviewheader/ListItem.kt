package com.example.recyclerviewheader

sealed class ListItem {
    data class Header(val title: String) : ListItem()
    data class Product(val name: String) : ListItem()
}
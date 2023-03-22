package com.x7.kotlin_retrofit_fakestoreapi.model.response

data class ProductModelItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)
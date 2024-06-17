package com.dicoding.batikpedia.ui

data class BatikData(
    val title: String,
    val logo: Int,
    val desc: String,
    var isExpandable: Boolean = false
)
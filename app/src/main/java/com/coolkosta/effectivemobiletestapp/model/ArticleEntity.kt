package com.coolkosta.effectivemobiletestapp.model

data class ArticleEntity(
    val id: Int,
    val title: String,
    val date: String,
    val text: String,
    val imageUri: List<String>
)

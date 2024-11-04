package com.coolkosta.effectivemobiletestapp.article

import com.coolkosta.effectivemobiletestapp.model.ArticleEntity

sealed class ArticleState {
    data object Loading : ArticleState()
    data class Success(val article: ArticleEntity) : ArticleState()
    data object Error : ArticleState()
}
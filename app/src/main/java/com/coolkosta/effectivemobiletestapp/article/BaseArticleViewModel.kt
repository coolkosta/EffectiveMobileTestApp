package com.coolkosta.effectivemobiletestapp.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coolkosta.effectivemobiletestapp.model.ArticleEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseArticleViewModel(
    private val article: ArticleEntity
) : ViewModel() {

    private val _state = MutableStateFlow<ArticleState>(ArticleState.Loading)
    val state = _state.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _state.update { ArticleState.Success(article) }
            }.getOrElse {
                _state.update { ArticleState.Error }
            }
        }
    }
}
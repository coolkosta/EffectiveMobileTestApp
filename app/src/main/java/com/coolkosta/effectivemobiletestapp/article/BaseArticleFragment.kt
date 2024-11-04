package com.coolkosta.effectivemobiletestapp.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.coolkosta.effectivemobiletestapp.model.ArticleEntity
import kotlinx.coroutines.launch

abstract class BaseArticleFragment<B : ViewBinding, V : BaseArticleViewModel> : Fragment() {
    protected abstract val viewModel: V
    private var _binding: B? = null
    private val binding get() = requireNotNull(_binding)
    protected abstract fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean = false
    ): B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeOnViewModel()
        observeToolbarBackButton()
    }

    private fun subscribeOnViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        is ArticleState.Loading -> onArticleStateLoading()
                        is ArticleState.Success -> onArticleStateSuccess(it.article)
                        is ArticleState.Error -> onArticleStateError()
                    }
                }
            }
        }
    }

    protected abstract fun onArticleStateLoading()
    protected abstract fun onArticleStateSuccess(articleEntity: ArticleEntity)
    protected abstract fun onArticleStateError()

    private fun observeToolbarBackButton() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}


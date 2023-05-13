package com.sgg.healthykaly.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.sgg.healthykaly.R
import com.sgg.healthykaly.ui.adapter.FindListAdapter
import com.sgg.healthykaly.ui.adapter.RecipeLoadStateAdapter
import com.sgg.healthykaly.databinding.FragmentFindBinding
import com.sgg.healthykaly.model.RecipeEntity
import com.sgg.healthykaly.ui.animation.FadeInFadeOutAnimation
import com.sgg.healthykaly.ui.viewmodel.RecipeViewModel
import com.sgg.healthykaly.ui.widget.CustomErrorWidget.RefreshListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FindFragment : Fragment() {

    private lateinit var binding: FragmentFindBinding

    @Inject
    lateinit var viewModel: RecipeViewModel

    lateinit var fabAnimation: FadeInFadeOutAnimation

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val adapter = FindListAdapter()

        binding = FragmentFindBinding.inflate(inflater)
        binding.bindView(viewModel.recipes, adapter)
        fabAnimation = FadeInFadeOutAnimation(binding.fabScrollTop)
        return binding.root
    }

    private fun FragmentFindBinding.bindView(recipeModelFlow: Flow<PagingData<RecipeEntity>>,
                                             adapter: FindListAdapter) {
        lifecycleOwner = this@FindFragment
        receipList.adapter = adapter.withLoadStateHeaderAndFooter(header = RecipeLoadStateAdapter { adapter.retry() },
                                                                  footer = RecipeLoadStateAdapter { adapter.retry() })
        receipList.layoutManager = LinearLayoutManager(context,
                                                       LinearLayoutManager.VERTICAL,
                                                       false)

        receipList.addOnScrollListener(recipesScrollListener())

        // Observe the adapter's load state flow from PagingDataAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadState ->
                progressBar.isVisible = loadState.refresh is LoadState.Loading
                errorWidget.isVisible = loadState.refresh is LoadState.Error && adapter.itemCount == 0
                if (loadState.refresh is LoadState.Error && adapter.itemCount > 0) {
                    showSnackBar(getString(R.string.error_loading_data)) {
                        adapter.retry()
                    }
                }
            }
        }

        // Set the refresh listener for the retry button on custom widget
        refreshListener = RefreshListener { adapter.retry() }

        // Observe the recipe flow either remote or database source
        viewLifecycleOwner.lifecycleScope.launch {
            recipeModelFlow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                    .collect {
                        adapter.submitData(it)
                    }
        }
    }

    private fun showSnackBar(message: String,
                             action: () -> Unit) {
        Snackbar.make(binding.root,
                      message,
                      Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.txt_retry_button)) {
                    action
                }
                .show()
    }

    private fun recipesScrollListener() = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView,
                                dx: Int,
                                dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager

            if (!layoutManager.isAtTop() && binding.fabScrollTop.visibility == View.GONE) {
                fabAnimation.fadeIn()
            }
            if (layoutManager.isAtTop()) {
                fabAnimation.fadeOut()
            }
        }
    }

    private fun LinearLayoutManager.isAtTop(): Boolean {
        return this.findFirstVisibleItemPosition() == 0
    }
}


package com.sgg.healthykaly.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.sgg.healthykaly.ui.adapter.FindListAdapter
import com.sgg.healthykaly.ui.adapter.RecipeLoadStateAdapter
import com.sgg.healthykaly.databinding.FragmentFindBinding
import com.sgg.healthykaly.model.RecipeModel
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

    /**
     * RecipeViewMode declaration using manual dependency injection
     * This viewModeProvider function will automatically add dependency
     * for remote data source to the viewModel
     */
    /*private val viewModel: RecipeViewModel by viewModels {
        Injection.provideRecipeViewModel(this)
    }*/

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val adapter = FindListAdapter()

        binding = FragmentFindBinding.inflate(inflater)
        binding.bindView(viewModel.recipes, adapter)
        return binding.root
    }

    private fun FragmentFindBinding.bindView(recipeModelFlow: Flow<PagingData<RecipeModel>>,
                                             adapter: FindListAdapter) {
        lifecycleOwner = this@FindFragment
        receipList.adapter = adapter.withLoadStateHeaderAndFooter(header = RecipeLoadStateAdapter { adapter.retry() },
                                                                  footer = RecipeLoadStateAdapter { adapter.retry() })
        receipList.layoutManager = LinearLayoutManager(context,
                                                       LinearLayoutManager.VERTICAL,
                                                       false)
        // Observe the adapter's load state flow from PagingDataAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadState ->
                progressBar.isVisible = loadState.refresh is LoadState.Loading
                errorWidget.isVisible = loadState.refresh is LoadState.Error
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
}
package com.sgg.healthykaly.ui.fragment

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.sgg.healthykaly.R
import com.sgg.healthykaly.databinding.FragmentRecipeDetailsFragmentBinding
import com.sgg.healthykaly.model.SummaryResults
import com.sgg.healthykaly.ui.viewmodel.RecipeViewModel
import com.sgg.healthykaly.utils.showRefreshSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRecipeDetailsFragmentBinding

    private val args: RecipeDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = TransitionInflater.from(requireContext())
                .inflateTransition(R.transition.fade_in)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentRecipeDetailsFragmentBinding.inflate(inflater)
        updateRecipeSummary()
        binding.bindView()
        return binding.root
    }

    private fun FragmentRecipeDetailsFragmentBinding.bindView() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedRecipeId?.let {
                recipe = viewModel.getRecipe(it)
            }

            viewModel.recipeSummary
                    .collect { summaryResult ->
                        when (summaryResult) {
                            is SummaryResults.Success -> {
                                summaryProgressBar.isVisible = false
                                recipeSummary = summaryResult.summary
                            }
                            is SummaryResults.Error -> {
                                showRefreshSnackBar(binding.root, requireContext()) {
                                    viewLifecycleOwner.lifecycleScope.launch {
                                        viewModel.refreshSummary()
                                    }
                                }
                                summaryProgressBar.isVisible = false
                            }
                            is SummaryResults.Loading -> {
                                summaryProgressBar.isVisible = true
                            }
                        }

                    }
        }
    }

    private fun updateRecipeSummary() {
        viewModel.selectedRecipeId = args.id
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.refreshSummary()
        }
    }
}
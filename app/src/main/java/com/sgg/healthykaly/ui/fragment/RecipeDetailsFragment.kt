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
        binding.bindView(args.id)
        return binding.root
    }

    private fun FragmentRecipeDetailsFragmentBinding.bindView(recipeId: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            recipe = viewModel.getRecipe(recipeId)
            viewModel.getRecipeSummary(recipeId)
                    .collect { summaryResult ->
                        summaryProgressBar.isGone = summaryResult !is SummaryResults.Loading
                        if (summaryResult is SummaryResults.Success) {
                            recipeSummary = summaryResult.summary
                        } else if (summaryResult is SummaryResults.Error) {
                            showRefreshSnackBar(binding.root, requireContext()) {
                                TODO("handle click listener")
                            }
                        }
                    }
        }
    }
}
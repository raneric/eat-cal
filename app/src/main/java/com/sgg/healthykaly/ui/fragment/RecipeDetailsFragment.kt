package com.sgg.healthykaly.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.sgg.healthykaly.databinding.FragmentRecipeDetailsFragmentBinding
import com.sgg.healthykaly.ui.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment() {

    private lateinit var _binding: FragmentRecipeDetailsFragmentBinding

    private val args: RecipeDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModel: RecipeViewModel


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentRecipeDetailsFragmentBinding.inflate(inflater)
        _binding.bindView(args.id)
        return _binding.root
    }

    private fun FragmentRecipeDetailsFragmentBinding.bindView(recipeId: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            recipe = viewModel.getRecipe(recipeId)
            recipeSummary = viewModel.getRecipeSummary(recipeId)
        }
    }
}
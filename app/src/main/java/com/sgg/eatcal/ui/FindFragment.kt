package com.sgg.eatcal.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sgg.eatcal.R
import com.sgg.eatcal.adapter.FindListAdapter
import com.sgg.eatcal.databinding.FragmentFindBinding
import com.sgg.eatcal.viewmodel.RecipeViewModel

class FindFragment : Fragment() {

    private val viewModel: RecipeViewModel by viewModels()
    private lateinit var binding: FragmentFindBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentFindBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.receipList.adapter = FindListAdapter()
        binding.receipList.layoutManager = LinearLayoutManager(context,
                                                               LinearLayoutManager.VERTICAL,
                                                               false)
        return binding.root
    }

}
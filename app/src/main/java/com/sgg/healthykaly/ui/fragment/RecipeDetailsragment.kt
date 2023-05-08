package com.sgg.healthykaly.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sgg.healthykaly.R


/**
 * A simple [Fragment] subclass.
 * Use the [RecipeDetailsragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipeDetailsragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_detailsragment, container, false)
    }

}
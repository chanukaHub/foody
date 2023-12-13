package com.chanuwa.foody.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.chanuwa.foody.activities.MealActivity
import com.chanuwa.foody.databinding.FragmentHomeBinding
import com.chanuwa.foody.models.MealListModel
import com.chanuwa.foody.viewModel.HomeViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeMvvm.getRandomMeal()
        observerRandomMeal()
        onRandomMealClick()
    }

    private fun onRandomMealClick() {
        binding.randomMealCard.setOnClickListener{
            val intent = Intent(activity,MealActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observerRandomMeal() {
        homeMvvm.observeRandomMealLivedata().observe(viewLifecycleOwner,object : Observer<MealListModel.Meal>{
            override fun onChanged(value: MealListModel.Meal) {
                Glide.with(this@HomeFragment)
                    .load(value.strMealThumb)
                    .into(binding.ivRandomMeal)
            }

        })
    }
}
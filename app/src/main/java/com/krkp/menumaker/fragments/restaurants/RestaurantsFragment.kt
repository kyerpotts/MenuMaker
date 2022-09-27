package com.krkp.menumaker.fragments.restaurants

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.krkp.menumaker.R
import kotlinx.android.synthetic.main.fragment_restaurants.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_restaurants, container, false)

        // Navigate to Cart Fragment
        view.btnToCart.setOnClickListener {
            findNavController().navigate(R.id.action_restaurantsFragment_to_cartFragment)
        }

        // Navigate to Specials Fragment
        view.btnToSpecials.setOnClickListener {
            findNavController().navigate(R.id.action_restaurantsFragment_to_specialsFragment)
        }

        return view
    }

}
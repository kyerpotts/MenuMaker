package com.krkp.menumaker.fragments.specials

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.krkp.menumaker.R
import kotlinx.android.synthetic.main.fragment_specials.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [SpecialsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SpecialsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_specials, container, false)

        // Navigate to Cart Fragment
        view.btnToCart.setOnClickListener {
            findNavController().navigate(R.id.action_specialsFragment_to_cartFragment)
        }

        // Navigate to Restaurants Fragment
        view.btnToRestaurants.setOnClickListener {
            findNavController().navigate(R.id.action_specialsFragment_to_restaurantsFragment)
        }


        return view
    }
}
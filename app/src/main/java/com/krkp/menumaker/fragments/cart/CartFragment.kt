package com.krkp.menumaker.fragments.cart

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
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cart, container, false)



        // Navigate to Restaurants Fragment
        view.btnToRestaurants.setOnClickListener {
            findNavController().navigate(R.id.action_cartFragment_to_restaurantsFragment)
        }

        return view
    }
}
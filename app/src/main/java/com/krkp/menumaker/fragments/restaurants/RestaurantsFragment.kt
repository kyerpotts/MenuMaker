package com.krkp.menumaker.fragments.restaurants

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.krkp.menumaker.R
import com.krkp.menumaker.database.entities.Restaurants
import kotlinx.android.synthetic.main.fragment_restaurants.view.*
import kotlinx.android.synthetic.main.fragment_restaurants.view.btnToCart
import kotlinx.android.synthetic.main.fragment_specials.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantsFragment : Fragment() {
    private lateinit var restaurantsViewModel: RestaurantsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_restaurants, container, false)

        // RecyclerView set up
        val adapter = RestaurantsAdapter()
        val recyclerView = view.rvRestaurants
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //ViewModel set up
        restaurantsViewModel = ViewModelProvider(this).get(RestaurantsViewModel::class.java)
        restaurantsViewModel.retrieveRestaurantsData().observe(viewLifecycleOwner, Observer { restaurants ->
            adapter.setData(restaurants)
        })

        restaurantsViewModel.insertRestaurant(Restaurants("Bubba Gump", "129 West 81st Street", "bubbagump", "Not Just Gumbo"))

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
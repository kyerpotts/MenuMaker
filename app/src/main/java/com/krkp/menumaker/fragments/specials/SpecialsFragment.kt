package com.krkp.menumaker.fragments.specials

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.krkp.menumaker.R
import com.krkp.menumaker.database.entities.Food
import kotlinx.android.synthetic.main.fragment_specials.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [SpecialsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SpecialsFragment : Fragment() {
    private lateinit var specialsViewModel: SpecialsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_specials, container, false)

        // RecyclerView set up
        val adapter = SpecialsAdapter(SpecialsAdapter.OnClickListener { orderItem ->
            specialsViewModel.addToCart(orderItem)
        })
        val recyclerView = view.rvSpecials
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ViewModel set up
        specialsViewModel = ViewModelProvider(this).get(SpecialsViewModel::class.java)
        specialsViewModel.retrieveSpecialsData().observe(viewLifecycleOwner, Observer { food ->
            adapter.setData(food)
        })

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
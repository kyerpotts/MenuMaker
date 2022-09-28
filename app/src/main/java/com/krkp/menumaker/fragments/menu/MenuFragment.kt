package com.krkp.menumaker.fragments.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.krkp.menumaker.R
import kotlinx.android.synthetic.main.fragment_menu.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {
    private val args by navArgs<MenuFragmentArgs>()
    private lateinit var menuViewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)


        // RecyclerView set up
        val adapter = MenuAdapter()
        val recyclerView = view.rvMenu
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ViewModel set up
        menuViewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        menuViewModel.retrieveMenuData(args.currentRestaurant).observe(viewLifecycleOwner, Observer{ menu ->
            adapter.setData(menu)
        })

        // Navigate to Cart Fragment
        view.btnToCart.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_cartFragment)
        }

        // Navigate to Restaurants Fragment
        view.btnToRestaurants.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_restaurantsFragment)
        }

        return view
    }
}
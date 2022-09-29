package com.krkp.menumaker.fragments.cart

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
import com.krkp.menumaker.MainActivity
import com.krkp.menumaker.R
import kotlinx.android.synthetic.main.fragment_cart.view.*
import kotlinx.android.synthetic.main.fragment_specials.view.*
import kotlinx.android.synthetic.main.fragment_specials.view.btnToRestaurants

/**
 * A simple [Fragment] subclass.
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartFragment : Fragment() {

    private lateinit var cartViewModel: CartViewModel

    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        // Connect to mainActivity
        mainActivity = activity as MainActivity

        // RecyclerView set up
        val adapter = CartAdapter(CartAdapter.OnClickListener { orderItem ->
            cartViewModel.updateOrRemoveFromCart(orderItem)
        })

        val recyclerView = view.rvCart
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ViewModel set up
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        cartViewModel.retrieveCartData().observe(viewLifecycleOwner, Observer { orderItems ->
            adapter.setData(orderItems)
        })


        // Navigate to Login / Orders Fragment
        view.btnCheckout.setOnClickListener {
            cartViewModel.retrieveCartData().observe(viewLifecycleOwner, Observer { orderItems ->
                if (mainActivity.isLoggedIn()) {
                    val action =
                        CartFragmentDirections.actionCartFragmentToOrdersFragment(orderItems.toTypedArray())
                    findNavController().navigate(action)
                } else {
                    val action =
                        CartFragmentDirections.actionCartFragmentToLoginFragment(orderItems.toTypedArray())
                    findNavController().navigate(action)
                }
            })
        }

        // Navigate to Restaurants Fragment
        view.btnToRestaurants.setOnClickListener {
            findNavController().navigate(R.id.action_cartFragment_to_restaurantsFragment)
        }

        return view
    }
}
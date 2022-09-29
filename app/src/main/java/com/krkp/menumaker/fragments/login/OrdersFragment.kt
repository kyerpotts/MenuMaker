package com.krkp.menumaker.fragments.login

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
import com.krkp.menumaker.MainActivity
import com.krkp.menumaker.R
import kotlinx.android.synthetic.main.fragment_orders.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [OrdersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrdersFragment : Fragment() {
    private val args by navArgs<OrdersFragmentArgs>()
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_orders, container, false)

        // Connect to mainActivity
        mainActivity = activity as MainActivity

        // RecyclerView set up
        val adapter = OrdersAdapter()
        val recyclerView = view.rvOrders
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ViewModel set up
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        mainActivity.user?.username?.let {
            loginViewModel.retrieveOrdersData(it).observe(viewLifecycleOwner, Observer { orders ->
                adapter.setData(orders)
            })
        }

        // Submit cart contents as an order and clear cart
        mainActivity.user?.username?.let {
            loginViewModel.dispatchOrder(it, args.orderList)
        }

        // Navigate to Restaurants Fragment
        view.btnToRestaurants.setOnClickListener{
            findNavController().navigate(R.id.action_ordersFragment_to_restaurantsFragment)
        }

        // Logout and navigate to Specials Fragment
        view.btnLogout.setOnClickListener {
            mainActivity.user = null
            findNavController().navigate(R.id.action_ordersFragment_to_specialsFragment)
        }
        return view
    }
}
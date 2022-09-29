package com.krkp.menumaker.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.krkp.menumaker.R
import com.krkp.menumaker.database.entities.OrderItem
import kotlinx.android.synthetic.main.fragment_single_order.view.*
import kotlinx.android.synthetic.main.fragment_single_order.view.tvDateTime
import kotlinx.android.synthetic.main.fragment_single_order.view.tvOrderID
import kotlinx.android.synthetic.main.order_row.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [SingleOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SingleOrderFragment : Fragment() {
    private val args by navArgs<SingleOrderFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_single_order, container, false)
        val content = args.singleOrder

        view.tvOrderID.text = content.orderID.toString()
        view.tvDateTime.text = content.dateTime
        view.tvOrder.text = content.order
        view.tvTotal.text = content.total.toString()

        view.btnOrderHistory.setOnClickListener {
//            findNavController().navigate(R.id.action_singleOrderFragment_to_ordersFragment)
            val emptyList = emptyList<OrderItem>().toTypedArray()
            val action = SingleOrderFragmentDirections.actionSingleOrderFragmentToOrdersFragment(emptyList)
            findNavController().navigate(action)
        }
        return view
    }
}
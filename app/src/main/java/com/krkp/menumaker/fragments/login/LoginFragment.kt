package com.krkp.menumaker.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.krkp.menumaker.MainActivity
import com.krkp.menumaker.R
import com.krkp.menumaker.database.entities.Users
import kotlinx.android.synthetic.main.fragment_login.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Connect to mainActivity
        mainActivity = activity as MainActivity

        // ViewModel set up
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        view.btnLogin.setOnClickListener {
            val username = view.etUsername.text.toString()
            val password = view.etPassword.text.toString()
            if (username == "" || password == "") {
                Toast.makeText(activity, "Fields cannot be empty", Toast.LENGTH_LONG).show()
            } else {
                loginViewModel.retrieveUserData(username)
                    .observe(viewLifecycleOwner, Observer { user ->
                        val testUserNull: Users? = user
                        if (loginViewModel.isValidUser(testUserNull, username, password)) {
                            mainActivity.user = user
                        } else {
                            Toast.makeText(
                                activity,
                                "User is invalid, check details and try again",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        if (mainActivity.isLoggedIn()) {
                            Toast.makeText(
                                activity,
                                "Login successful",
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(R.id.action_loginFragment_to_ordersFragment)
                        }
                    })
            }
        }

        view.btnRegister.setOnClickListener {
            val username = view.etUsername.text.toString()
            val password = view.etPassword.text.toString()
            if (username == "" || password == "") {
                Toast.makeText(activity, "Fields cannot be empty", Toast.LENGTH_LONG).show()
            } else {
                val user = Users(username, password)
                loginViewModel.registerUser(user)
                Toast.makeText(activity, "Successfully registered $username", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return view
    }
}
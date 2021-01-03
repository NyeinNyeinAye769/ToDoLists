package com.example.todolists.ui.user_register

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolists.R
import com.example.todolists.data.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    private val viewModelUser: RegistrationFragmentViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val login_name = view.findViewById<TextView>(R.id.login_name)
        val login_email = view.findViewById<TextView>(R.id.login_email)
        val login_password = view.findViewById<TextView>(R.id.login_password)

        view.findViewById<Button>(R.id.login_register)?.setOnClickListener {

            var hasError = false

            if (TextUtils.isEmpty(login_name.text)){
                login_name.error = "Name is required"
                hasError = true
            }
            if (TextUtils.isEmpty(login_email.text)){
                login_email.error = "Email is required"
                hasError = true
            }
            if (TextUtils.isEmpty(login_password.text)){
                login_password.error = "Password is required"
                hasError = true
            }
            if (hasError != true){

                val user = User(
                    id = null,
                    name = login_name.text.toString(),
                    email = login_email.text.toString(),
                    password = login_password.text.toString())

                viewModelUser.registerUser(user)

                val bundle = bundleOf("email" to login_email.text.toString(), "name" to login_name.text.toString())
                findNavController().navigate(R.id.home_fragment, bundle,null)
            }
        }
    }

}
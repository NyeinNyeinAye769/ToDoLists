package com.example.todolists.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.todolists.R
import com.example.todolists.data.model.Task
import com.example.todolists.data.model.User
import com.example.todolists.ui.user_register.RegistrationFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModelUser: LoginFragmentViewModel by viewModels()

    lateinit var adapter: LoginAdapter

    lateinit var userList1: LiveData<List<User>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = view.findViewById<TextView>(R.id.login_email)
        val password = view.findViewById<TextView>(R.id.login_password)

        view.findViewById<Button>(R.id.login_button)?.setOnClickListener {

            var hasError = false

            if (TextUtils.isEmpty(email.text)){
                email.error = "Name is required"
                hasError = true
            }
            if (TextUtils.isEmpty(password.text)){
                password.error = "Password is required"
                hasError = true
            }
            if (hasError != true){

//                userList1 = viewModelUser.getUsers(email.text.toString())

                var user = viewModelUser.searchUserByEmail(email.text.toString())

                ObserverChecking()

//                val email1 = userList1.value?.get(0)?.email
//                val password1 = userList1.value?.get(0)?.password
//
//                if (email1 == null) {
//                    Toast.makeText(activity, "Email and password don't match. Please! Try Again!", Toast.LENGTH_SHORT).show()
//                } else {
//                    if (email1 == email.text.toString()) {
//                        if (password1 == password.text.toString()) {
//                            findNavController().navigate(R.id.home_fragment, null, null)
//                        } else {
//                            Toast.makeText(activity, "Email and password don't match. Please! Try Again!", Toast.LENGTH_SHORT).show()
//                        }
//                    } else {
//                        Toast.makeText(activity, "Email and password don't match. Please! Try Again!", Toast.LENGTH_SHORT).show()
//                    }
//                }
            }
        }

        view.findViewById<TextView>(R.id.register)?.setOnClickListener {
            findNavController().navigate(R.id.register_fragment,null,null)
        }
    }

    private fun ObserverChecking() {
        viewModelUser.displayLoading.observe(viewLifecycleOwner, Observer {
            if (it){
                viewModelUser.isUserExist.observe(viewLifecycleOwner, Observer {
                    if (it){
                        findNavController().navigate(R.id.home_fragment, null, null)
                    }
                    else{
                        Toast.makeText(activity, "Email and password don't match. Please! Try Again!", Toast.LENGTH_SHORT).show()
                    }
                })
            }
//            else{
//                Toast.makeText(activity, "Email and password don't match. Please! Try Again!", Toast.LENGTH_SHORT).show()
//            }
        })
    }
}

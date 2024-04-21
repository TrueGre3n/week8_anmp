package com.ubaya.uts_160421038.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.uts_160421038.R
import com.ubaya.uts_160421038.databinding.FragmentRegisterBinding
import com.ubaya.uts_160421038.viewmodel.RegisterViewModel


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener {
            var username = binding.txtUsername.text.toString()
            var firstName = binding.txtFirst.text.toString()
            var lastName = binding.txtLast.text.toString()
            var password = binding.txtPassword.text.toString()
            var confirm = binding.txtConfirm.text.toString()
            var email = binding.txtEmail.text.toString()


            if ((password == confirm) &&
                (username != "" && password != "" && confirm != "" && firstName != "" && lastName != "" && email != "")
            ) {
                viewModel.fetch(username, firstName, lastName, email,password )


            } else if (username == "" || password == "" || confirm == "" || firstName == "" || lastName == "" || email == "") {
                Toast.makeText(requireContext(), "Fill all Field", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(requireContext(), "Wrong Password", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBack.setOnClickListener{
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }

        viewModel.registerLD.observe(viewLifecycleOwner, Observer{
            if(it == true) {
                Toast.makeText(requireContext(), "Succesfully Registered", Toast.LENGTH_SHORT).show()

            }
            else{
                Toast.makeText(requireContext(), "Failed to Register", Toast.LENGTH_SHORT).show()
            }




        })
    }
}
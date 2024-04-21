package com.ubaya.uts_160421038.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.ubaya.uts_160421038.databinding.FragmentLoginBinding
import com.ubaya.uts_160421038.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            var username = binding.txtUsername.text.toString()
            var password = binding.txtPassword.text.toString()
            if (username == "" || password == ""){
                Toast.makeText(requireContext(), "Isi semua field", Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.fetch(username, password)
            }

        }

        viewModel.passErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true){
                Toast.makeText(requireContext(), "gagal login", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.loginStatLD.observe(viewLifecycleOwner, Observer{
            if(it == true) {
                Toast.makeText(requireContext(), "sukses login", Toast.LENGTH_SHORT).show()
                var loginInfo = "com.ubaya.uts_160421038"
                var shared: SharedPreferences = requireContext().getSharedPreferences(loginInfo,
                    Context.MODE_PRIVATE )
                var editor: SharedPreferences.Editor = shared.edit()
                editor.putString("username", binding.txtUsername.text.toString())
                editor.apply()
                binding.txtUsername.setText("")
                binding.txtPassword.setText("")

                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                viewModel.loginStatLD.value = false

            }
        })


        binding.btnRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }


    }

}
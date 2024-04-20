package com.ubaya.uts_160421038.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.uts_160421038.R
import com.ubaya.uts_160421038.databinding.FragmentProfileBinding
import com.ubaya.uts_160421038.model.User
import com.ubaya.uts_160421038.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private var username:String = ""
    private var user: User = User("","","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.updateDataLD.value = false
        var loginInfo = "com.ubaya.uts_160421038"
        var shared: SharedPreferences = requireContext().getSharedPreferences(loginInfo,
            Context.MODE_PRIVATE )
        username = shared.getString("username","").toString()

        viewModel.fetch(username)

        binding.btnLogout.setOnClickListener {
            var loginInfo = "com.ubaya.uts_160421038"
            var shared: SharedPreferences =
                requireContext().getSharedPreferences(loginInfo, Context.MODE_PRIVATE)
            var editor: SharedPreferences.Editor = shared.edit()
            editor.clear()
            editor.apply()
            requireActivity().finish()
        }


        fun UpdateUI(userLog: User){
            binding.txtFirst.setText(userLog.first_name)
            binding.txtLast.setText(userLog.last_name)
            binding.txtInfo.setText(userLog.username+"("+userLog.email+")")

        }

        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            user = it
            UpdateUI(it)

            //Update Data
            binding.btnUpdate.setOnClickListener {
                val newFirstName = binding.txtFirst.text.toString()
                val newLastName = binding.txtLast.text.toString()

                viewModel.updateData(user.username!!, newFirstName, newLastName)
            }

            //Update Pass
            binding.btnChange.setOnClickListener {
                val oldPass = binding.txtOldPass.text.toString()
                val newPass = binding.txtNewPass.text.toString()
                val confPass = binding.txtConfPass.text.toString()

                if (newPass == confPass && oldPass == user.password)
                {
                    viewModel.updatePassword(user.username!!, newPass)
                    binding.txtOldPass.setText("")
                    binding.txtNewPass.setText("")
                    binding.txtConfPass.setText("")
                    Toast.makeText(requireContext(), "Password Updated Succesfully", Toast.LENGTH_SHORT).show()


                }
                else if (confPass == "" || newPass == "" ||oldPass == "")
                {
                    Toast.makeText(requireContext(), "Please Fill Every Field ", Toast.LENGTH_SHORT)
                        .show()
                }
                else
                {
                    Toast.makeText(requireContext(), "Field not Matched", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

        viewModel.updateDataLD.observe(viewLifecycleOwner, Observer{
            if(it == true) {
                Toast.makeText(requireContext(), "Data Succesfully Updated", Toast.LENGTH_SHORT).show()
            }
        })

    }


}
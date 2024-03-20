package com.ubaya.studentapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.studentapp.R
import com.ubaya.studentapp.databinding.FragmentStudentDetailBinding
import com.ubaya.studentapp.model.Student
import com.ubaya.studentapp.viewmodel.DetailViewModel
import com.ubaya.studentapp.viewmodel.ListViewModel


class StudentDetailFragment : Fragment() {
    private lateinit var binding:FragmentStudentDetailBinding
    private lateinit var viewModel: DetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val data = viewModel.fetch()

        val student = viewModel.studentLD.value

        binding.txtID.setText(student?.id)
        binding.txtName.setText(student?.name)
        binding.txtBod.setText(student?.dob)
        binding.txtPhone.setText(student?.phone)
    }

}
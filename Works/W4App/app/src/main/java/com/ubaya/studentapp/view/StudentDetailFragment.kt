package com.ubaya.studentapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.studentapp.R
import com.ubaya.studentapp.databinding.FragmentStudentDetailBinding
import com.ubaya.studentapp.model.Student
import com.ubaya.studentapp.viewmodel.DetailViewModel
import com.ubaya.studentapp.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


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
        var id = ""
        if(arguments != null){
            id = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
        }

        viewModel.fetch(id)
        binding.txtID.setText(id)
        viewModel.studentLD.observe(viewLifecycleOwner,  {
            var student = it
            binding.btnUpdate?.setOnClickListener {
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotification(student.name.toString(),
                            "created new notification ",
                            R.drawable.baseline_person_2_24)
                    }
            }

            binding.txtName.setText(it.name)
            binding.txtBod.setText(it.dob)
            binding.txtPhone.setText(it.phone)
            val picasso = Picasso.Builder(requireContext())
            picasso.listener { picasso, uri, exception ->
                exception.printStackTrace()
            }

            picasso.build().load(it.photoUrl)
                .into(binding.imageView, object: Callback {
                    override fun onSuccess() {
                        binding.progressBar2.visibility = View.INVISIBLE
                        binding.imageView.visibility = View.VISIBLE
                    }
                    override fun onError(e: Exception?) {
                        Log.e("picasso_error", e.toString())
                    }
                })

        })



    }


}
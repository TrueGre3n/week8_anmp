package com.ubaya.anmp_week1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ubaya.anmp_week1.databinding.FragmentMainBinding
import com.ubaya.anmp_week1.databinding.FragmentResultBinding



class ResultFragment : Fragment() {

   private lateinit var binding:FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null){
            val score = ResultFragmentArgs.fromBundle(requireArguments()).resultScore
            binding.txtScore.text = "Your Score is $score"
        }

        binding.btnBackMain.setOnClickListener{
            val action = ResultFragmentDirections.actionBackMainFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

}
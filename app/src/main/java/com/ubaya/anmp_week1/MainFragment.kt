package com.ubaya.anmp_week1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ubaya.anmp_week1.databinding.FragmentMainBinding
import kotlin.random.Random

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var num1 = Random.nextInt(1,1000)
        var num2 = Random.nextInt(1,1000)
        var answer = num1 + num2

        binding.txtQuestion.text = "$num1 + $num2"

        var score = 0
        binding.btnSubmit.setOnClickListener{
            if(binding.txtAnswer.text.toString() == answer.toString()){
                score = score + 1

                num1 = Random.nextInt(1,1000)
                num2 = Random.nextInt(1,1000)
                answer = num1 + num2

                binding.txtQuestion.text = "$num1 + $num2"
                binding.txtAnswer.text = null
            }
            else{
                //val name =  binding.txtName.text.toString()
                val action = MainFragmentDirections.actionResultFragment(score)
                //memintakan contorler untuk mencari controler yg ada di
                //bernavigasi ke action yg dipilih
                Navigation.findNavController(it).navigate(action)
            }

        }
    }
}
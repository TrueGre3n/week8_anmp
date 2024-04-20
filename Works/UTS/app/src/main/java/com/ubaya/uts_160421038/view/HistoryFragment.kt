package com.ubaya.uts_160421038.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ubaya.uts_160421038.R
import com.ubaya.uts_160421038.databinding.FragmentDetailBinding
import com.ubaya.uts_160421038.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {
private lateinit var binding: FragmentHistoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }


}
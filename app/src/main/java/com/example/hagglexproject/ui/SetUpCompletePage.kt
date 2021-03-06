package com.example.hagglexproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hagglexproject.R
import com.example.hagglexproject.databinding.FragmentSetUpCompletePageBinding


class SetUpCompletePage : Fragment() {

    private var _binding : FragmentSetUpCompletePageBinding? = null
    private val binding
    get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSetUpCompletePageBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.exploreBtn.setOnClickListener {
            findNavController().navigate(R.id.action_setUpCompletePage_to_dashBoardPage)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
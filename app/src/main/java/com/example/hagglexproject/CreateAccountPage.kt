package com.example.hagglexproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.hagglexproject.databinding.FragmentCreateAccountPageBinding


class CreateAccountPage : Fragment() {


    private  var _binding : FragmentCreateAccountPageBinding? = null
    private val binding
     get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateAccountPageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createAccountSignUpBtn.setOnClickListener {
            findNavController().navigate(R.id.verificationPage)
        }



    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
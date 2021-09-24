package com.example.hagglexproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.example.hagglexproject.LoginUserMutation
import com.example.hagglexproject.R
import com.example.hagglexproject.apolloClient
import com.example.hagglexproject.databinding.FragmentWelcomePageBinding
import com.example.hagglexproject.viewmodel.MainViewModel
import com.example.type.LoginInput

class WelcomePage : Fragment() {

    private var _binding : FragmentWelcomePageBinding? = null
    private val binding
    get() = _binding!!
    private val viewModel : MainViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentWelcomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.welcomePageForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.verificationPage)
        }

        binding.welcomePageCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.createAccountPage)
        }
        binding.welcomePageLogin.setOnClickListener {
            loginUser()
        }

    }



    fun loginUser(){
        var login = LoginInput(binding.welcomePageEmailEt.text.toString(), binding.welcomePagePasswordEt.text.toString())

        apolloClient().mutate(LoginUserMutation(login)).enqueue(
            object : ApolloCall.Callback<LoginUserMutation.Data>(){
                override fun onResponse(response: Response<LoginUserMutation.Data>) {
                   var res = response.data

                    res?.let {
                        if(it.login.token != null ){
                            activity?.runOnUiThread {
                                findNavController().navigate(R.id.dashBoardPage)
                                viewModel.updateLoginToken(res.login.token!!)
                                viewModel.updateId(res.login.user._id)
                            }
                        }
                    }

                    if(response.errors != null){
                        activity?.runOnUiThread {
                            Toast.makeText(requireContext(), "message : ${response.errors!![0].message} ", Toast.LENGTH_SHORT).show()
                        }

                    }




                }

                override fun onFailure(e: ApolloException) {

                    e.printStackTrace()
                }

            }
        )

    }





    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}
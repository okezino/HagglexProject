package com.example.hagglexproject.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.example.hagglexproject.RegisterMutation
import com.example.hagglexproject.apolloClient
import com.example.hagglexproject.databinding.FragmentCreateAccountPageBinding
import com.example.hagglexproject.viewmodel.MainViewModel
import com.example.type.PhoneNumberDetailsInput
import com.google.android.material.snackbar.Snackbar


class CreateAccountPage : Fragment() {

    private val viewMode : MainViewModel by activityViewModels()
    private  var _binding : FragmentCreateAccountPageBinding? = null
    private val binding
     get() = _binding!!

    companion object {
        var userToken: String = ""
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
            createUser(view)

        }



    }

    fun createUser(view:View){
        var phoneNumber = PhoneNumberDetailsInput(binding.createAccountPhoneNumber.text.toString(),binding.createAccountCcp.defaultCountryName,binding.createAccountCcp.defaultCountryCode)
        var input : Input<PhoneNumberDetailsInput> = Input.fromNullable(phoneNumber)
        var createUserInput = com.example.type.CreateUserInput(binding.createAccountEmailEt.text.toString(),
            binding.createAccountUsernameEt.text.toString(),binding.createAccountPasswordEt.text.toString(),
            binding.createAccountPhoneNumber.text.toString(), Input.fromNullable(null),input,binding.createAccountCcp.defaultCountryName,binding.createAccountCcp.defaultCountryName)
        apolloClient().mutate(
            RegisterMutation(data = Input.optional(createUserInput)
        )
        ).enqueue(object : ApolloCall.Callback<RegisterMutation.Data>(){
            override fun onResponse(response: Response<RegisterMutation.Data>) {


                Log.d("Create User Error",response.toString() )



                response.data?.register?.let {
                    Log.d("Create User",response.data.toString())
                    activity?.runOnUiThread {
                        val action = CreateAccountPageDirections.actionCreateAccountPageToVerificationPage(
                            response.data!!.register!!.user.email, response.data!!.register!!.token)
                        viewMode.updateRegistrationToken(response.data!!.register!!.token)
                        userToken = response.data!!.register!!.token
                        findNavController().navigate(action)
                        Snackbar.make(view,"Account has been Created",Snackbar.LENGTH_LONG).show()
                        Log.d("Create User" , response.data!!.register!!.token )
                    }
                }


                response.errors?.let {
                    activity?.runOnUiThread {
                        Toast.makeText(requireContext(),"Message : ${response.errors!![0].message}" , Toast.LENGTH_SHORT).show()
                    }
                }

            }

            override fun onFailure(e: ApolloException) {

                Log.d("Create User Error", e.localizedMessage)
                e.printStackTrace()
            }

        })

    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
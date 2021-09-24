package com.example.hagglexproject.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.hagglexproject.R
import com.example.hagglexproject.SplashScreen
import com.example.hagglexproject.databinding.FragmentVerificationPageBinding
import com.example.hagglexproject.generateMaterialDialog
import com.example.hagglexproject.ui.adapter.MainRecyclerView
import com.example.hagglexproject.ui.adapter.Viewpager
import com.example.hagglexproject.viewmodel.MainViewModel
import java.lang.Math.abs


class DashBoardPage : Fragment() {

    lateinit var userStatus : TextView
    lateinit var  amountdash : TextView
    lateinit var logout : View
    lateinit var switch: Switch
    private val viewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dash_board_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userStatus = view.findViewById(R.id.dashboard_userId)
        val adapter = Viewpager()
        val pager : ViewPager2 = view.findViewById(R.id.pager)
        pager.adapter = adapter
        val dashRecyclerView : RecyclerView = view.findViewById(R.id.dashBoardRecyclerview)
        val reAdapter = MainRecyclerView()
        val layoutManager = LinearLayoutManager(requireContext())
        logout = view.findViewById(R.id.textbg)
        switch = view.findViewById(R.id.dashboard_switch)
        amountdash = view.findViewById(R.id.amount_value)



        layoutManager.orientation = LinearLayoutManager.VERTICAL
        dashRecyclerView.layoutManager = layoutManager
        dashRecyclerView.adapter = reAdapter

        pager.clipToPadding = false
        pager.clipChildren = false
        pager.offscreenPageLimit = 3
        pager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val marginPageTransformer = MarginPageTransformer(40)
        val compositePageTransformer = CompositePageTransformer()

        compositePageTransformer.addTransformer(marginPageTransformer)
        compositePageTransformer.addTransformer { page, position ->
//            val r: Float = 1 - kotlin.math.abs(position)
//         page.translationX = 60f
//         page.scaleY = 1 -(0.25f * abs(position))
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f

        }


        pager.setPageTransformer(compositePageTransformer)

        viewModel.userName.observe(viewLifecycleOwner,{
            userStatus.text = it.take(2).toUpperCase()
        })

        logout.setOnClickListener {
            generateMaterialDialog(
                requireActivity(), "Alert", "Are You sure you want to logout",
                "Yes", "No", { signout() },{}
            )
        }

        switch.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                if(p1){
                    amountdash.text = "#0.00"

                }else {
                    amountdash.text = "$****"
                }
            }

        })
    }

    private fun signout() {

        requireActivity().finish()
    }


}
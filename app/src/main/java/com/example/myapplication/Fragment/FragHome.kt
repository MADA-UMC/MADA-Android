package com.example.myapplication.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.HomeFragmentBinding

class FragHome : Fragment() {

    lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)

        // menu 클릭 리스너
        binding.toolbarHome.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.home_menu_category -> {
                    findNavController().navigate(R.id.action_fragHome_to_homeCategoryFragment2)
                }
                R.id.home_menu_timetable -> {
                    findNavController().navigate(R.id.action_fragHome_to_homeTimetableFragment)
                }
                R.id.home_menu_repeatTodo -> {
                    findNavController().navigate(R.id.action_fragHome_to_homeRepeatTodoFragment)
                }
            }
            false
        }

        //date clicklistener -> 추가 예정
        binding.tvHomeCalendar.setOnClickListener {

        }

        return binding.root
    }

}
package com.example.myapplication.CalenderFuntion

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.HomeFunction.category.sampleCategoryData
import com.example.myapplication.R
import com.example.myapplication.databinding.CalendarAddBinding
import com.example.myapplication.databinding.CalendarAddSBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale


class CalendarAddSFragment : Fragment() {
    lateinit var binding: CalendarAddSBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = CalendarAddSBinding.inflate(layoutInflater)
        hideBootomNavigation(true)
        val title = arguments?.getString("title")
        val preSchedule = arguments?.getString("preSchedule") ?:"2023-6-1"
        val nextSchedule = arguments?.getString("nextSchedule") ?:"2023-6-1"
        val preClock = arguments?.getString("preClock")
        val nextClock = arguments?.getString("nextClock")
        val cycle = arguments?.getString("cycle")
        val memo = arguments?.getString("memo")
        val color = arguments?.getString("color")
        //데이터 받아서 로드하기
        binding.title.text = title
        binding.calendarColor.setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)
        binding.preScheldule.text = convertToDateKoreanFormat(preSchedule)
        binding.nextScheldule.text = convertToDateKoreanFormat(nextSchedule)
        if(preClock != null) {
            binding.preScheldule2.text = preClock
            binding.nextScheldule2.text = nextClock
        } else {
            binding.clcckLayoutBar.visibility = View.GONE
            binding.clockLayout.visibility = View.GONE
        }

        binding.cycle.text = cycle
        if(memo != null)
            binding.memo.text = memo
        else
            binding.memoLayout.visibility = View.GONE

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_calendarAddS_to_fragCalendar)
        }
        binding.toolbarCalendar.inflateMenu(R.menu.calendar_edit_menu)
        binding.toolbarCalendar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.calendar_eidt -> {
                    val bundle = Bundle()
                    bundle.putString("title", arguments?.getString("title"))
                    bundle.putString("preSchedule", arguments?.getString("preSchedule"))
                    bundle.putString("nextSchedule", arguments?.getString("nextSchedule"))
                    bundle.putString("preClock", arguments?.getString("preClock"))
                    bundle.putString("nextClock", arguments?.getString("nextClock"))
                    bundle.putString("cycle", arguments?.getString("cycle"))
                    bundle.putString("memo", arguments?.getString("memo"))
                    bundle.putString("color", arguments?.getString("color"))
                    Navigation.findNavController(view).navigate(R.id.action_calendarAddS_to_calendarAdd,bundle)
                    true
                }
                R.id.calendar_delete -> {
                    val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.calendar_add_popup, null)
                    val mBuilder = AlertDialog.Builder(requireContext())
                        .setView(mDialogView)
                        .create()
                    mBuilder?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    mBuilder?.window?.requestFeature(Window.FEATURE_NO_TITLE)
                    mBuilder.show()

                    val displayMetrics = DisplayMetrics()
                    requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)

                    val deviceWidth = displayMetrics.widthPixels

                    val desiredRatio = 0.8f
                    val desiredWidth = (deviceWidth * desiredRatio).toInt()
                    mBuilder?.window?.setLayout(desiredWidth, WindowManager.LayoutParams.WRAP_CONTENT)

                    val display = requireActivity().windowManager.defaultDisplay
                    mDialogView.findViewById<TextView>(R.id.textTitle).setText("일정을 삭제하시겠습니까?")
                    mDialogView.findViewById<ImageButton>(R.id.nobutton).setOnClickListener( {
                        mBuilder.dismiss()
                    })
                    mDialogView.findViewById<ImageButton>(R.id.yesbutton).setOnClickListener( {
                        Navigation.findNavController(view).navigate(R.id.action_calendarAddS_to_fragCalendar)
                        mBuilder.dismiss()
                    })
                    true
                }
                else -> false
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        hideBootomNavigation(false)
    }

    fun hideBootomNavigation(bool : Boolean){
        val bottomNavigation = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        if(bool){
            bottomNavigation?.isGone = true
        }
        else {
            bottomNavigation?.isVisible = true
        }
    }
    fun convertToDateKoreanFormat(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-M-d", Locale.getDefault())
        val outputFormat = SimpleDateFormat("M월 d일 (E)", Locale("ko", "KR"))

        val date = inputFormat.parse(dateString)
        return outputFormat.format(date)
    }





}
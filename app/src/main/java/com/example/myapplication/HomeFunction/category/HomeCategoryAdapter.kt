package com.example.myapplication.HomeFunction.category

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class HomeCategoryAdapter(private val dataSet : ArrayList<sampleCategoryData>) : RecyclerView.Adapter<HomeCategoryAdapter.viewHolder>() {

    class viewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val categoryLayout : LinearLayout
        val categoryTextView : TextView
        val categoryImageView : ImageView

        init {
            categoryLayout = view.findViewById(R.id.layout_home_cate_list)
            categoryTextView = view.findViewById(R.id.tv_home_add_category)
            categoryImageView = view.findViewById(R.id.iv_home_add_category)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryAdapter.viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_edit_category_list, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeCategoryAdapter.viewHolder, position: Int) {

        val mGradientDrawable : GradientDrawable = holder.categoryLayout.background as GradientDrawable
        mGradientDrawable.setStroke(6, dataSet[position].color)

        holder.categoryTextView.text = dataSet[position].name
        holder.categoryImageView.setImageResource(dataSet[position].icon)

        holder.categoryLayout.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }


    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener
}
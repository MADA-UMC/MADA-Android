package com.example.myapplication.MyFuction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.MyProfileBinding

class MyProfileActivity : AppCompatActivity() {

    private lateinit var binding: MyProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
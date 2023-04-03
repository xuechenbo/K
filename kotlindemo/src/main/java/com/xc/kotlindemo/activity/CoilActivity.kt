package com.xc.kotlindemo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.xc.kotlindemo.R
import com.xc.kotlindemo.databinding.ActivityCoilBinding

class CoilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.iv.load(R.drawable.image)
    }


}
package com.interview.com.activity

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.Handler.com.databinding.ActivityTouchBinding

class ViewTouchActivity : AppCompatActivity() {
    private val TAG: String = "viewTouch"
    private lateinit var binding: ActivityTouchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTouchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClick()
    }

    private fun initClick() {
        binding.run {
//            btn.setOnClickListener {
//                Log.e(TAG, "setOnClickListener")
//            }
//            btn.setOnTouchListener { _, _ ->
//                true
//            }
            view1.setOnTouchListener { view, motionEvent ->
                Log.e(TAG, "view1")
                false
            }
            view2.setOnTouchListener { view, motionEvent ->
                Log.e(TAG, "view2")
                true
            }
//            view2.setOnClickListener {
//                Log.e(TAG, "view2---setOnClickListener")
//            }
//            view1.setOnClickListener {
//                Log.e(TAG, "view1---setOnClickListener")
//            }

        }
    }

    fun aaa1(v: View){
        Log.e(TAG, "aaa1")
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.e(TAG, "Activity----dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e(TAG, "Activity----onTouchEvent")
        return true

    }
}
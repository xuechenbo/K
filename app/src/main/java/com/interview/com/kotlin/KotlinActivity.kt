package com.interview.com.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.Handler.com.databinding.ActivityKotlinBinding

class KotlinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKotlinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlinBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    fun add1() {
        for (i in 1 until 25 step 3) {

        }
        for (i in 1..15 step 2) {

        }
        for (i in 50 downTo 0) {

        }

        listOf<String>().filter { it ->
            it.length > 5
        }
        listOf<String>().map {

        }

        (1..10).forEach { _ ->

        }
        val p: String by lazy {
            "22323"
        }
        val dd = object : MyAbstractClass() {
            override fun doSomething() {
            }

            override fun sleep() {
                TODO("Not yet implemented")
            }
        }

    }

    //单例
    object aaaa {
        val name = 1
    }

    abstract class MyAbstractClass {
        abstract fun doSomething()
        abstract fun sleep()
    }

    fun String.Add11(): Int {
        return this.length
    }

    fun add(a: Int): Int {
        return a
    }

    fun add(): Int = 1

    fun maxValue(a: Int, b: Int) = if (a > b) a else b


    class perSon {
        constructor()
        constructor(aaa: String, bbb: String = "") : this() {

        }

        init {

        }

    }

    fun getPer() {
        perSon("")
    }



}
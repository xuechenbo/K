package com.xc.kotlindemo.activity

import android.os.Bundle
import android.view.View
import com.xc.common_base.u.base.BaseActivity
import com.xc.kotlindemo.databinding.ActivityFunBinding
import com.xc.kotlindemo.vm.FunViewModel

class FunActivity : BaseActivity<FunViewModel>() {
    private lateinit var binding: ActivityFunBinding
    override fun viewModelClass(): Class<FunViewModel> = FunViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFunBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //一个高阶函数
        val intArray = intArrayOf(1, 2, 3, 4, 5)
//        val action: (Int) -> Unit = ::printValue
        val action: (Int) -> Unit = { value: Int -> print(value) }
        val action1: (Int) -> Unit = { print(it) }

        intArray.forEach(action)
        intArray.filter {
            it > 5
        }.map {
            if (it > 5) {
                "22312"
            }
        }


        //定义一个函数类型的变量
        var addAction: (Int, Int) -> String = ::addTest
        //调用
        getA(addAction, "abc", 1)


        val p8: Function3<Int, Int, Int, Int> = { p, t, d ->
            p + t
        }
        getPro("112") {
            binding.btn.text = "okkk"
        }
    }

    public inline fun IntArray.dddddd(action: (Int) -> Unit): Unit {
        for (element in this) action(element)
    }

    val a = fun(a: Int) {

    }
    val ab = {

    }
    val abc: (Int) -> Unit = {

    }

    private fun printValue(value: Int): Unit {
        println(value)
    }

    //函数类型就是将函数的参数和返回值抽象出来
    //函数类型为  (int,int)->int
    fun addTest(num1: Int, num2: Int): String {
        return "${num1 + num2}"
    }

    //函数类型为  ()->Unit
    private fun funTest() {}

    //接收参数是函数
    fun getA(add: (Int, Int) -> String, string: String, int: Int): String {
        return ""
    }

    //
//    //返回值是函数
//    fun funadd(a: Int, b: Int): (Int) -> Unit {
//
//    }
    data class Book(
        var name: String,
        var from: (Int, Int) -> String
    )

    //点击事件 1--8
    fun click() {
        binding.run {
            //
            //1 本质是用 object 关键字定义了一个匿名内部类
            btn.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {

                }
            })
            //2 删掉 object 关键字，它就是 Lambda 表达式了，因此它里面 override 的方法也要跟着删掉
            btn.setOnClickListener(View.OnClickListener {

            })
            //3 Lambda 表达式是不需要 SAM Constructor的，所以它也可以被删掉
            btn.setOnClickListener({ view: View? ->

            })

            //4 由于 Kotlin 支持类型推导，所以 View? 可以被删掉
            btn.setOnClickListener({ view ->

            })
            //5 当 Kotlin Lambda 表达式只有一个参数的时候，它可以被写成 it
            btn.setOnClickListener({ it ->

            })
            //6 Kotlin Lambda 的 it 是可以被省略的
            btn.setOnClickListener({

            })
            //7 当 Kotlin Lambda 作为函数的最后一个参数时，Lambda 可以被挪到外面
            btn.setOnClickListener() {

            }
            //8  Kotlin 只有一个 Lambda 作为函数参数时，() 可以被省略
            btn.setOnClickListener {

            }
        }
    }


}
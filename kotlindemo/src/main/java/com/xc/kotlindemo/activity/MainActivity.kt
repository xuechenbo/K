package com.xc.kotlindemo.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xc.common_base.u.base.BaseActivity
import com.xc.kotlindemo.LeetCodeJava
import com.xc.kotlindemo.R
import com.xc.kotlindemo.databinding.ActivityMainBinding
import com.xc.kotlindemo.vm.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch


class MainActivity : BaseActivity<MainViewModel>() {
    //使用官方库的 MainScope()获取一个协程作用域用于创建协程
    private lateinit var binding: ActivityMainBinding
    override fun viewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obser()
        binding.recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycler.adapter = MyAdapter()


        //
    }

    class Example {

    }

    class Delegate {

    }


    class MyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        private val mList: List<String>? = null
        private val mContext: Context? = null
        fun MyAdapter(mList: List<String?>, mContext: Context) {
            //大部分只有数据与上下文
//            mList = mList
//            mContext = mContext
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            TODO("Not yet implemented")
            //主要负责绑定数据
            //主要负责绑定数据
//            (holder as ContentViewHolder).tvItem.text = mList!![position]
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            TODO("Not yet implemented")
//            val view: View = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item, viewGroup, false)
//            return ContentViewHolder(view)
        }

        override fun getItemCount(): Int {
            return 5
        }

        internal class ContentViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
//            private val tvItem: TextView
//            private val llLayout: LinearLayout

            init {
//                tvItem = itemView.findViewById<View>(R.id.tv_item) as TextView
//                llLayout = itemView.findViewById<View>(R.id.ll_layout) as LinearLayout
            }
        }
    }

    fun test() {
        lifecycleScope.launch {
            flow {
                for (i in 1..3) {
                    delay(100)
                    //发射数据
                    emit(i)
                }
            }.collect { value -> Log.d("TAG", "value :${value}") }
        }
    }

    fun testFlow2() {
        val list = listOf(1, 2, 3, 4)
        val asFlow = list.asFlow()
        val testFlow = flowOf(65, 66, 67)
        lifecycleScope.launch {
            testFlow.collect {
                println("输出：$it")
                println()
            }

            asFlow.collect {

            }
        }
    }

    private fun obser() {
        binding.run {
            btn.setOnClickListener {
//                mViewModel.getList()
                val lengthOfLongestSubstring22 = LeetCodeJava.lengthOfLongestSubstring22("pwwkew")
                Log.e("tag", "$lengthOfLongestSubstring22")
            }
        }
        mViewModel?.run {
            logtType.observeForever {
                Log.e("logtType", "$it")
            }
            loadingLiveData.observeForever {
                if (it) {
                    Log.e("loadingLiveData", "请求开始")
                } else {
                    Log.e("loadingLiveData", "请求结束")
                }
            }
            errorLiveData.observeForever {
                Log.e("errorLiveData", "$it")
            }
        }
        lifecycleScope.launchWhenCreated {
            mViewModel.stateFlow.collect {
                Log.e("ttttt", it.toString())
            }
        }
        mViewModel.SendFlow()

    }

    //Repository层
    private fun log(msg: Any?) = println("[${Thread.currentThread().name}] $msg")


}
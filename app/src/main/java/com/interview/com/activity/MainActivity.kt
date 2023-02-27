package com.interview.com.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.interview.com.IMyAidlInterface
import com.interview.com.databinding.ActivityMainBinding
import com.interview.com.serview.AAservice
import com.interview.com.serview.AidlServiced
import com.interview.com.serview.MusicPlayerService
import com.interview.com.vm.LoginViewModel


class MainActivity : AppCompatActivity() {
    private var mBinder: AAservice.MyBinder? = null
    private var mIMyAidlInterface: IMyAidlInterface? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        initBindService()
    }

    private fun initBindService() {
        binding.run {
            bindService.setOnClickListener {
                val mIntent = Intent(this@MainActivity, AAservice::class.java)
                bindService(mIntent, mConn, BIND_AUTO_CREATE)
            }
            IntentService.setOnClickListener {
                MusicPlayerService.startPlayer(this@MainActivity)
            }
            AIDLservice.setOnClickListener {
                val intent = Intent(this@MainActivity, AidlServiced::class.java)
                // 绑定服务时自动创建服务
                bindService(intent, mAidl, Context.BIND_AUTO_CREATE)
            }
            BtnAidl.setOnClickListener {
                mIMyAidlInterface?.start(1)
                mIMyAidlInterface?.getNum(2, 4)
            }
        }

        loginViewModel.run {
            tag.observeForever {

            }
//            tag.observe(this@MainActivity){
//                // ......
//            }
        }
    }

    private val mAidl: ServiceConnection = object : ServiceConnection {
        //Activity与Service连接成功时回调该方法
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Log.i("TAG", "onServiceConnected")
            mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service)

        }

        //Activity与Service断开连接时回调该方法
        override fun onServiceDisconnected(name: ComponentName) {
            Log.i("TAG", "onServiceDisconnected")
        }
    }


    private val mConn: ServiceConnection = object : ServiceConnection {
        //Activity与Service连接成功时回调该方法
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Log.i("TAG", "onServiceConnected")
            mBinder = service as AAservice.MyBinder
            val count = mBinder!!.getCount()
            Log.i("TAG", "$count")
        }

        //Activity与Service断开连接时回调该方法
        override fun onServiceDisconnected(name: ComponentName) {
            Log.i("TAG", "onServiceDisconnected")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("TAG", "onDestroy")
        unbindService(mConn)
    }


}


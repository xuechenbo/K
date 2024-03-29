package com.interview.com.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Parcel
import android.os.RemoteException
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.interview.com.databinding.ActivityMainBinding
import com.interview.com.IMyAidlInterface
import com.interview.com.serview.AAservice
import com.interview.com.aidl.AidlServiced
import com.interview.com.serview.MusicPlayerService
import com.interview.com.vm.LoginViewModel


class MainActivity : AppCompatActivity() {
    private var mBinder: AAservice.MyBinder? = null
    private var mIMyAidlInterface: IMyAidlInterface? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var mPlusBinde: IBinder
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
                mIMyAidlInterface?.run {
                    start(1)
                    getNum(2, 4)
                    val add = add(1, 3)
                    Log.e("TAG", add.toString())
                    val j = j(8, 2)
                    Log.e("TAG", j.toString())
                }
            }
            noAidl.setOnClickListener {
                val intent = Intent()
                intent.action = "com.interview.com.calcplus"
                intent.`package` = "com.interview.com.serview"
                var boolean = bindService(intent, mServiceConnPlus, Context.BIND_AUTO_CREATE)
                Log.e("plus", boolean.toString())
            }
            add.setOnClickListener {
                aaa()
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

    //调用方法
    private fun aaa() {
        if (mPlusBinde == null) {
            Toast.makeText(this@MainActivity, "未连接服务端或服务端被异常杀死", Toast.LENGTH_SHORT).show()
        } else {
            val _data = Parcel.obtain()
            val _reply = Parcel.obtain()
            val _result: Int
            try {
                _data.writeInterfaceToken("CalcPlusService")
                _data.writeInt(50)
                _data.writeInt(12)
                mPlusBinde.transact(0x110, _data, _reply, 0)
                _reply.readException()
                _result = _reply.readInt()
                Toast.makeText(this, _result.toString() + "", Toast.LENGTH_SHORT).show()
            } catch (e: RemoteException) {
                e.printStackTrace()
            } finally {
                _reply.recycle()
                _data.recycle()
            }
        }
    }

    //无AIDL通讯
    private val mServiceConnPlus: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            Log.e("client", "mServiceConnPlus onServiceDisconnected")
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Log.e("client", " mServiceConnPlus onServiceConnected")
            mPlusBinde = service
        }
    }


    //TODO AIDL 测试
    private val mAidl: ServiceConnection = object : ServiceConnection {
        //Activity与Service连接成功时回调该方法
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Log.i("TAG", "onServiceConnected")
            mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service)

        }

        //Activity与Service断开连接时回调该方法
        override fun onServiceDisconnected(name: ComponentName) {
            Log.i("TAG", "onServiceDisconnected")
            mIMyAidlInterface = null;
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


package com.xc.kotlindemo.aidlservice.binder;

import static com.xc.kotlindemo.aidlservice.binder.GradeService.REQUEST_CODE;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

//手动binder机制
public class BinderProxy implements IGradeInterface {
    // 被代理的Binder
    private final IBinder mBinder;
    // 私有化构造方法
    private BinderProxy(IBinder binder) {
        mBinder = binder;
    }

    // 通过Binde读取成绩
    @Override
    public int getStudentGrade(String name) {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        int grade = 0;
        data.writeString(name);
        try {
            if (mBinder == null) {
                throw new IllegalStateException("Need Bind Remote Server...");
            }
            mBinder.transact(REQUEST_CODE, data, reply, 0);
            grade = reply.readInt();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return grade;
    }

    // 实例化Binder代理类的对象
    public static IGradeInterface asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        if (iBinder instanceof IGradeInterface) {
            Log.e("当前进程","-----------");
            // 如果是同一个进程的请求，则直接返回Binder
            return (IGradeInterface) iBinder;
        } else {
            Log.e("远程进程","-----------");
            // 如果是跨进程查询则返回Binder的代理对象
            return new BinderProxy(iBinder);
        }
    }

}

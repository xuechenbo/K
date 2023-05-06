package com.xc.kotlindemo.aidlservice.binder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//手动binder机制
public class GradeService extends Service {
    public static final int REQUEST_CODE = 1000;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new myBinder();
    }

    private Binder mBinder = new Binder() {
        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            return super.onTransact(code, data, reply, flags);
        }

    };

    class myBinder extends Binder implements IGradeInterface {

        @Override
        public int getStudentGrade(String name) {
            return 0;
        }

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            if (code == REQUEST_CODE) {
                String name = data.readString();
                int studentGrade = getStudentGrade(name);
                if (reply != null)
                    reply.writeInt(studentGrade);
                return true;
            }
            return super.onTransact(code, data, reply, flags);
        }


    }
}

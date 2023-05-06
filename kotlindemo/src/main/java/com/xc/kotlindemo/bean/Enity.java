package com.xc.kotlindemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Enity implements Parcelable {
    public String num;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.num);
    }

    public void readFromParcel(Parcel source) {
        this.num = source.readString();
    }

    public Enity() {

    }

    protected Enity(Parcel in) {
        this.num = in.readString();
    }

    public static final Parcelable.Creator<Enity> CREATOR = new Parcelable.Creator<Enity>() {
        @Override
        public Enity createFromParcel(Parcel source) {
            return new Enity(source);
        }

        @Override
        public Enity[] newArray(int size) {
            return new Enity[size];
        }
    };
}

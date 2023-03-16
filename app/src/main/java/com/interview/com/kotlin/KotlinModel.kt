package com.interview.com.kotlin

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData

data class KotlinModel(
    var mBitmap: Bitmap,
    var request: Int,
    val topHeight: MutableLiveData<Int> = MutableLiveData(),
) {
    init {
        request = 1231
        topHeight.observeForever {

        }
    }
}

//幕后字段 field
class person {
    val age: Int get() = this.age
    var nam: String = ""
        set(value) {
            if (value.length > 5) {
                field = ""
            } else {
                field = value
            }
        }
}


abstract class Polygon {
    abstract fun add()
}

class aaa : Polygon(), color {
    override fun add() {

    }

    override fun foo() {


    }

    override fun foo1() {
        super.foo1()
    }
}

class empty

interface color {
    fun foo()
    fun foo1() {

    }
}

fun <T> singletonList(item: T): List<T> {
    return listOf<T>()
}






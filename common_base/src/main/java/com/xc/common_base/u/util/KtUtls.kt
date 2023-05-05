package com.xc.common_base.u.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Checkable
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat


//TODO 扩展函数
//时间戳转日期字符串
fun Long.toYMD(): String = SimpleDateFormat("yyyy-MM-dd").format(this)

//日期字符串转时间戳
fun String.getTimeCurr(): Long = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this).time

fun String.getTimeCurrYMD(): Long = SimpleDateFormat("yyyy-MM-dd").parse(this).time


inline fun <reified T : Any> getList(json: String): T {
    return Gson().fromJson(json, object : TypeToken<T>() {}.type)
}

//TODO 关键字 inline内联函数
// 不在传入 T 的class，inline 的作用就是将函数插入到被调用处，配合 reified 就可以获取到 T 真正的类型
inline fun <reified T : Any> fromJson(json: String): T {
    return Gson().fromJson(json, T::class.java)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.onClickBind(click: View.OnClickListener, vararg views: View) {
    for(view in views){
        view.setOnClickListener(click)
    }
}

inline fun <T : View> T.singleClick(time: Long = 800, crossinline block: (T) -> Unit) {
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastClickTime > time || this is Checkable) {
            lastClickTime = currentTimeMillis
            block(this)
        }
    }
}
//兼容点击事件设置为this的情况
fun <T : View> T.singleClick(onClickListener: View.OnClickListener, time: Long = 800) {
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastClickTime > time || this is Checkable) {
            lastClickTime = currentTimeMillis
            onClickListener.onClick(this)
        }
    }
}

var <T : View> T.lastClickTime: Long
    set(value) = setTag(1766613352, value)
    get() = getTag(1766613352) as? Long ?: 0











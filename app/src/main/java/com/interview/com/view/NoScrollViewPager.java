package com.interview.com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class NoScrollViewPager extends ViewPager {
    private boolean isScroll = true;
    private float rawX;
    private float rawY;

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }

    /**
     * 1.dispatchTouchEvent一般情况不做处理
     * ,如果修改了默认的返回值,子孩子都无法收到事件
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("viewTouch", "NoScrollView-----dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);   // return true;不行
    }

    /**
     * 是否拦截
     * 拦截:会走到自己的onTouchEvent方法里面来
     * 不拦截:事件传递给子孩子
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("viewTouch", "NoScrollView-----onInterceptTouchEvent");
        // return false;//可行,不拦截事件,
        // return true;//不行,孩子无法处理事件
        //return super.onInterceptTouchEvent(ev);//不行,会有细微移动
        int action = MotionEvent.ACTION_MASK & ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                rawX = ev.getRawX();
                rawY = ev.getRawY();
                Log.d("viewTouch", "onInterceptTouchEvent   " + "MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                float downX = ev.getRawX();
                float downY = ev.getRawY();
                float dx = Math.abs(downX - rawX);
                float dy = Math.abs(downY - rawY);
                if (dy > dx) {
                    Log.d("viewTouch", "onInterceptTouchEvent   " + "MotionEvent.ACTION_MOVE");
                    return onTouchEvent(ev);
                } else {
                    return super.onInterceptTouchEvent(ev);
                }
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 是否消费事件
     * 消费:事件就结束
     * 不消费:往父控件传
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e("viewTouch", "NoScrollView----onTouchEvent");
        //return false;// 可行,不消费,传给父控件
        //return true;// 可行,消费,拦截事件
        //super.onTouchEvent(ev); //不行,
        //虽然onInterceptTouchEvent中拦截了,
        //但是如果viewpage里面子控件不是viewgroup,还是会调用这个方法.
//        if (isScroll) {
//            return true;// 可行,消费,拦截事件
//        } else {
//            return super.onTouchEvent(ev);
//        }
        final int action = ev.getAction() & MotionEvent.ACTION_MASK;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                rawX = ev.getRawX();
                rawY = ev.getRawY();
                Log.d("viewTouch", "onTouchEvent   " + "MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("viewTouch", "onTouchEvent   " + "MotionEvent.ACTION_MOVE");
                float downX = ev.getRawX();
                float downY = ev.getRawY();
                float dx = Math.abs(downX - rawX);
                float dy = Math.abs(downY - rawY);
                if (true) {
                    Log.d("viewTouch", "onTouchEvent----消费事件");
                    return true;//表明事件被消费了
                } else {
                    return super.onTouchEvent(ev);
                }
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                Log.d("viewTouch", "onTouchEvent   " + "ACTION_CANCEL:\n" + "ACTION_UP:");
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void setScroll(boolean scroll) {
        isScroll = scroll;
    }
}

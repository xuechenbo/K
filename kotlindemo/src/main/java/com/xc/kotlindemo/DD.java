package com.xc.kotlindemo;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DD {
    /**
     * 首先声明一个接口，用于工作的接口
     */
    public interface IStarDao {
        void dowork();
    }

    /**
     * 明星工作类
     * 主要为演戏
     */
    public class SuperStarDao implements IStarDao {
        @Override
        public void dowork() {
            //演戏工作
            Log.e("tag", "明星工作了");
        }
    }

    /**
     * 经纪人代理类
     * 主要是负责接活，并且安排明星工作，以及后续宣传工作
     */
    public class StarbrokerDaoProxy implements IStarDao {

        private IStarDao starDao;

        public StarbrokerDaoProxy(IStarDao starDao) {
            this.starDao = starDao;
        }

        @Override
        public void dowork() {
            /*--接活--*/
            starDao.dowork();//明星工作
            /*--宣传工作--*/
        }
    }

    void main() {
        SuperStarDao superStarDao = new SuperStarDao();
        StarbrokerDaoProxy starbrokerDaoProxy = new StarbrokerDaoProxy(superStarDao);
        starbrokerDaoProxy.dowork();
    }

    public void mainDD() {
        final IStarDao starDao = new SuperStarDao();

        IStarDao proxy = (IStarDao) Proxy.newProxyInstance(
                starDao.getClass().getClassLoader(),
                starDao.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        /*--接活--*/
                        Object returnValue = method.invoke(starDao, args);//明星工作
                        /*--宣传工作--*/
                        return returnValue;
                    }
                });

        proxy.dowork();
    }


}

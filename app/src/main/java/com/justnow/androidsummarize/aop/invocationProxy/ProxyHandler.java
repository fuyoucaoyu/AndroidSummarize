package com.justnow.androidsummarize.aop.invocationProxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理：动态代理是一种设计模式。它有以下特征：
 *      1. 我们不需要自己写代理类。
 *      2. 运行期通过接口直接生成代理对象。
 *      3. 运行期间才确定代理哪个对象。
 * InvocationHandler 是 JDK 提供的动态代理的入口，用来对被代理对象的方法做处理。
 *
 *
 * IUserSetting source = new UserSetting();
 * IUserSetting iUserSetting = ProxyHandler.proxy(source, IUserSetting.class);
 * iUserSetting.changePwd("new Password");
 */
public class ProxyHandler implements InvocationHandler {
    private final static String TAG = "ProxyHandler";

    private static <S, T extends S> T proxy(S source, Class<T> tClass) {
        return (T) Proxy.newProxyInstance(
                InvocationHandler.class.getClassLoader(),
                new Class[]{tClass},
                new ProxyHandler(source));
    }

    private Object mSource;

    private ProxyHandler(Object source) {
        this.mSource = source;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!isLogin()) {
            jumpToLoginActivity();
        }

        return method.invoke(mSource, args);
    }

    private boolean isLogin() {
        return false;
    }

    private void jumpToLoginActivity() {
        Log.d(TAG, "Jump to login activity");
    }
}

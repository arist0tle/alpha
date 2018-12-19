package com.haizhi.observer;

/* Created by Haiyang on 2018/2/28. */


import com.haizhi.log.Log;

public class Main {
    public static void main(String[] args) {
        // Publish是被观察者，继承了Observable类
        Publish publish = new Publish();

        // 将被观察者加入监视范围，实现了Observer接口，Observer监视Observable
        // 当Observable对象发生变化时，Observer得到通知
        // 实际上这一步是通过Observable的addObserver传入一个Observer，来初始化Observable的，
        // 将publish这个Observer放入监视器里面，说白了就是publish为主动为自己找了一个监护人
        // 但是这个写法的怪异之处在于publish是作为参数传入给Subscribe，
        // 而实际实现是把Subscribe的实例传给publish
        Subscribe subscribe = new Subscribe(publish);

        // 然后publish(Observable)开始更新，并触发一个通告
        // notifyObservers -> notifyObservers（null）
        publish.setData("开始");

        Log.logger.info("getData:{}", publish.getData());
        publish.setData("1111");
    }
}

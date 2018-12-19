package com.haizhi.observer2;

/* Created by Haiyang on 2018/2/28. */

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        // 一个小孩找监护人，一个小孩做事情，被监护
        // 这么些代码的好处是好理解观察者模式，主语是被观察者即小孩
        // 一个小孩可以找多个监护人嘛，还是一个监护人照顾多个小孩呢
        // 显然是后者更符合伦理，所以一般不这么写
        // 根本原因是：观察者模式的场景是一个观察者去观察多个事物，统一调用update方法做出反应
        Subscribe subscribe = new Subscribe();
        Publish publish = new Publish(subscribe);

        // 然后publish(Observable)开始更新，并触发一个通告
        // notifyObservers -> notifyObservers（null）
        publish.setData("开始");

        log.info("getData:{}", publish.getData());
        publish.setData("1111");
    }
}

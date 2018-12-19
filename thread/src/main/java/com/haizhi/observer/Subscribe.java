package com.haizhi.observer;

/* Created by Haiyang on 2018/2/28. */


import java.util.Observable;
import java.util.Observer;

public class Subscribe implements Observer {
    public Subscribe(Observable o){
        o.addObserver(this);
    }

    @Override
    public void update(Observable o,Object arg){
        System.out.println("收到通知:" + ((Publish)o).getData());
    }
}

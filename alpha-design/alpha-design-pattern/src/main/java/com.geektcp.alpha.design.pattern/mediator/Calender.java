package com.geektcp.alpha.design.pattern.mediator;

/**
 * Created by TangHaiyang on 2019/9/21.
 */
public class Calender extends Colleague {
    @Override
    public void onEvent(Mediator mediator) {
        mediator.doEvent("calender");
    }

    public void doCalender() {
        System.out.println("doCalender()");
    }
}

package com.geektcp.alpha.design.pattern.mediator;

/**
 * Created by TangHaiyang on 2019/9/21.
 */
public class CoffeePot extends Colleague {
    @Override
    public void onEvent(Mediator mediator) {
        mediator.doEvent("coffeePot");
    }

    public void doCoffeePot() {
        System.out.println("doCoffeePot()");
    }
}

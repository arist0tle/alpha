package com.geektcp.alpha.design.pattern.singleton;

/**
 * Created by TangHaiyang on 2019/9/21.
 */
public class HolderSingleton {
    private HolderSingleton() {
    }

    private static class Holder {
        private static final HolderSingleton INSTANCE = new HolderSingleton();
    }

    public static HolderSingleton getUniqueInstance() {
        return Holder.INSTANCE;
    }
}

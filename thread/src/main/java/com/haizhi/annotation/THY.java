package com.haizhi.annotation;

/* Created by Haiyang on 2017/11/20. */


public @interface THY {
    String value();

    public enum FontColor {RED, GREEN, BLUE};

    String name();

    String content();

    FontColor fontColor() default FontColor.BLUE;
}

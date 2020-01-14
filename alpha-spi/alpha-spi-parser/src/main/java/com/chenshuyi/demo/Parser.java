package com.chenshuyi.demo;

/**
 * @author tanghaiyang on 2020/1/14.
 */
public interface Parser {

    Song parse(byte[] data) throws Exception;

}

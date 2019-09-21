package com.geektcp.alpha.design.pattern.iterator;

/**
 * Created by TangHaiyang on 2019/9/21.
 */
public class IteratorTest {
    public static void main(String[] args) {
        Aggregate aggregate = new ConcreteAggregate();
        Iterator<Integer> iterator = aggregate.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

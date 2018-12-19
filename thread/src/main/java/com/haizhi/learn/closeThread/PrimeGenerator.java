package com.haizhi.learn.closeThread;

/* Created by Haiyang on 2017/3/16. */
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.Immutable;
import net.jcip.annotations.ThreadSafe;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

@ThreadSafe
@Immutable
public class PrimeGenerator implements Runnable {
    @GuardedBy("this")
    private final List<BigInteger> primes = new ArrayList<BigInteger>();

    private volatile boolean cancelled;

    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled){
            p = p.nextProbablePrime();
            synchronized (this){
                primes.add(p);
            }
        }
    }

    public void cancel(){ cancelled = true; }

    public synchronized List<BigInteger> get() {
        return new ArrayList<BigInteger>(primes);
    }

    List<BigInteger> aSecondOfPrimes() throws InterruptedException{
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        try{
            SECONDS.sleep(1);
        } finally {
            generator.cancel();
        }
        return generator.get();
    }

    public static void main(String[] args) throws Exception {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        try{
            SECONDS.sleep(1);
        } finally {
            generator.cancel();
        }
        System.out.println(generator.get());
    }


}


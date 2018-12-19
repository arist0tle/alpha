package com.haizhi.learn.sector1;

/* Created by Haiyang on 2017/11/4. */

public class MyThread4 extends Thread {
    @Override
    public void run() {
        while (true) {
            try {
                // 调用会阻塞的函数...
                Thread.sleep(5000);
            } catch (InterruptedException ie) {
                // InterruptedException在while(true)循环体内。
                // 当线程产生了InterruptedException异常时, while(true)仍能继续运行！需要手动退出
                break;
            }
        }
    }


    public static void main(String[] args) {
        Thread t1 = new MyThread4();
        t1.start();
        t1.interrupt();
        System.out.println(t1.getName() +"|" + t1.getState() + " is interrupted.");

    }

}

package com.haizhi.thread.thread;

/* Created by Haiyang on 2017/3/16. */



class hello_thread extends Thread {
    private String name;

    public hello_thread(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行\t" + i);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        hello_thread h1 = new hello_thread("A");
        hello_thread h2 = new hello_thread("B");

        h1.start();
        System.out.println("==============");
        h2.start();

        try {
            h1.join();
            h2.join();
        }catch (Exception e){
            e.printStackTrace();
        }



//        h1.stop();
//        h2.stop();
    }

}

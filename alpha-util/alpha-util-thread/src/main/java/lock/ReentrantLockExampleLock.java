package lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by TangHaiyang on 2019/8/20.
 *  lock方法不支持interrupt
 */
public class ReentrantLockExampleLock implements Runnable{

    //重入锁ReentrantLock
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    int lock;
    public ReentrantLockExampleLock(int lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            if(lock==1){
                lock1.lock();
                System.out.println("线程1");
                Thread.sleep(1000);
                lock2.lock();
                System.out.println("线程1执行完毕: " + Thread.currentThread().getId());
            }else{
                lock2.lock();
                System.out.println("线程2");
                Thread.sleep(1000);
                lock1.lock();
                System.out.println("线程2执行完毕: " + Thread.currentThread().getId());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            if(lock1.isHeldByCurrentThread()){
                lock1.unlock();//释放锁
            }
            if(lock2.isHeldByCurrentThread()){
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId() + "：线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockExampleLock r1 = new ReentrantLockExampleLock(1);
        ReentrantLockExampleLock r2 = new ReentrantLockExampleLock(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        Thread.sleep(5000);
        System.out.println("主线程等待5s后");
        // lock方法不支持interrupt，无论用这个方法中断哪个线程，都没有用。这就是lock和lockInterruptibly的区别
        t2.interrupt();
        System.out.println("查看中断效果");
    }

}

package utils;

import java.util.concurrent.*;

public class MyThreadPoolExecutor {

    public static void main(String[] args) {

        ArrayBlockingQueue<Runnable> strings = new ArrayBlockingQueue(10);
        ThreadPoolExecutor threadProolExecutor = new ThreadPoolExecutor(1,2,2, TimeUnit.MINUTES, strings);

        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService1 = Executors.newFixedThreadPool(10);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        PriorityBlockingQueue mQueue = new PriorityBlockingQueue();

        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();


    }

}

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

    private static void createSingleThreadPool() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                // 获取线程名称,默认格式:pool-1-thread-1
                System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + " " + index);
                // 等待2秒
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

}

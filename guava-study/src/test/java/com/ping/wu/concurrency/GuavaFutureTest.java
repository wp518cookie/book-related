package com.ping.wu.concurrency;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Mengxg on 2019/5/30
 */
public class GuavaFutureTest {

    static final ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(100));

    @Test
    public void testGuava() {
        long start = System.currentTimeMillis();
        List<Integer> list = Lists.newArrayListWithCapacity(1);
        List<ListenableFuture<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            ListenableFuture<Integer> future = service.submit(new GuavaTask(i));
            futures.add(future);
        }
        futures.forEach(f -> Futures.addCallback(f, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(@Nullable Integer var1) {

            }

            @Override
            public void onFailure(Throwable var1) {

            }
        }, service));
    }

    @Test
    public void testGuava2() {
        List<Integer> list = Lists.newArrayListWithCapacity(1);
        ExecutorService service = Executors.newCachedThreadPool();
        List<FutureTask<Integer>> futureTasks = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            futureTasks.add(new FutureTask(new GuavaTask(i)));
        }
        long start = System.currentTimeMillis();
        futureTasks.forEach(service::submit);
        while (true) {
            if (service.isTerminated()) {
                futureTasks.forEach(f -> {
                    try {
                        list.add(f.get());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                break;
            }
        }
        System.out.println("time: " + (System.currentTimeMillis() - start));
        list.forEach(System.out::println);
    }

    public class GuavaTask implements Callable<Integer> {

        private int num;

        public GuavaTask(int num) {
            this.num = num;
        }

        @Override
        public Integer call() throws Exception {
//            int i = (int) (Math.random() * 10 + num);
//            Thread.sleep(i);
            return num;
        }
    }
}

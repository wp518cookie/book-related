package com.ping.wu.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.concurrent.TimeUnit;

/**
 * @author wuping
 * @date 2020-08-20
 */

public class GetCityNameCommand extends HystrixCommand<String> {
    private Long cityId;

    public GetCityNameCommand(Long cityId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetCityNameGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(1)
                )
        );
        this.cityId = cityId;
    }

    @Override
    protected String run() throws Exception {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (Exception e) {

        }
        return LocationCache.getCityName(cityId);
    }

    @Override
    protected String getFallback() {
        System.out.println("执行了回退方法");
        return "无法获取城市名称";
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    GetCityNameCommand cityNameCommand = new GetCityNameCommand(1L);
                    System.out.println(Thread.currentThread().getName() + " : " + cityNameCommand.execute());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {

                    }
                }
            }).start();
        }
    }
}

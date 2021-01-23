package com.ping.wu.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @author wuping
 * @date 2020-08-19
 */

public class HelloCircuitBreakCommand extends HystrixCommand<String> {
    protected HelloCircuitBreakCommand() {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("testCircuitBreak"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerEnabled(true)
                        .withCircuitBreakerErrorThresholdPercentage(30)
                        .withCircuitBreakerRequestVolumeThreshold(10)
                        .withCircuitBreakerSleepWindowInMilliseconds(3000)
                )
        );
    }

    @Override
    protected String run() throws Exception {
        System.out.println("执行了run方法");
        Thread.sleep(2000);
        return "success";
    }

    @Override
    protected String getFallback() {
        System.out.println("执行了回退方法");
        return "error";
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            HelloCommand command = new HelloCommand();
            String result = command.execute();
            System.out.println("circuit Breaker is open : " + command.isCircuitBreakerOpen());
            if (command.isCircuitBreakerOpen()) {
                Thread.currentThread().sleep(500);
            }
        }
    }
}

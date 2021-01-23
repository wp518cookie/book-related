package com.ping.wu.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author wuping
 * @date 2020-08-19
 */

public class HelloCommand extends HystrixCommand<String> {
    protected HelloCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("test"));
    }

    @Override
    protected String run() throws Exception {
        Thread.sleep(5000);
        return "success";
    }

    @Override
    protected String getFallback() {
        System.out.println("执行了回退方法");
        return "error";
    }

    public static void main(String[] args) {
        HelloCommand helloCommand = new HelloCommand();
        String result = helloCommand.execute();
        System.out.println(result);
    }
}

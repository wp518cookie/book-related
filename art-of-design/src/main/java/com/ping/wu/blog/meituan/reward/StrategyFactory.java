package com.ping.wu.blog.meituan.reward;

/**
 * @author wuping
 * @date 2021-01-12
 */

public abstract class StrategyFactory<T> {
    abstract RewardStrategy createStrategy(Class<T> c);
}

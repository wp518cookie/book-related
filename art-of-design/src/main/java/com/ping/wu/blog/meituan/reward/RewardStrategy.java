package com.ping.wu.blog.meituan.reward;

/**
 * @author wuping
 * @date 2021-01-12
 */

public abstract class RewardStrategy {
    public abstract int reward(long userId);

    public void insertRewardAndSettlement(long userId, int reward) {

    }
}

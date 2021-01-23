package com.ping.wu.blog.meituan.reward;

/**
 * @author wuping
 * @date 2021-01-12
 */

public class RewardContext {
    private RewardStrategy strategy;

    public RewardContext(RewardStrategy strategy) {
        this.strategy = strategy;
    }

    public void doStrategy(long userId) {
        int rewardMoney = strategy.reward(userId);
        strategy.insertRewardAndSettlement(userId, rewardMoney);
    }
}

package com.ping.wu.blog.meituan.reward.rewardstate;

/**
 * @author wuping
 * @date 2021-01-13
 */

public abstract class RewardState {
    abstract void doReward(RewardStateContext context, Request request);
}

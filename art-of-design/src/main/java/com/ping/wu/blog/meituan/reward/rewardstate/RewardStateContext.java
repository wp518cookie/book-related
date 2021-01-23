package com.ping.wu.blog.meituan.reward.rewardstate;

/**
 * @author wuping
 * @date 2021-01-13
 */

public class RewardStateContext {
    private RewardState rewardState;

    public void setRewardState(RewardState currentState) {
        this.rewardState = currentState;
    }

    public RewardState getRewardState() {
        return rewardState;
    }

    public void echo(RewardStateContext context, Request request) {
        rewardState.doReward(context, request);
    }
}

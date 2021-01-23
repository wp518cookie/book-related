package com.ping.wu.blog.meituan.reward.rewardstate;

/**
 * @author wuping
 * @date 2021-01-23
 */

public class CompensateRewardState extends RewardState {
    @Override
    void doReward(RewardStateContext context, Request request) {
        compensateReward(context, request);
    }

    private void compensateReward(RewardStateContext context, Request request) {

    }
}

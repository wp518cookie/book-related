package com.ping.wu.blog.meituan.reward.rewardstate;

/**
 * @author wuping
 * @date 2021-01-23
 */

public class OrderCheckState extends RewardState {

    @Override
    void doReward(RewardStateContext context, Request request) {
        orderCheck(context, request);
    }

    private void orderCheck(RewardStateContext context, Request request) {

    }
}

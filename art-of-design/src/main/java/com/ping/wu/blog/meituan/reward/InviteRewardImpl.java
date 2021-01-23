package com.ping.wu.blog.meituan.reward;

import org.apache.commons.lang3.RandomUtils;

/**
 * @author wuping
 * @date 2021-01-12
 */

public class InviteRewardImpl {
    public void sendReward(long userId) {
        FactorRewardStrategyFactory strategyFactory = new FactorRewardStrategyFactory();
        if (ifNewUser(userId)) {

        } else {

        }
    }

    public boolean ifNewUser(long userId) {
        return RandomUtils.nextBoolean();
    }
}

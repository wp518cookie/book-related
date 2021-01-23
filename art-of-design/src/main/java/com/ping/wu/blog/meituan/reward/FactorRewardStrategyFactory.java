package com.ping.wu.blog.meituan.reward;

/**
 * @author wuping
 * @date 2021-01-12
 */

public class FactorRewardStrategyFactory extends StrategyFactory {

    @Override
    RewardStrategy createStrategy(Class c) {
        RewardStrategy product = null;
        try {
            product = (RewardStrategy) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {

        }
        return product;
    }
}

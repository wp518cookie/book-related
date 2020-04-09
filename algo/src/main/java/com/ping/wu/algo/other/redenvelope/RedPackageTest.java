package com.ping.wu.algo.other.redenvelope;

import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

/**
 * @author wuping
 * @date 2019-10-30
 */

public class RedPackageTest {
    public static void main(String[] args) {
        Redpackage redpackage = new Redpackage(10, 1000);

    }

    public static int getRandomMoney(Redpackage redpackage) {
        if (redpackage.remainSize == 1) {
            redpackage.remainSize--;
            return redpackage.remainMoney;
        }
        int min = 1;
        int max = redpackage.remainMoney / redpackage.remainSize * 2;
        int money = RandomUtils.nextInt(1, max + 1);
        return 9;
    }
}

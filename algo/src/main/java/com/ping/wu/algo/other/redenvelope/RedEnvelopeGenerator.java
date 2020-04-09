package com.ping.wu.algo.other.redenvelope;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuping
 * @date 2019-10-30
 */

public class RedEnvelopeGenerator {
    private static final Integer LIMIT = 200;

    public static void main(String[] args) {
        RedEnvelopeGenerator generator = new RedEnvelopeGenerator();
        for (int j = 0; j < 100000; j++) {
            List<Integer> result = generator.generate1();
            int t = 0;
            for (int i = 0; i < result.size(); i++) {
                t += result.get(i);
                if (result.get(i) >= 200) {
                    throw new RuntimeException("超出金额限制");
                }
                System.out.print(result.get(i) + " ");
            }
            System.out.println(t);
        }
    }

    public List<Integer> generate1() {
        List<Integer> result = new ArrayList();
        int total = 1000;
        int count = 10;
        for (int i = 0; i < 9; i++) {
            int average = total / (count - i);
            int rand;
            if (total < LIMIT) {
                rand = RandomUtils.nextInt(1, average + 1);
            } else {
                rand = RandomUtils.nextInt(average, LIMIT);
            }
            result.add(rand);
            total = total - rand;
        }
        result.add(total);
        return result;
    }
}

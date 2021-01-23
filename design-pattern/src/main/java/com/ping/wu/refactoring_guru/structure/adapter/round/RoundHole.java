package com.ping.wu.refactoring_guru.structure.adapter.round;

/**
 * @author wuping
 * @date 2020-11-05
 */

public class RoundHole {
    private double radius;

    public RoundHole(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public boolean fits(RoundPeg peg) {
        boolean result;
        result = (this.getRadius() >= peg.getRadius());
        return result;
    }
}

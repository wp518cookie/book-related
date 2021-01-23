package com.ping.wu.refactoring_guru.structure.adapter.square;

/**
 * @author wuping
 * @date 2020-11-05
 */

public class SquarePeg {
    private double width;

    public SquarePeg(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public double getSquare() {
        double result;
        result = Math.pow(this.width, 2);
        return result;
    }
}

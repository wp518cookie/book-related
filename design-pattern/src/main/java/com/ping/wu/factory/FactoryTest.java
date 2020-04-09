package com.ping.wu.factory;

/**
 * @author wuping
 * @date 2019-09-25
 */

public class FactoryTest {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape1 = shapeFactory.getShapre("circle");
        shape1.draw();
    }
}

package com.ping.wu.factory;

/**
 * @author wuping
 * @date 2019-09-25
 */

public class ShapeFactory {
    public Shape getShapre(String s) {
        if (s.equals("circle")) {
            return new Circle();
        } else if (s.equals("square")) {
            return new Square();
        } else {
            return null;
        }
    }
}

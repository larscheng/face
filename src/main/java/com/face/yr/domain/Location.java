package com.face.yr.domain;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/12/6 13:40
 */
public class Location {
    private double top;
    private double left;
    private int rotation;
    private int width;
    private int height;

    public double getTop() {
        return top;
    }

    public Location setTop(double top) {
        this.top = top;
        return this;
    }

    public double getLeft() {
        return left;
    }

    public Location setLeft(double left) {
        this.left = left;
        return this;
    }

    public int getRotation() {
        return rotation;
    }

    public Location setRotation(int rotation) {
        this.rotation = rotation;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public Location setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public Location setHeight(int height) {
        this.height = height;
        return this;
    }

    @Override
    public String toString() {
        return "Location{" +
                "top=" + top +
                ", left=" + left +
                ", rotation=" + rotation +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}

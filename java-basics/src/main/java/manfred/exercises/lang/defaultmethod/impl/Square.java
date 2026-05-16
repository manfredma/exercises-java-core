package manfred.exercises.lang.defaultmethod.impl;

import manfred.exercises.lang.defaultmethod.Resizable;
/**
 * Resizable 接口的正方形实现，用于 default 方法演示场景。
 *
 * 实现了 Resizable（继承自 Drawable）的所有抽象方法，
 * 与 Ellipse、Triangle 共同作为可调整大小图形的具体实现，
 * 配合 Game 和 Utils 演示面向接口的多态调用。
 */
public class Square implements Resizable {
    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void setWidth(int width) {

    }

    @Override
    public void setHeight(int height) {

    }

    @Override
    public void setAbsoluteSize(int width, int height) {

    }

    @Override
    public void draw() {

    }
}
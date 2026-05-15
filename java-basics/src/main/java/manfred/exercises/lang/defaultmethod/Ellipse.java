package manfred.exercises.lang.defaultmethod;
/**
 * Resizable 接口的椭圆形实现，用于 default 方法演示场景。
 *
 * 实现了 Resizable（继承自 Drawable）的所有抽象方法，
 * 与 Square、Triangle 共同作为可调整大小图形的具体实现，
 * 配合 Game 和 Utils 演示对接口的多态调用。
 */
public class Ellipse implements Resizable {
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
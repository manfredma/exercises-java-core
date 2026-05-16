package manfred.exercises.lang.defaultmethod;

/**
 * 定义可绘制对象的基础接口。
 * <p>
 * 作为 Resizable 接口的父接口，规定实现类必须提供 draw() 方法，
 * 演示接口继承在 default 方法体系中的角色。
 */
public interface Drawable {
    void draw();
}
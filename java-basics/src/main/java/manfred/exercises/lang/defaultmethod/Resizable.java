package manfred.exercises.lang.defaultmethod;

/**
 * 定义可调整大小图形的接口，继承自 Drawable。
 *
 * 声明获取/设置宽高及绝对尺寸调整的方法，
 * 作为 default 方法向后兼容性演示的核心接口，
 * 通过注释掉的 setRelativeSize 方法模拟向接口添加新方法破坏现有实现的场景。
 */
public interface Resizable extends Drawable{
    public int getWidth();
    public int getHeight();
    public void setWidth(int width);
    public void setHeight(int height);
    public void setAbsoluteSize(int width, int height);
    //TODO: uncomment, read the README.txt for instructions
    //public void setRelativeSize(int widthFactor, int heightFactor);
}

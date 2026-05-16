package manfred.exercises.lang.defaultmethod.util;

import manfred.exercises.lang.defaultmethod.Resizable;

import java.util.List;

/**
 * 提供对 Resizable 图形列表的批量绘制工具方法。
 *
 * paint 方法遍历图形列表并调用 setAbsoluteSize，
 * 演示通过接口类型统一操作不同实现类的多态用法，
 * 同时注释掉的 setRelativeSize 调用模拟向接口添加方法后的兼容性问题。
 */
public class Utils{
    public static void paint(List<Resizable> l){
        l.forEach(r -> { r.setAbsoluteSize(42, 42); });

        //TODO: uncomment, read the README.txt for instructions
        //l.forEach(r -> { r.setRelativeSize(2, 2); });
    }

}
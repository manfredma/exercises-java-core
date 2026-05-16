package manfred.exercises.lang.defaultmethod;

import manfred.exercises.lang.defaultmethod.util.Utils;
import manfred.exercises.lang.defaultmethod.impl.Triangle;
import manfred.exercises.lang.defaultmethod.impl.Square;
import manfred.exercises.lang.defaultmethod.impl.Ellipse;
import java.util.Arrays;
import java.util.List;

/**
 * 演示通过 Resizable 接口对多种图形进行统一操作。
 *
 * 创建 Square、Triangle、Ellipse 的列表，
 * 调用 Utils.paint() 展示面向接口编程与多态分派，
 * 体现 default 方法为已有接口添加新行为而不破坏现有实现类的优势。
 */
public class Game {

    public static void main(String...args){
        List<Resizable> resizableShapes =
                Arrays.asList(new Square(),
                        new Triangle(), new Ellipse());
        Utils.paint(resizableShapes);
    }
}

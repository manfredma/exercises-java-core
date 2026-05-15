package manfred.exercises.lang.optional;

import java.util.*;

/**
 * Optional 链式调用练习中的汽车领域模型。
 *
 * 持有 Optional<Insurance> 字段，避免直接使用 null 表示无保险的情况，
 * 配合 Person、Insurance 类演示 Optional.flatMap 和 Optional.map 的链式调用。
 */
public class Car {

    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}
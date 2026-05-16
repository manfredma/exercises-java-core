package manfred.exercises.lang.optional.model;

import manfred.exercises.lang.optional.model.Car;
import manfred.exercises.lang.optional.model.Insurance;

import java.util.*;

/**
 * Optional 链式调用练习中的人员领域模型。
 *
 * 持有 Optional<Car> 字段，表示一个人可能拥有也可能没有汽车，
 * 配合 Car 和 Insurance 类演示三层 Optional 嵌套的 flatMap 链式调用。
 */
public class Person {

    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }
}
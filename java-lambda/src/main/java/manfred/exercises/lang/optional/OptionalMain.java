package manfred.exercises.lang.optional;

import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * 演示 Optional 的 flatMap 链式调用与 Stream 结合处理可空值。
 *
 * getCarInsuranceName 方法展示多层 flatMap/map 链安全获取嵌套对象属性，
 * getCarInsuranceNames 方法演示 stream + flatMap 将 Optional 流扁平化为实际值的集合。
 */
public class OptionalMain {

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public Set<String> getCarInsuranceNames(List<Person> persons) {
        return persons.stream()
                .map(Person::getCar)
                .map(optCar -> optCar.flatMap(Car::getInsurance))
                .map(optInsurance -> optInsurance.map(Insurance::getName))
                .flatMap(a -> {
                    if (a.isPresent()) {
                        return Stream.of(a.get());
                    } else {
                        return Stream.empty();
                    }
                })
                .collect(toSet());
    }

    public static void main(String[] args) {
        OptionalMain demo = new OptionalMain();
        System.out.println(demo.getCarInsuranceName(java.util.Optional.empty()));
    }

}

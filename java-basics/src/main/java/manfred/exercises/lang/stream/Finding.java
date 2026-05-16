package manfred.exercises.lang.stream;

import manfred.exercises.lang.stream.model.Dish;
import static manfred.exercises.lang.stream.model.Dish.menu;
import java.util.*;

/**
 * 演示 Stream 的查找与匹配操作。
 *
 * 涵盖 anyMatch（至少一个匹配）、allMatch（全部匹配）、noneMatch（无一匹配）
 * 和 findAny（返回 Optional 任意匹配元素）四种短路终端操作的用法。
 */
public class Finding{

    public static void main(String...args){
        if(isVegetarianFriendlyMenu()){
            System.out.println("Vegetarian friendly");
        }

        System.out.println(isHealthyMenu());
        System.out.println(isHealthyMenu2());
        
        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }
    
    private static boolean isVegetarianFriendlyMenu(){
        return menu.stream().anyMatch(Dish::isVegetarian);
    }
    
    private static boolean isHealthyMenu(){
        return menu.stream().allMatch(d -> d.getCalories() < 1000);
    }
    
    private static boolean isHealthyMenu2(){
        return menu.stream().noneMatch(d -> d.getCalories() >= 1000);
    }
    
    private static Optional<Dish> findVegetarianDish(){
        return menu.stream().filter(Dish::isVegetarian).findAny();
    }
    
}
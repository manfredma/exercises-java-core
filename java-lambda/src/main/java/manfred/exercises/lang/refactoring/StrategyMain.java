package manfred.exercises.lang.refactoring;

/**
 * 演示策略模式的传统类实现与 Lambda 两种写法。
 *
 * 传统方式定义 IsNumeric/IsAllLowerCase 策略实现类；
 * Lambda 方式直接传入校验逻辑函数，消除策略类定义，
 * 对比说明 Lambda 简化策略模式的效果及适用场景。
 */
public class StrategyMain {

    public static void main(String[] args) {
        // old school
        Validator v1 = new Validator(new IsNumeric());
        System.out.println(v1.validate("aaaa"));
        Validator v2 = new Validator(new IsAllLowerCase ());
        System.out.println(v2.validate("bbbb"));

        // with lambdas
        Validator v3 = new Validator((String s) -> s.matches("\\d+"));
        System.out.println(v3.validate("aaaa"));
        Validator v4 = new Validator((String s) -> s.matches("[a-z]+"));
        System.out.println(v4.validate("bbbb"));
    }

    interface ValidationStrategy {
        public boolean execute(String s);
    }

    static private class IsAllLowerCase implements ValidationStrategy {
        public boolean execute(String s){
            return s.matches("[a-z]+");
        }
    }
    static private class IsNumeric implements ValidationStrategy {
        public boolean execute(String s){
            return s.matches("\\d+");
        }
    }

    static private class Validator{
        private final ValidationStrategy strategy;
        public Validator(ValidationStrategy v){
            this.strategy = v;
        }
        public boolean validate(String s){
            return strategy.execute(s); }
    }
}
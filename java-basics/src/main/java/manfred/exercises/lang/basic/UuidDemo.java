package manfred.exercises.lang.basic;

import java.util.UUID;

/**
 * 演示使用 {@link java.util.UUID#randomUUID()} 生成不含连字符的 32 位 GUID 字符串。
 *
 * {@code createGUID()} 方法调用 {@code UUID.randomUUID()} 生成标准 UUID，
 * 再通过 {@code replace("-", "")} 去除分隔符，得到常见于数据库主键或业务流水号场景的
 * 32 位十六进制字符串，连续生成 8 个以直观展示每次结果均唯一。
 */
public class UuidDemo {

    public static void main(String[] args) throws Exception {
        new UuidDemo().testCreate();
    }

    public void testCreate() {
        System.out.println(createGUID());
        System.out.println(createGUID());
        System.out.println(createGUID());
        System.out.println(createGUID());
        System.out.println(createGUID());
        System.out.println(createGUID());
        System.out.println(createGUID());
        System.out.println(createGUID());

    }

    private static String createGUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

}

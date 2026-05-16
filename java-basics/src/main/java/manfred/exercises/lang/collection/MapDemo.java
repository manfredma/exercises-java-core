package manfred.exercises.lang.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * 演示 {@link java.util.HashMap} 允许 value 为 {@code null} 的特性。
 *
 * 向 HashMap 中插入一个 value 为 {@code null} 的条目，
 * 并通过 {@code containsKey()} 验证该键确实存在于 Map 中，
 * 提示开发者在使用 {@code get()} 返回值判断键是否存在时需注意 null value 与 key 不存在的歧义，
 * 应优先使用 {@code containsKey()} 而非 {@code get() == null} 做存在性判断。
 */
public class MapDemo {

    public static void main(String[] args) throws Exception {
        new MapDemo().testInsertNullIntoHashMap();
    }

    /**
     * Hash Map 可以插入 null 到 value
     */
    public void testInsertNullIntoHashMap() {
        Map<String, String> map = new HashMap<String, String>(12);
        map.put("aaa", "bbb");
        map.put("ccc", null);
        System.out.println("containsKey(\"ccc\"): " + map.containsKey("ccc"));
    }
}

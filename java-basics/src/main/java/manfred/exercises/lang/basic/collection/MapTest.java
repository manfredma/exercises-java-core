package manfred.exercises.lang.basic.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Manfred since 2018/3/15.
 */
public class MapTest {

    public static void main(String[] args) throws Exception {
        new MapTest().testInsertNullIntoHashMap();
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

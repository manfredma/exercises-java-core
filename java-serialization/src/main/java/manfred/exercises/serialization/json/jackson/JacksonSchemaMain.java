package manfred.exercises.serialization.json.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;

/**
 * 演示使用 Jackson 结合 JSON Schema 对 JSON 数据进行结构校验。
 *
 * 练习通过 {@code com.github.fge.jsonschema} 库与 Jackson 的 {@code JsonNode}
 * 配合使用，定义 JSON Schema 并对输入数据执行验证，获取并遍历校验报告
 * ({@code ProcessingReport})，掌握 JSON Schema 校验失败信息的解析方式。
 */
public class JacksonSchemaMain {
    @Test
    public void testJsonSchema2() {
        String failure = "{\"foo\":1234}";
        String Schema = "{\"type\": \"object\", \"properties\" : {\"foo\" : {\"type\" : " +
                "\"string\"}}}";
        ProcessingReport report = null;
        try {
            JsonNode data = JsonLoader.fromString(failure);
            JsonNode schema = JsonLoader.fromString(Schema);
            report = JsonSchemaFactory.byDefault().getValidator().validateUnchecked(schema, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Assert.assertTrue(report.isSuccess());
        Iterator<ProcessingMessage> it = report.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}

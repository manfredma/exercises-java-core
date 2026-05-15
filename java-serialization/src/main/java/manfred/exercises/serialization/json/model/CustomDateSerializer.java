package manfred.exercises.serialization.json.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 演示 Jackson 自定义日期序列化器的实现方式。
 *
 * 通过继承 {@code StdSerializer<Date>} 并重写 {@code serialize} 方法，
 * 将 {@code java.util.Date} 按指定格式（{@code dd-MM-yyyy hh:mm:ss}）输出为
 * JSON 字符串，配合 {@code @JsonSerialize(using = CustomDateSerializer.class)}
 * 注解应用到具体字段，实现字段级别的日期格式定制。
 */
public class CustomDateSerializer extends StdSerializer<Date> {

    private static final SimpleDateFormat DATE_FORMAT
            = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    public CustomDateSerializer() {
        this(null);
    }

    public CustomDateSerializer(Class<Date> t) {
        super(t);
    }

    @Override
    public void serialize(
            Date value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException {
        gen.writeString(DATE_FORMAT.format(value));
    }
}
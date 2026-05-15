package manfred.exercises.serialization.messagepack;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;
import org.msgpack.type.Value;
import org.msgpack.unpacker.Converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * 演示 MessagePack 对集合类型进行高效二进制序列化的基本用法。
 *
 * 练习使用 {@code org.msgpack.MessagePack} 将 {@code List<String>} 序列化为紧凑的
 * 字节数组，并通过两种方式反序列化：直接使用模板（{@code Templates.tList}）
 * 以及先解析为动态 {@code Value} 再通过 {@code Converter} 转换为强类型集合，
 * 对比 MessagePack 与 JSON 在编解码效率上的差异。
 */
public class MessagePackDemo {
    public static void main(String[] args) throws IOException {
        // Create serialize objects.
        List<String> src = new ArrayList<String>();
        src.add("msgpack");
        src.add("kumofs");
        src.add("viver");

        MessagePack msgpack = new MessagePack();
// Serialize
        byte[] raw = msgpack.write(src);

// Deserialize directly using a template
        List<String> dst1 = msgpack.read(raw, Templates.tList(Templates.TString));
        System.out.println(dst1.get(0));
        System.out.println(dst1.get(1));
        System.out.println(dst1.get(2));

// Or, Deserialze to Value then convert type.
        Value dynamic = msgpack.read(raw);
        List<String> dst2 = new Converter(dynamic)
                .read(Templates.tList(Templates.TString));
        System.out.println(dst2.get(0));
        System.out.println(dst2.get(1));
        System.out.println(dst2.get(2));
    }
}

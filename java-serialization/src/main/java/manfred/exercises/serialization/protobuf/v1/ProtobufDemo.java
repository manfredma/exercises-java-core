package manfred.exercises.serialization.protobuf.v1;

import com.google.protobuf.InvalidProtocolBufferException;
/**
 * 演示 Protocol Buffers v1 版本中 Person 消息的序列化与反序列化完整流程。
 *
 * 练习使用生成的 {@code PersonModel.Person} Builder 构建消息对象，
 * 通过 {@code toByteArray()} 将其编码为二进制字节数组，再通过 {@code parseFrom(byte[])}
 * 从字节数组还原为 Person 对象，并打印各字段值，验证 Protobuf 编解码的正确性与
 * 二进制格式的紧凑性。
 */
public class ProtobufDemo {
    public static void main(String[] args) throws Exception {
        new ProtobufDemo().testN();
    }

    public void testN() throws InvalidProtocolBufferException {
        PersonModel.Person.Builder builder = PersonModel.Person.newBuilder();
        builder.setId(1);
        builder.setName("jihite");
        builder.setEmail("jihite@jihite.com");

        PersonModel.Person person = builder.build();
        System.out.println("before:" + person);

        System.out.println("===Person Byte:");
        for (byte b : person.toByteArray()) {
            System.out.print(b);
        }
        System.out.println("================");

        byte[] byteArray = person.toByteArray();
        PersonModel.Person p2 = PersonModel.Person.parseFrom(byteArray);
        System.out.println("after id:" + p2.getId());
        System.out.println("after name:" + p2.getName());
        System.out.println("after email:" + p2.getEmail());

    }
}
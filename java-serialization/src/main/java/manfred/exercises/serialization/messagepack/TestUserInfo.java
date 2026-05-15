package manfred.exercises.serialization.messagepack;

import org.msgpack.MessagePack;

import java.io.IOException;
/**
 * 演示 MessagePack 对自定义 POJO 进行序列化与反序列化的完整流程。
 *
 * 练习将标注了 {@code @Message} 注解的 {@link UserInfo} 对象通过 MessagePack
 * 编码为字节数组，再从字节数组还原为等价对象，验证序列化前后字段值的一致性，
 * 掌握 MessagePack 对 Java Bean 的自动字段映射机制。
 */
public class TestUserInfo {
    public static void main(String[] args) throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo.buildUserId(100).buildUserName("manfred");
        MessagePack msgPack = new MessagePack();
        byte[] userInfoBytes = msgPack.write(userInfo);

        UserInfo cloneUserInfo = msgPack.read(userInfoBytes, UserInfo.class);

        System.out.println(cloneUserInfo);
    }
}

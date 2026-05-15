package manfred.exercises.serialization.jdk;

import java.io.Serializable;
/**
 * 演示 JDK 原生序列化的用户信息实体类。
 *
 * 通过实现 {@code java.io.Serializable} 接口，使该类的实例可被
 * {@code ObjectOutputStream} 序列化为字节流，并可通过 {@code ObjectInputStream}
 * 反序列化还原，练习 Java 内置序列化协议的基本使用方式。
 */
public class UserInfo implements Serializable {

}

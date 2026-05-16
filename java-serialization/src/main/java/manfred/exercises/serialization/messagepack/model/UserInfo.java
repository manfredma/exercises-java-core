package manfred.exercises.serialization.messagepack.model;

import org.msgpack.annotation.Message;

import java.io.Serializable;
import java.nio.ByteBuffer;
/**
 * MessagePack 序列化练习中使用的用户信息实体类。
 *
 * 通过同时实现 {@code java.io.Serializable} 和标注 {@code @org.msgpack.annotation.Message}，
 * 演示 MessagePack 对 POJO 的序列化支持；同时包含手动编码方法 {@code codeC()}，
 * 展示基于 {@code ByteBuffer} 的低层次二进制编码实现，便于与 MessagePack 自动编码
 * 在性能和代码复杂度上进行对比。
 */
@Message
public class UserInfo implements Serializable {

    private String userName;

    private int userId;

    public UserInfo buildUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserInfo buildUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte[] codeC() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] value = this.userName.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(userId);
        buffer.flip();

        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }

    @Override
    public String toString() {
        return "[" + userId + ", " + userName + "]";
    }
}

package manfred.exercises.serialization.protobuf.v2;

import com.google.protobuf.InvalidProtocolBufferException;
/**
 * 演示 Protocol Buffers v2 版本中含 repeated 字段的订阅请求消息编解码流程。
 *
 * 练习使用 {@code SubscribeReqProto.SubscribeReq} Builder 构建包含多地址
 * ({@code repeated string address}) 的复合消息，通过自定义的 {@code encode}/{@code decode}
 * 方法封装 Protobuf 的二进制序列化接口，并通过 {@code equals} 断言验证
 * 编解码前后对象的一致性，体会 Protobuf 对复杂结构消息的高效处理能力。
 */
public class ProtobufSubscribeDemo {
    private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] req) throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(req);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
        return SubscribeReqProto.SubscribeReq.newBuilder()
                .setSubReqId(1)
                .setUserName("manfred")
                .setProductName("netty")
                .addAddress("Nj")
                .addAddress("bj")
                .addAddress("sz")
                .build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq req = createSubscribeReq();
        System.out.println("before encode : \n" + req.toString());
        SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
        System.out.println("after decode : \n" + req2.toString());
        System.out.println("Assert equal : --> " + req2.equals(req));
    }


}

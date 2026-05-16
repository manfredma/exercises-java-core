package manfred.exercises.io.nio;

import java.io.IOException;
import java.net.Socket;

/**
 * NIO 演示模块中配套 Reactor 服务端使用的 TCP 客户端。
 *
 * 使用阻塞式 {@code java.net.Socket} 向本地 8080 端口发送 "hello, world"
 * 并读取服务端响应，作为触发 {@code Reactor} 非阻塞事件驱动处理流程的测试入口。
 */
public class Client implements Runnable {

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            socket.getOutputStream().write("hello, world".getBytes());
            byte[] s = new byte[1024];
            int l = socket.getInputStream().read(s);
            System.out.println(new String(s, 0, l));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client c = new Client();
        c.run();
    }
}

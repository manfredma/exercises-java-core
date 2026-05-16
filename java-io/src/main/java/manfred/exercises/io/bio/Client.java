package manfred.exercises.io.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * BIO 模式下的 TCP 客户端，向服务端发送消息并接收回显。
 *
 * 使用传统阻塞式 {@code java.net.Socket} 连接到本地 {@code Server}，
 * 发送 "hello, world" 字节流后同步等待服务端回写，演示 BIO 编程模型中
 * 请求-响应的单次同步交互流程。
 */
public class Client implements Runnable {

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", Server.PORT);
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

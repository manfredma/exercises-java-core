package manfred.exercises.io.bio;

import manfred.exercises.io.bio.handler.TimeServerHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO 一对一模型的时间服务器，演示最基础的阻塞 I/O 服务端实现。
 *
 * 使用 {@link java.net.ServerSocket} 循环接受连接，每接受一个客户端连接就创建一个新线程
 * 交由 {@link TimeServerHandler} 处理，展示"一连接一线程"的传统 BIO 并发模型及其局限性。
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port : " + port);

            Socket socket = null;
            while (true) {
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }
}

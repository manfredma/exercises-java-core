package manfred.exercises.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * BIO 模式下的多线程 TCP 回显服务器。
 *
 * 监听指定端口，每接受一个客户端连接就创建一个新线程处理，由内部类 {@code Handler}
 * 完成数据读取与原样回写。演示了传统"一连接一线程"的 BIO 并发模型及其在高并发场景
 * 下线程数量无限增长的局限性。
 */
public class Server implements Runnable {
    static final int PORT = 18080;
    private static final int MAX_INPUT = 1024;

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(PORT);
            while (!Thread.interrupted()) {
                new Thread(new Handler(ss.accept())).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Handler implements Runnable {
        final Socket socket;

        Handler(Socket s) {
            this.socket = s;
        }

        @Override
        public void run() {
            try {
                byte[] input = new byte[MAX_INPUT];
                int l = socket.getInputStream().read(input);
                byte[] output = process(input, l);
                socket.getOutputStream().write(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private byte[] process(byte[] input, int length) {
            return Arrays.copyOf(input, length);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }
}

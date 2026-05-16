package manfred.exercises.io.bio;

import manfred.exercises.io.bio.handler.TimeServerHandlerExecutorPool;
import manfred.exercises.io.bio.handler.TimeServerHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO 多对多模型的时间服务器启动入口，演示线程池版 BIO 服务端。
 *
 * 与 {@code bio.TimeServer} 的区别在于引入了 {@link TimeServerHandlerExecutorPool}
 * 线程池来处理客户端请求，演示用有界线程池替代"一连接一线程"的伪异步 BIO 模型，
 * 展示如何在传统阻塞 I/O 下通过线程池提升并发处理能力及资源控制能力。
 */
public class M2nTimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                // do nothing
            }
        }

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port : " + port);
            TimeServerHandlerExecutorPool singleExecutor = new TimeServerHandlerExecutorPool(50, 10000);
            while (true) {
                Socket socket = server.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
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

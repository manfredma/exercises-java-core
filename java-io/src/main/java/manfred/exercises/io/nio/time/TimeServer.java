package manfred.exercises.io.nio.time;

/**
 * NIO 时间服务器的启动入口类。
 *
 * 解析命令行端口参数，创建 {@link MultiplexerTimeServer} 并以命名线程启动，
 * 演示 NIO 多路复用服务端的整体启动方式，与 BIO/AIO 服务端启动方式并列对比学习。
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                // do nothing
            }
        }

        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}

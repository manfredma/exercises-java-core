package manfred.exercises.io.nio;

/**
 * NIO 时间客户端的启动入口类。
 *
 * 解析命令行端口参数，创建并启动 {@link TimeClientHandler} 线程，
 * 演示如何以独立线程驱动基于 {@link java.nio.channels.Selector} 的 NIO 非阻塞客户端。
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                // do nothing
            }
        }

        new Thread(new TimeClientHandler("127.0.0.1", port)).start();
    }
}

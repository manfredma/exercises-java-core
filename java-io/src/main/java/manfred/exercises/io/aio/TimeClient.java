package manfred.exercises.io.aio;

/**
 * AIO 时间客户端的启动入口类。
 *
 * 解析命令行端口参数，创建并启动 {@link AsyncTimeClientHandler} 线程，
 * 演示如何以独立线程驱动 AIO 异步客户端连接与通信的完整流程。
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

        new Thread(new AsyncTimeClientHandler("127.0.0.1", port)).start();
    }
}

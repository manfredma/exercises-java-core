package manfred.exercises.io.aio;

import manfred.exercises.io.aio.handler.AsyncTimeServerHandler;

import manfred.exercises.io.aio.handler.AcceptCompletionHandler;

/**
 * AIO 时间服务器的启动入口类。
 *
 * 解析命令行端口参数，创建 {@link AsyncTimeServerHandler} 并以命名线程启动，
 * 演示 AIO（异步非阻塞 I/O）服务端的整体启动方式，与 BIO/NIO 启动方式对比学习。
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;

        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (Exception e) {
                // do nothing
            }
        }

        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer, "AIO-AsyncTimeServerHandler-001").start();
    }
}

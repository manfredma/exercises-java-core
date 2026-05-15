package manfred.exercises.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * AIO 时间服务器的异步处理器，负责启动并维持服务端的生命周期。
 *
 * 实现 {@link Runnable} 接口，封装 {@link java.nio.channels.AsynchronousServerSocketChannel} 的初始化与绑定，
 * 演示 AIO 服务端的启动流程：开启异步服务通道、注册 accept 操作并通过 {@link CountDownLatch}
 * 阻塞主线程，直到服务关闭，展示 AIO 异步非阻塞 I/O 的服务端驱动模型。
 */
public class AsyncTimeServerHandler implements Runnable {

    CountDownLatch latch;

    AsynchronousServerSocketChannel asynchronousServerSocketChannel;


    public AsyncTimeServerHandler(int port) {

        try {
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));

            System.out.println("The time server is start in port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);

        doAccept();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void doAccept() {
        asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
    }
}

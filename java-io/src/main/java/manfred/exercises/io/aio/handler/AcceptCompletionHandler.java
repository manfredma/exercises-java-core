package manfred.exercises.io.aio.handler;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * AIO 时间服务器的连接接受完成处理器。
 *
 * 实现 {@link CompletionHandler} 接口，在 {@link AsyncTimeServerHandler} 接受到新连接后被回调，
 * 演示 AIO 异步 I/O 模型中连接事件的回调处理机制：继续注册下一个 accept 操作，
 * 并为新连接创建 {@link ReadCompletionHandler} 开始异步读取客户端数据。
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {
    @Override
    public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
        // 继续监听
        attachment.asynchronousServerSocketChannel.accept(attachment, this);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        result.read(byteBuffer, byteBuffer, new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
        exc.printStackTrace();
        attachment.latch.countDown();
    }
}

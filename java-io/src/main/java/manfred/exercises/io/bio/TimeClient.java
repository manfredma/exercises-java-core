package manfred.exercises.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * BIO 一对一模型的时间查询客户端。
 *
 * 使用传统阻塞 I/O 的 {@link java.net.Socket} 连接服务端，
 * 演示通过 {@link java.io.BufferedReader} 和 {@link java.io.PrintWriter} 收发文本消息的基本用法，
 * 发送 "QUERY TIME ORDER" 指令并打印服务端返回的当前时间。
 */
public class TimeClient {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        Socket client = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            client = new Socket("127.0.0.1", port);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
            out.println("QUERY TIME ORDER");

            System.out.println("Send order 2 server succeed.");

            String resp = in.readLine();
            System.out.println("Now is : " + resp);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }
            if (in != null) {
                in.close();
                in = null;
            }
            if (client != null) {
                client.close();
                client = null;
            }

        }
    }
}

package manfred.exercises.lang.lambda;

import java.io.*;

/**
 * 演示"环绕执行"（Execute Around）模式：用 Lambda 参数化资源的处理行为。
 *
 * 将文件的打开/关闭资源管理代码固定在 processFile 方法中，
 * 通过函数式接口 BufferedReaderProcessor 将具体读取逻辑作为参数传入，
 * 实现行为参数化，避免资源管理代码的重复。
 */
public class ExecuteAround {

	public static void main(String ...args) throws IOException{

        // method we want to refactor to make more flexible
        String result = processFileLimited();
        System.out.println(result);

        System.out.println("---");

		String oneLine = processFile((BufferedReader b) -> b.readLine());
		System.out.println(oneLine);

		String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
		System.out.println(twoLines);

	}

    public static String processFileLimited() throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader(ExecuteAround.class.getResource("/manfred/exercises/lang/lambda/data.txt").getPath()))) {
            return br.readLine();
        }
    }

	public static String processFile(BufferedReaderProcessor p) throws IOException {
		try(BufferedReader br = new BufferedReader(new FileReader(ExecuteAround.class.getResource("/manfred/exercises/lang/lambda/data.txt").getPath()))){
			return p.process(br);
		}

	}

	public interface BufferedReaderProcessor{
		public String process(BufferedReader b) throws IOException;

	}
}
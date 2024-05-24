package asqnw.project;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main
{
    private static final ExecutorService threadPool = Executors.newCachedThreadPool();

    public static void main(String[] args)
    {
        System.out.println("输入URL路径：");
        String url = new Scanner(System.in).nextLine();
        System.out.println("输入文件验证值：");
        String msg = new Scanner(System.in).nextLine();
        System.out.println("启动服务器");
        new HttpServer().start(request -> {
            HashMap<String, String> response = new HashMap<>();
            if (request.get(HttpServer.URL).get(0).equals(url))
                response.put("200 OK", msg);
            else
                response.put("403 Forbidden", "");
            return response;
        }, 80);
    }

    public static void thread(Runnable runnable)
    {
        threadPool.execute(runnable);
    }
}
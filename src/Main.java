import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class Main implements Runnable {
    private static boolean stop = false;
    private static String user = "Igor";
    private static StringBuilder stringBuilder = new StringBuilder();
    public static void main(String[] args) throws IOException, InterruptedException {
        String hello = "hello";
        hello = hello.substring(2, 4);
        System.out.println(hello);
        Socket socket = new Socket("217.18.61.102", 1111);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        InputStream inputStream = socket.getInputStream();
        bufferedWriter.write("http://www.onlinepasswordgenerator.ru/");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        System.out.println("continue1");
        Thread.sleep(590);
        String str;
        int bytes = 10;
        new Thread(new Main()).start();
        while (!stop) {
            str = new String(inputStream.readNBytes(bytes));
            stringBuilder.append(str);
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        stop = true;
        System.out.println(stringBuilder);
        System.out.println("Good!");
        File file = new File("C:/users/" + user + "/site.html");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(stringBuilder.toString());
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

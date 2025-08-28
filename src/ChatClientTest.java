import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatClientTest {
    private static final String host = "127.0.0.1";
    private static final int port = 5000;

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket(host, port);
             BufferedReader br = new BufferedReader(
                     new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter pw = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
             BufferedReader keyboard = new BufferedReader(
                     new InputStreamReader(System.in, StandardCharsets.UTF_8))
        ) {
            // 닉네임 등록
            System.out.print("NICK ");
            String str = keyboard.readLine();
            pw.println(str);
            pw.flush();
            System.out.println(str + " joined");

            // 클라 -> 서버 채팅
            String msg;
            Chat:
            while (true) {
                System.out.print("Enter Message> ");
                msg = keyboard.readLine();
                switch (msg) {
                    case "/quit" -> {
                        pw.println(msg);
                        System.out.println("프로그램이 종료됩니다.");
                        break Chat;
                    }
                    case "/who" -> {
                        pw.println(msg);
                    }
                }
                if (msg == null) {
                    break;
                } else if (msg.isEmpty()) {
                    System.out.println("ERR: 다시 입력해 주십시오.");
                } else {
                    pw.println(msg);
                }

                // 서버 -> 클라 채팅
                String resp = br.readLine();
                if (resp == null) {
                    System.out.println("서버와의 연결이 끊어졌습니다.");
                    break;
                }
                System.out.println(resp);
            }
        } catch (IOException e) {
            System.err.println("ERR : 문제가 발생하였습니다." + e.getMessage());
        }

    }
}

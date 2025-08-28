
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
    private static final int PORT = 5000;
    private static final ExecutorService POOL = Executors.newCachedThreadPool();
    private static ConcurrentHashMap<String, PrintWriter> map = new ConcurrentHashMap<>();
    private static List<String> nickList = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws IOException {
        System.out.println("[서버] 서버 시작: " + PORT);
        // Ctrl+C 시 스레드 풀 정리
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\n[서버] 종료하는 중...");
            POOL.shutdownNow();
        }));

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                System.out.println("클라이언트 대기중");
                Socket socket = serverSocket.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String nickname = br.readLine();
                if (map.containsKey(nickname)) {
                    PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                    pw.println("ERR: 중복되는 이름입니다.");
                    socket.close();

                } else {
                    POOL.submit(new ClientHandler(socket, nickname));
                }
            }

        } catch (IOException e) {
            System.err.println("[서버] 에러 발생: " + e.getMessage());
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket socket;
        private final String nickname;

        ClientHandler(Socket socket, String nickname) {
            this.socket = socket;
            this.nickname = nickname;
            System.out.println(nickname + " Connected");
        }

        @Override
        public void run() {
            broadcast(nickname + " joined");
            try (
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                PrintWriter pw = new PrintWriter(
                        new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)
            ) {
                map.put(nickname, pw);
                nickList.add(nickname);

                String contents;
                while ((contents = br.readLine()) != null) {
                    switch (contents) {
                        case "/quit" -> {
                            broadcast(nickname + " left");
                        }
                        case "/who" -> {
                            String str = String.join(", ", nickList);
                            pw.println("USERS " + str);
                        }
                        default -> {
                            broadcast("[" + nickname + "] " + contents);
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("ERR: " + nickname + " | " + e.getMessage());
            } finally {
                try { socket.close(); } catch (IOException ignored) {}
                map.remove(nickname);
                nickList.remove(nickname);
                broadcast(nickname + " left");
            }

        }
    }
    // 전체 채팅 기능
    private static synchronized void broadcast(String str) {
        for (PrintWriter pw : map.values()) {
            pw.println(str);
        }
    }
}

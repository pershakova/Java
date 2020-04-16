package Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient{
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public static Scanner sc = new Scanner(System.in);

    public EchoClient() {
        try {
            processConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                while (true) {
                    String strFromServer = in.readUTF();
                    System.out.println(strFromServer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                closeConnection();
            }
        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    String line = sc.nextLine().trim();
                    if (!line.isEmpty()){
                        out.writeUTF(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                closeConnection();
            }
        }).start();
    }

    public void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new EchoClient();
    }
}
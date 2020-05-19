package Auth;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;

public class EchoClient extends JFrame {
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;

    private JTextField msgInputField;
    private JTextArea chatArea;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private static boolean authorized = false;

    public EchoClient() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        authWindow();
        prepareGUI();
    }

    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        Thread t = new Thread(() -> {
            try {
                while (true) {
                    String strFromServer = in.readUTF();
                    if(strFromServer.startsWith("/authok")) {
                        setAuthorized(true);
                        break;
                    }
                    authWindow();
                }
                while (true) {
                    String strFromServer = in.readUTF();
                    if (strFromServer.equalsIgnoreCase("/end")) {
                        break;
                    }
                    chatArea.append(strFromServer);
                    chatArea.append("\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t.setDaemon(true);
        t.start();
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

    public void sendMessage() {
        if (!msgInputField.getText().trim().isEmpty()) {
            try {
                sendJsonMessage();
               // out.writeUTF(msgInputField.getText());
                msgInputField.setText("");
                msgInputField.grabFocus();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
            }
        }
    }

    public void sendJsonMessage() throws IOException {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("json", true);
            jsonObject.put("message", msgInputField.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(out);
        pw.println(jsonObject);
        pw.flush();
    }

    public void authWindow(){
        try {
            JPanel userPanel = new JPanel();
            userPanel.setLayout(new GridLayout(2, 2));
            JLabel usernameLbl = new JLabel("Логин:");
            JLabel passwordLbl = new JLabel("Пароль:");
            JTextField username = new JTextField();
            JTextField passwordFld = new JTextField();
            userPanel.add(usernameLbl);
            userPanel.add(username);
            userPanel.add(passwordLbl);
            userPanel.add(passwordFld);

            int input = JOptionPane.showConfirmDialog(this, userPanel, "Введите ваш логин и пароль:"
                    , JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (input == 0) {
                out.writeUTF("/auth " + username.getText() + " " + passwordFld.getText());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareGUI() {
        setBounds(600, 300, 500, 500);
        setTitle("Клиент");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton btnSendMsg = new JButton("Отправить");
        bottomPanel.add(btnSendMsg, BorderLayout.EAST);
        msgInputField = new JTextField();
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(msgInputField, BorderLayout.CENTER);
        btnSendMsg.addActionListener(e -> sendMessage());
        msgInputField.addActionListener(e -> sendMessage());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    out.writeUTF("/end");
                    closeConnection();
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater((Runnable) () -> new EchoClient());
    }

    public static synchronized boolean getAuthorized(){ return authorized; }

    public synchronized void setAuthorized(boolean value){ authorized = value; }

}
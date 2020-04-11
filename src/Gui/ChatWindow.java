package Gui;

import javax.swing.*;
import java.awt.*;

public class ChatWindow extends JFrame {
    private final JTextArea multiLineTextArea;
    private final JTextField messageInput;

    public ChatWindow() {
        setBounds(500, 500, 400, 300);
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        multiLineTextArea = new JTextArea();
        messageInput = new JTextField();
        JButton sendButton = new JButton("Send");
        JPanel inputBar = new JPanel();
        JScrollPane scrollPane = new JScrollPane(multiLineTextArea);

        multiLineTextArea.setBackground(Color.LIGHT_GRAY);
        multiLineTextArea.setEditable(false);
        multiLineTextArea.setFont( new Font("Arial", Font.PLAIN, 12) );

        messageInput.setBackground(Color.white);
        messageInput.addActionListener(e -> moveTextToMultiLineTextArea());

        sendButton.addActionListener(e -> moveTextToMultiLineTextArea());

        inputBar.setLayout(new BorderLayout());
        inputBar.add(messageInput, BorderLayout.CENTER);
        inputBar.add(sendButton, BorderLayout.EAST);
        inputBar.setBorder(BorderFactory.createEtchedBorder());

        getContentPane().add(inputBar, BorderLayout.SOUTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void moveTextToMultiLineTextArea(){
        if (messageInput.getText().equals("")){
            return;
        }
        multiLineTextArea.append(messageInput.getText().trim() + "\n");
        messageInput.setText("");
        messageInput.requestFocus();
    }
}

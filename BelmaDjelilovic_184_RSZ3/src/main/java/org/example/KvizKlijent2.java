package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class KvizKlijent2 {
    String serverAddress;
    Scanner in;
    PrintWriter out;
    JFrame frame = new JFrame("Kviz");
    JTextField txtChat = new JTextField(50);
    JTextArea txtArea = new JTextArea(16, 50);
    JScrollPane scroll = new JScrollPane(txtArea);

    public KvizKlijent2(String serverAddress) {
        this.serverAddress = serverAddress;
        txtArea.setEditable(false);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(txtChat, BorderLayout.SOUTH);
        frame.getContentPane().add(scroll, BorderLayout.CENTER);
        frame.pack();

        txtChat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                out.println(txtChat.getText());
                txtChat.setText("");
            }
        });
    }

    private String getIme() {
        return JOptionPane.showInputDialog(frame, "Unesite vase ime", "Unos imena", JOptionPane.PLAIN_MESSAGE);
    }

    private void run() throws UnknownHostException, IOException {
        try {
            Socket socket = new Socket(serverAddress, 54321);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);

            while(in.hasNextLine()) {
                String line = in.nextLine();
                if(line.startsWith("Unesite vase ime")) {
                    out.println(getIme());
                } else if(line.startsWith("Takmicar: ")) {
                    this.frame.setTitle("Takmicar - " + line.substring(10));
                }else if(line.startsWith("[INFO]")) {
                    txtArea.append(line.substring(6) + "\n");
                }

            }
        }
        finally {
            frame.setVisible(false);
            frame.dispose();
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        KvizKlijent2 klijent = new KvizKlijent2("127.0.0.1");
        klijent.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        klijent.frame.setVisible(true);
        klijent.run();
    }
}

package server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        final int PORT = 45000;

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            NoteController nc = new NoteController();
            
            while (true) {
                Socket socket = serverSocket.accept();
                new AttendToClient(socket, nc).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

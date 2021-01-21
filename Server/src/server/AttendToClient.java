package server;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import model.Note;

public class AttendToClient extends Thread {

    Socket socket;
    NoteController nc;

    public AttendToClient(Socket socket, NoteController nc) {
        this.socket = socket;
        this.nc = nc;
    }

    @Override
    public void run() {
        talkToClient(socket);
    }

    private void talkToClient(Socket socket) {
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            System.out.println("Cliente conectado");

            while (true) {
                ObjectInputStream ois = new ObjectInputStream(is);
                List<Note> notes = null;

                String option = (String) ois.readObject();
                System.out.println("Opción del menú: " + option);

                if (option.equals("1")) {
                    ObjectOutputStream oos = new ObjectOutputStream(os);

                    notes = nc.getNotes();

                    for (Note n : notes) {
                        System.out.println("Nota: " + n.getText() + " | Autor: " + n.getAuthor() + "| Tipo: " + n.getCategory());
                    }
                    System.out.println("----------------");

                    oos.writeObject(notes);
                    oos.flush();
                } else if (option.equals("2")) {
                    ois = new ObjectInputStream(is);
                    nc.addNote((Note) ois.readObject());

                    for (Note n : nc.getNotes()) {
                        System.out.println("Nota: " + n.getText() + " | Autor: " + n.getAuthor() + "| Tipo: " + n.getCategory());
                    }
                    System.out.println("----------------");
                } else if (option.equals("3")) {
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    ois = new ObjectInputStream(is);

                    notes = nc.getNotesByCategory((String) ois.readObject());

                    for (Note n : notes) {
                        System.out.println("Nota: " + n.getText() + " | Autor: " + n.getAuthor() + "| Tipo: " + n.getCategory());
                    }

                    oos.writeObject(notes);
                    oos.flush();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

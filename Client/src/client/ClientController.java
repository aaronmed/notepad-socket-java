package client;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import model.Note;

public class ClientController {

    private static final String HOST = "192.168.103.10";
    private static final int PORT = 45000;

    private InputStream is;
    private OutputStream os;
    private Scanner sc;

    public ClientController() {
        sc = new Scanner(System.in);
    }

    public void showMenu() {
        String option = "";
        String note = "";
        String author = "";
        String category = "";
        String categoryToSearch = "";
        while (true) {
            System.out.println("----------------------------------------");
            System.out.println("PRESIONA 1 PARA MOSTRAR LAS NOTAS");
            System.out.println("PRESIONA 2 PARA AÑADIR UNA NUEVA NOTA");
            System.out.println("PRESIONA 3 PARA VER NOTAS POR CATEGORIA");
            System.out.println("----------------------------------------");
            option = sc.nextLine();
            sendOption(option);
            if (option.equals("1")) {
                for (Note n : getNotes()) {
                    System.out.println("Nota: " + n.getText() + " | Autor: " + n.getAuthor() + "| Tipo: " + n.getCategory());
                }
            } else if (option.equals("2")) {
                System.out.print("Nota: ");
                note = sc.nextLine();
                System.out.print("Autor: ");
                author = sc.nextLine();
                System.out.print("Tipo de nota: ");
                category = sc.nextLine();
                addNote(new Note(note, author, category));
                System.out.println("Nota enviada");
            } else if (option.equals("3")){
                System.out.print("Categoria: ");
                categoryToSearch = sc.nextLine();
                sendCategoryToSearch(categoryToSearch);
                for (Note n : getNotes()) {
                    System.out.println("Nota: " + n.getText() + " | Autor: " + n.getAuthor() + "| Tipo: " + n.getCategory());
                }
            }
        }
    }

    public void startConnection() {
        try {
            Socket socket = new Socket(HOST, PORT);
            this.is = socket.getInputStream();
            this.os = socket.getOutputStream();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Note> getNotes() {
        ObjectInputStream ois;
        List<Note> notes = null;
        try {
            ois = new ObjectInputStream(is);
            notes = (List<Note>) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return notes;
    }

    public void addNote(Note n) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(os);
            oos.writeObject(n);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendOption(String option) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(os);
            oos.writeObject(option);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void sendCategoryToSearch(String category){
         ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(os);
            oos.writeObject(category);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

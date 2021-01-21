package client;

public class Client {

    public static void main(String[] args) {
        ClientController cc = new ClientController();
        cc.startConnection();
        cc.showMenu();
    }
}

package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Network {
    private static boolean connection;
    private Socket socket;
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;
    private static ArrayList messageToServer;

    public Network(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер ожидает соединения..");
            socket = serverSocket.accept();
            System.out.println("Соединение установлено");
            connection = true;
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            messageToServer = new ArrayList();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

    }

    public static void write(Object object) {
        messageToServer.add(object);
    }

    public static Object read() throws IOException, ClassNotFoundException {
        return ois.readObject();
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void sendResponseToServer() throws IOException {
        oos.writeObject(messageToServer);
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void closeStream() throws IOException {
        ois.close();
        oos.close();
        socket.close();
        connection = false;
    }


    public static boolean isConnection() {
        return connection;
    }
}



import app.collection.City;
import io.Message;
import manager.ApplicationManager;
import network.Network;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        /**try {
            Network network = new Network(8000);
            Message message = (Message) network.read();
            String commandName = message.getCommandName();
            String arguments = message.getArgs();
            City city = message.getCity();

            network.write("Объект получен на сервере!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }*/
        ApplicationManager applicationManager = new ApplicationManager();
        applicationManager.initialisation();
    }
}

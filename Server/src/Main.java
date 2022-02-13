

import app.collection.City;
import io.Message;
import manager.ApplicationManager;
import network.Network;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        
        ApplicationManager applicationManager = new ApplicationManager();
        applicationManager.initialisation();
    }
}

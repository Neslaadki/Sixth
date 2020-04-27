import io.Message;

import java.io.IOException;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        System.out.println("Клиент запущен!");
        UserResponseValidator userResponseValidator;
        Network network;
        do {
            try {
                userResponseValidator = new UserResponseValidator();
                Message message = userResponseValidator.validate();
                if (userResponseValidator.getCommandName() != null){
                    network = new Network("localhost", 8080);
                    network.write(message);
                    ArrayList resopnse = (ArrayList) network.read();
                    resopnse.forEach(System.out::println);
                    network.closeStream();
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace(System.out);
            }
        }while (true);


    }
}

package manager;

import collectionManager.Invoker;
import collectionManager.Receiver;
import collectionManager.RepositoryOfCity;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import commands.*;
import csv.CSVReader;
import exceptions.ArgumentException;
import exceptions.MessageErrors;
import io.ConsoleReader;
import io.Message;
import network.Network;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Класс, в котором присходят инициализация объектов, отвечающих за работу приложения
 * Он имеет главный метод initialisation(), который создает эти объекты
 *
 * @see ApplicationManager#initialisation()
 */
public class ApplicationManager implements MessageErrors {

    private ConsoleReader input;
    private Invoker invoker;
    private RepositoryOfCity repositoryOfCity;
    private Receiver receiver;
    private String line = "";
    public static String path = "";
    private CSVReader csvReaderClass;
    private Network network;

    public ApplicationManager() {
        /**
         String directory = System.getenv("INPUT_LAB");
         if (directory == null || directory.equals("")) {
         path = "/Users/antondzubak/Documents/JavaApp/src/csvFiles/file";
         } else {
         path = directory;
         }*/
        String directory = System.getenv("INPUT_LAB");
        directory = "/Users/antondzubak/Documents/JavaApp/src/csvFiles/file";
        if (directory == null || directory.equals("")) {
            System.out.println("Ошибка! Проверьте, инициализирована ли переменная окружения 'INPUT_LAB'" + System.lineSeparator()
                    + "Она указывает на файл, хранящий коллекцию, в формате 'csv' - пр. INPUT_LAB='/home/s284728/programming/lab5/resources/files/cities.csv");
            System.out.println("Исправьте значение переменной окружения. Приложение не сможет сохранять объекты, поэтому оно экстренно закрылось ");
            System.exit(0);
        } else {
            path = directory;
        }

    }


    {

        receiver = new Receiver();
        input = new ConsoleReader();
        repositoryOfCity = new RepositoryOfCity();
        invoker = new Invoker();
        csvReaderClass = new CSVReader();
    }

    /**
     * Этот метод занимается созданием всех команд приложения
     * Тут происходит раздедение команд с аргументов и без, и отправка их на выполнение
     *
     * @throws IOException ошибка ввода/вывода, которая может тут возникнуть
     */

    public void initialisation() throws IOException {
        repositoryOfCity.getCitiesCollection().putAll(csvReaderClass.parseCSV());
        System.out.println("--------------------------------------------------------");
        System.out.println();

        Command add = new AddCommand(receiver);
        Command clear = new ClearCommand(receiver);
        Command averageMetersAboveSeaLeers = new AvarageOfMetersAboveSeaLevelCommand(receiver);
        Command help = new HelpCommand(receiver);
        Command info = new InfoCommand(receiver);
        Command insert = new InsertCommand(receiver);
        Command minByClimate = new MinByClimateCommand(receiver);
        Command printDescending = new PrintDescendingCommand(receiver);
        Command removeGreater = new RemoveGreaterCommand(receiver);
        Command removeLower = new RemoveLowerCommand(receiver);
        Command replaceIfGreater = new ReplaceIfGreaterCommand(receiver);
        Command show = new ShowCommand(receiver);
        Command updateId = new UpdateIdCommand(receiver);
        Command exit = new ExitCommand(receiver);
        Command save = new SaveCommand(receiver);
        Command executeScript = new ExecuteScriptCommand(receiver);
        invoker.setCommandMap(add.getName(), add);
        invoker.setCommandMap(clear.getName(), clear);
        invoker.setCommandMap(averageMetersAboveSeaLeers.getName(), averageMetersAboveSeaLeers);
        invoker.setCommandMap(help.getName(), help);
        invoker.setCommandMap(info.getName(), info);
        invoker.setCommandMap(insert.getName(), insert);
        invoker.setCommandMap(minByClimate.getName(), minByClimate);
        invoker.setCommandMap(printDescending.getName(), printDescending);
        invoker.setCommandMap(removeGreater.getName(), removeGreater);
        invoker.setCommandMap(removeLower.getName(), removeLower);
        invoker.setCommandMap(replaceIfGreater.getName(), replaceIfGreater);
        invoker.setCommandMap(show.getName(), show);
        invoker.setCommandMap(updateId.getName(), updateId);
        invoker.setCommandMap(exit.getName(), exit);
        invoker.setCommandMap(save.getName(), save);
        invoker.setCommandMap(executeScript.getName(), executeScript);

        //System.out.println(mainMessage());
        try {


            do {
                network = new Network(8080);
                Message message = (Message) network.read();
                    String command = message.getCommandName();
                System.out.println(message.getCommandName() + " " + message.getArgs() + " " + message.getCity());
                    if (message.getArgs() != null && message.getCity() == null) {
                        System.out.println(message.getArgs() + "!!!!");
                            invoker.execute(command, invoker.getCommandMap(), repositoryOfCity, message.getArgs());
                    } else if (message.getArgs() == null && message.getCity() == null) {
                            invoker.execute(command, invoker.getCommandMap(), repositoryOfCity);
                    } else if (message.getArgs() == null && message.getCity() != null) {
                            invoker.execute(command, invoker.getCommandMap(),repositoryOfCity,message.getCity());
                    } else {
                        Network.write(" Else !");
                        System.out.println("else");
                    }
                    network.sendResponseToServer();
                    network.closeStream();
            } while (true);

        } catch (NoSuchElementException | ClassNotFoundException ignored) {
        }

    }

    public String mainMessage() {
        return "Добрый День! Добро пожаловать в консольное приложение 'CityBuilder'!" + System.lineSeparator() +
                "by Дзюбак Антон и Митрофанов Егор - P3114 " + System.lineSeparator() +
                System.lineSeparator() + "Пожалуйста, введи 'help' в консоль, чтобы ознакомиться с функционалом приложения" + System.lineSeparator();
    }

}


import app.collection.building.CityBuilder;
import io.ConsoleReader;
import io.Message;

import java.io.IOException;

public class UserResponseValidator {

    private String commandName;
    private ConsoleReader consoleReader;
    private CityBuilder cityBuilder;

    public UserResponseValidator() throws IOException {
        consoleReader = new ConsoleReader();
        cityBuilder = new CityBuilder();
    }

    public String getCommandName() {
        return commandName;
    }

    public Message validate() throws IOException {
        while (true) {
            consoleReader.getDefaultConsoleSymbol();
            String line = consoleReader.getScanner().nextLine().trim();
            if (line.equals("")) {
                break;
            }
            String[] request = line.split(" ");
            commandName = request[0].toLowerCase();
            switch (commandName) {
                case "add":
                    System.out.println("Команда add выбрана");
                    return new Message(Commands.ADD.getCommandName(), new CityBuilder().create());
                case "average_of_meters_above_sea_levell":
                    System.out.println("Команда avarage_above_sea_level успешно выбрана");
                    return new Message(Commands.AVARAGEABOVESEALVL.getCommandName());
                case "clear":
                    System.out.println("Команда clear успешно выбрана");
                    return new Message(Commands.CLEAR.getCommandName());
                case "execute_script":
                    System.out.println("Команда execute_script успешно выбрана");
                    return new Message(Commands.EXECUTESCRIPT.getCommandName(), request[1].trim());
                case "exit":
                    System.out.println("Команда exit успешно выбрана");
                    return new Message(Commands.EXIT.getCommandName());
                case "help":
                    System.out.println("Команда help успешно выбрана");
                    return new Message(Commands.HELP.getCommandName());
                case "insert":
                    System.out.println("Команда insert успешно выбрана");
                    return new Message(Commands.INSERT.getCommandName(), request[1], new CityBuilder().create());
                case "min_by_climate":
                    System.out.println("Команда min_by_climate успешно выбрана");
                    return new Message(Commands.MINBYCLIMATE.getCommandName());
                case "print_descending":
                    System.out.println("Команда print_descending успешно выбрана");
                    return new Message(Commands.PRINTDESCENDING.getCommandName());
                case "remove_greater":
                    System.out.println("Команда insert успешно выбрана");
                    return new Message(Commands.REMOVEGREATER.getCommandName(), new CityBuilder().create());
                case "remove_lower":
                    System.out.println("Команда remove_lower успешно выбрана");
                    return new Message(Commands.REMOVELOVER.getCommandName(), new CityBuilder().create());
                case "replace_if_greater":
                    System.out.println("Команда replace_if_greater успешно выбрана");
                    return new Message(Commands.REPLACEIFGERATER.getCommandName(), new CityBuilder().create());
                case "save":
                    System.out.println("Команда save успешно выбрана");
                    return new Message(Commands.SAVE.getCommandName());
                case "show":
                    System.out.println("Команда show успешно выбрана");
                    return new Message(Commands.SHOW.getCommandName());
                case "update_id":
                    System.out.println("Команда update_id успешно выбрана");
                    return new Message(Commands.UPDATEID.getCommandName(), request[1].trim());
                default:
                    System.out.println("Вы ввели непрвильную команду. Проверьте правильность введенной команды.");
                    validate();
            }
        }
        System.out.println("Возвращаю null!");
        return null;
    }
}

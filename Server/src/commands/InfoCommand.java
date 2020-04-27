package commands;

import collectionManager.Invoker;
import collectionManager.Receiver;
import collectionManager.RepositoryOfCity;
import exceptions.ArgumentException;

import java.util.TreeMap;

public class InfoCommand extends Command {


    private Receiver receiver;


    public InfoCommand(Receiver receiver) {
        setName("info");
        setDescription("вывести информацию о коллекции");
        this.receiver = receiver;
    }


    @Override
    public void execute(TreeMap<String, Command> commandTreeMap, RepositoryOfCity repositoryOfCity,
                        Invoker commandManager, String... args) {
            receiver.info(repositoryOfCity);
    }
}

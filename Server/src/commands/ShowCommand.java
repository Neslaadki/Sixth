package commands;

import collectionManager.Invoker;
import collectionManager.Receiver;
import collectionManager.RepositoryOfCity;
import exceptions.ArgumentException;

import java.util.TreeMap;

public class ShowCommand extends Command {


    private Receiver receiver;


    public ShowCommand(Receiver receiver) {
        setName("show");
        setDescription("вывести коллекцию в строковом представлении");
        this.receiver = receiver;
    }


    @Override
    public void execute(TreeMap<String, Command> commandTreeMap, RepositoryOfCity repositoryOfCity,
                        Invoker commandManager) {
            receiver.show(repositoryOfCity);

    }
}

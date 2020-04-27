package commands;

import collectionManager.Invoker;
import collectionManager.Receiver;
import collectionManager.RepositoryOfCity;
import exceptions.ArgumentException;

import java.util.TreeMap;

public class ClearCommand extends Command {
    private Receiver receiver;

    public ClearCommand(Receiver receiver) {
        setName("clear");
        setDescription("Очистить коллекцию");
        this.receiver = receiver;
    }


    @Override
    public void execute(TreeMap<String, Command> treemap, RepositoryOfCity repositoryOfCity, Invoker commandManager) {
            receiver.clear(repositoryOfCity);
    }
}

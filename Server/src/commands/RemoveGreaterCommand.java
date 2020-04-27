package commands;

import app.collection.City;
import collectionManager.Invoker;
import collectionManager.Receiver;
import collectionManager.RepositoryOfCity;
import exceptions.ArgumentException;

import java.util.TreeMap;

public class RemoveGreaterCommand extends Command {


    private Receiver receiver;


    public RemoveGreaterCommand(Receiver receiver) {
        setName("remove_greater");
        setDescription("удалить из коллекции элементы, превышающие заданный");
        this.receiver = receiver;
    }


    @Override
    public void execute(TreeMap<String, Command> commandTreeMap, RepositoryOfCity repositoryOfCity,
                        Invoker commandManager, City city) {
            receiver.removeGreater(repositoryOfCity, city);

    }

}

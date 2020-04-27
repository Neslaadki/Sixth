package commands;

import app.collection.City;
import collectionManager.Invoker;
import collectionManager.Receiver;
import collectionManager.RepositoryOfCity;
import exceptions.ArgumentException;

import java.util.TreeMap;

public class InsertCommand extends Command {


    private Receiver receiver;


    public InsertCommand(Receiver receiver) {
        setName("insert");
        setDescription("добавить новый элемент с заданным ключом - [key]");
        this.receiver = receiver;
    }


    @Override
    public void execute(TreeMap<String, Command> commandTreeMap, RepositoryOfCity repositoryOfCity,
                        Invoker commandManager, City city,String... args) {
                int key = Integer.parseInt(args[0]);
                receiver.insert(repositoryOfCity,city, key);
    }
}


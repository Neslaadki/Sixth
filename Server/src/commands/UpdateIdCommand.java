package commands;

import app.collection.City;
import collectionManager.Invoker;
import collectionManager.Receiver;
import collectionManager.RepositoryOfCity;
import exceptions.ArgumentException;

import java.util.TreeMap;

public class UpdateIdCommand extends Command {


    private Receiver receiver;


    public UpdateIdCommand(Receiver receiver) {
        setName("update_id");
        setDescription("обновить элменет по заданному id - [key]");
        this.receiver = receiver;
    }


    public void execute(TreeMap<String, Command> commandTreeMap, RepositoryOfCity repositoryOfCity,
                        Invoker commandManager, City city, String... args) {
            int id = Integer.parseInt(args[0]);
            receiver.updateId(repositoryOfCity,city, id);
    }
}

package commands;

import collectionManager.Invoker;
import collectionManager.Receiver;
import collectionManager.RepositoryOfCity;
import exceptions.ArgumentException;

import java.io.IOException;
import java.util.TreeMap;

public class SaveCommand extends Command {

    private Receiver receiver;

    public SaveCommand(Receiver receiver) {
        this.receiver = receiver;
        setName("save");
        setDescription("сохранить объекты коллекции в файл");

    }

    @Override
    public void execute(TreeMap<String, Command> commandTreeMap, RepositoryOfCity repositoryOfCity,
                        Invoker commandManager){
            receiver.save(repositoryOfCity);

    }
}


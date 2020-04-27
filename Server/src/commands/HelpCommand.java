package commands;

import collectionManager.Invoker;
import collectionManager.Receiver;
import collectionManager.RepositoryOfCity;
import exceptions.ArgumentException;

import java.util.TreeMap;

public class HelpCommand extends Command {

    private Receiver receiver;

    public HelpCommand(Receiver receiver) {
        this.receiver = receiver;
        setName("help");
        setDescription("вывести справку по доступным командам");
    }

    @Override
    public void execute(TreeMap<String, Command> treeMap, RepositoryOfCity repositoryOfCity, Invoker commandManager) {
            receiver.help(treeMap);
    }
}

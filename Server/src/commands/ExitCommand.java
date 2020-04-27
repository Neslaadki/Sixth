package commands;

import collectionManager.Invoker;
import collectionManager.Receiver;
import collectionManager.RepositoryOfCity;
import exceptions.ArgumentException;

import java.util.TreeMap;

public class ExitCommand extends Command {

    private Receiver receiver;

    public ExitCommand(Receiver receiver) {
        this.receiver = receiver;
        setName("exit");
        setDescription("завершить программу (без сохранения в файл)");

    }

    @Override
    public void execute(TreeMap<String, Command> commandTreeMap, RepositoryOfCity repositoryOfCity,
                        Invoker commandManager) {
            receiver.exit();
    }
}

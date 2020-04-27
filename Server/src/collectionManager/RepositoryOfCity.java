package collectionManager;

import app.collection.City;
import app.collection.building.CityBuilder;
import app.collection.ui.IdGenerator;
import network.Network;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Класс, который хранит в себе коллекцию объектов и хранит реализациюю некоторых команд, связанных с коллекцией напрямую
 */

public class RepositoryOfCity {


    private final TreeMap<Integer, City> citiesCollection = new TreeMap<Integer, City>();
    private CityBuilder cityBuilder;
    private City city;
    private LocalDateTime birthdayOfTreemap;

    public RepositoryOfCity() {
        try {
            this.cityBuilder = new CityBuilder();
            this.birthdayOfTreemap = LocalDateTime.now();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Реализация добавления объекта в коллекцию
     */


    public void add(City city) {
        try {
            getCitiesCollection().put(city.getId(), city);
            Network.write("Объект добавлен");
        } catch (StackOverflowError e) {
            Network.write("Коллекция переполнена!");
        }
    }

    public void setCityBuilderPath(Scanner inputStream) {
        cityBuilder.setInputPath(inputStream);
    }

    /**
     * Реализация получение объекта из коллекции по id
     *
     * @param id значение id объекта, который хотим получить
     * @return возвращает объект, если в коллекции он присутстывует; иначе - сообщение об его отсутствии
     */


    public City get(int id) {
        if (getCitiesCollection().get(id) != null) {
            return getCitiesCollection().get(id);
        } else {
            Network.write("В коллекции нет объекта с заданным ключем!");
            return null;
        }

    }

    /**
     * Реализация удаления объекта из коллекции по заданному id
     *
     * @param id значение id элемента, который хотим удалить
     */


    public void remove(int id) {

        if (!getCitiesCollection().isEmpty()) {
            if (getCitiesCollection().get(id) != null) {
                Network.write("Объект " + getCitiesCollection().get(id).getName() + " удален");
                IdGenerator.remove(id);
                getCitiesCollection().remove(id);
            } else {
                Network.write("Не найден объект с данным id!");
            }
        } else {
            Network.write("Коллекция пустая. Удалить объект невозможно!");
        }
    }

    /**
     * Метод реализует вывод количества элементов, находящихся в коллекции в данный момент
     */

    public void size() {
        Network.write("В коллекции сейчас " + getCitiesCollection().size() + " объекта/ов");
    }

    /**
     * Реализация отчистки коллекции от всех объектов
     */

    public void clear() {
        if (!getCitiesCollection().isEmpty()) {
            getCitiesCollection().clear();
            IdGenerator.clear();
            Network.write("Коллекция успешно очищена!");
        } else {
            Network.write("Коллекция пустая!");
        }
    }

    /**
     * Реализация вывода всех объектов коллекции
     */

    public void show() {
        char codeDow = 2039;
        if (getCitiesCollection().isEmpty()) {
            Network.write("Коллекция пустая!");
        } else {
            for (Map.Entry<Integer, City> pair : getCitiesCollection().entrySet()) {
                Integer k = pair.getKey();
                City v = pair.getValue();
                Network.write("|" + codeDow + "|" + System.lineSeparator() + v.toString());

            }

        }

    }

    /**
     * Реализация команды удаления всех элементов, больших чем заданный
     */
    /**
     * @Override public void removeGreater() {
     * if (!getCitiesCollection().isEmpty()) {
     * city = cityBuilder.create();
     * for (Map.Entry<Integer, City> pair : getCitiesCollection().entrySet()) {
     * City v = pair.getValue();
     * Integer k = pair.getKey();
     * if (v.compareTo(city.getPopulation()) > 0) {
     * remove(k);
     * }
     * }
     * System.out.println("Удалены все элементы, большие чем введенный город: " + city.getName() + "с численностью населения:" + city.getPopulation());
     * } else {
     * System.out.println("Коллекция пустая!");
     * }
     * <p>
     * }
     */
    public void removeGreater(City city) {
        if (!getCitiesCollection().isEmpty()) {
            for (Map.Entry<Integer, City> pair : getCitiesCollection().entrySet()) {
                City v = pair.getValue();
                Integer k = pair.getKey();
                if (v.compareTo(city.getPopulation()) > 0) {
                    remove(k);

                }
            }
            Network.write("Удалены все элементы, большие чем введенный город: " + city.getName() + "с численностью населения:" + city.getPopulation());
        } else {
            Network.write("Коллекция пустая!");
        }
    }

    /**
     * Реализация команды удаления всех элементов, меньших чем заданный
     */

    public void removeLower(City city) {
        if (!getCitiesCollection().isEmpty()) {
            for (Map.Entry<Integer, City> pair : getCitiesCollection().entrySet()) {
                City v = pair.getValue();
                Integer k = pair.getKey();
                if (v.compareTo(city.getPopulation()) < 0) {
                    remove(k);

                }
            }
            Network.write("Удалены все элементы, меньшие чем введенный город: " + city.getName() + "с численностью населения:" + city.getPopulation());
        } else {
            Network.write("Коллекция пустая!");
        }

    }

    public TreeMap<Integer, City> getCitiesCollection() {
        return citiesCollection;
    }

    /**
     * Реализация команды вывода информации о коллекции обхектов
     */
    public void info() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String collectionCreationDate = getBirthdayOfTreemap().format(formatter);
        Network.write("Информация о коллекции: " + System.lineSeparator() +
                "- время инициализации коллекции: " + collectionCreationDate + System.lineSeparator() +
                "- типа коллекции: " + getCitiesCollection().getClass().getTypeName() + System.lineSeparator() +
                "- количество элементов коллекции: " + getCitiesCollection().size());
    }


    public LocalDateTime getBirthdayOfTreemap() {
        return birthdayOfTreemap;
    }

    /**
     * Реализация команды обновления элемента по заданному id
     *
     * @param id id элменета, который хотим обновить
     */


    public void updateId(City city, Integer id) {
        if (!getCitiesCollection().isEmpty()) {
            if (getCitiesCollection().get(id) == null) {
                Network.write("Объекта с заданным id не существует" + System.lineSeparator() + "Обновление объекта невозможно!");
            } else {
                getCitiesCollection().remove(id, get(id));
                city.setId(id);
                getCitiesCollection().put(id, city);
                Network.write("Поле успешно обновлено!");
            }
        } else {
            Network.write("Коллекция пустая!");
        }

    }

    /**
     * Реализация метода, который выводит среднее значение поля высоты над уровнем моря всех объектов
     */

    public void averageMetersAboveSeaLevel() {
        long citymeterAboveSeaLevel = 0;
        for (Map.Entry<Integer, City> pair : getCitiesCollection().entrySet()) {
            City v = pair.getValue();
            citymeterAboveSeaLevel = citymeterAboveSeaLevel + v.getMetersAboveSeaLevel();
        }
        Network.write(citymeterAboveSeaLevel);
        try {
            citymeterAboveSeaLevel /= getCitiesCollection().size();
            Network.write("Среднее значение поля 'MetersAboveSeaLevel' элементов коллекции = " + citymeterAboveSeaLevel);
        } catch (ArithmeticException e) {
            Network.write("Т.к. в коллекции нет элементов - среднеее значение = 0");
        }
    }

    /**
     * Реализация вывода элемента коллекции с минимальным значением поля Климат
     */


    public void minByClimate() {
        City city = null;
        int minValue = 4;

        if (!getCitiesCollection().isEmpty()) {
            for (Map.Entry<Integer, City> pair : getCitiesCollection().entrySet()) {
                City v = pair.getValue();
                if (v.getClimate() != null) {
                    if (v.getClimate().getNumber() < minValue) {
                        city = v;
                        minValue = v.getClimate().getNumber();
                    }
                }
            }
            if (city != null) {
                Network.write(city.toString());
            } else {
                Network.write("В коллекции нет объектов с заданным полем 'Climate', сортировка невозможна..");
            }

        } else {
            Network.write("Коллекция пустая!");
        }

    }

    /**
     * Реализация вывода элементов коллекции в порядке возрастания
     */


    public void printDescending() {
        if (!getCitiesCollection().isEmpty()) {
            ArrayList<City> citiesArray = new ArrayList<>();
            for (Map.Entry<Integer, City> pair : getCitiesCollection().entrySet()) {
                City v = pair.getValue();
                citiesArray.add(v);
            }
            citiesArray.sort((o1, o2) -> -o1.compareTo(o2.getPopulation()));
            for (City city : citiesArray) {
                Network.write(city.toString());
            }
        } else {
            Network.write("Коллекция пустая!");
        }
    }
    //new ComparatorPopulation().reversed()

    /**
     * Реализация команды добавления элемента по заданному id
     *
     * @param key сам id, с которым мы хотим создать элемент
     */


    public void insert(City city, int key) {

        if (getCitiesCollection().get(key) == null) {
            city.setId(key);
            getCitiesCollection().put(city.getId(), city);
            Network.write(city.getName() + "добавлен в коллекцию!");
        } else {
            Network.write("По данному адресу ячейка уже занята!");
        }
    }

    /**
     * Реализация команды замены элемента по заданному id, если он больше, чем старый
     *
     * @param key id, по которому хотим произвести замену
     */

    public void replaceIfGreater(City city, int key) {
        if (!getCitiesCollection().isEmpty()) {
            if (city.getPopulation() > get(key).getPopulation()) {
                getCitiesCollection().put(key, city);
                Network.write("Элемент успешно обновлен");
            } else {
                Network.write("Значение нового элемента меньше старого!");
            }
        } else {
            Network.write("Коллекция пустая!");

        }


    }
}


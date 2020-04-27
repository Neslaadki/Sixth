package io;

import app.collection.City;

import java.io.Serializable;

public class Message implements Serializable {
    private String commandName;
    private String args;
    private City city;


    public Message(String commandName) {
        this.commandName = commandName;
    }

    public Message(String commandName, String args) {
        this.commandName = commandName;
        this.args = args;
    }

    public Message(String commandName, City objectCity) {
        this.commandName = commandName;
        city = objectCity;
    }

    public Message(String commandName, String args, City objectCity) {
        this.commandName = commandName;
        this.args = args;
        city = objectCity;
    }


    public String getCommandName() {
        return commandName;
    }

    public String getArgs() {
        return args;
    }

    public City getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "ResponseCommand{" +
                "commandName='" + commandName + '\'' +
                ", args='" + args + '\'' +
                ", city=" + city +
                '}';
    }
}

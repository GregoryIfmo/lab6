package ClOb;

import collection.LabWork;

import java.io.Serializable;

public class ClientObject implements Serializable {
    String[] command;
    LabWork labWork;


    public String[] getCommand() {
        return command;
    }

    public LabWork getObject() {
        return labWork;
    }

    public void setCommand(String[] command) {
        this.command = command;
    }

    public void setLabWork(LabWork labWork) {
        this.labWork = labWork;
    }
}

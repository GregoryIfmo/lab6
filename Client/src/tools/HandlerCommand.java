package tools;

import ClOb.ClientObject;
import ClientPackage.Client;
import collection.LabWork;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class HandlerCommand {
    private ClientObject obj;
    private String[] comannd;
    private Client client;
    private boolean flag = true;


    public void HandlerCommandRead(ClientObject obj,Client client) throws IOException {
        this.obj = obj;
        comannd = obj.getCommand();
        Client clientRead = client;
        switch (comannd[0]){
            case "help":
                for(int i=1; i<comannd.length; i++){
                    System.out.println(comannd[i]);
                }
                break;
            case "show":
                int amount = Integer.parseInt(comannd[1]);
                for(int i=0;i<amount;i++){
                    System.out.println(clientRead.readHandle().getCommand()[0]);

                }
                System.out.println(clientRead.readHandle().getCommand()[0]);
                break;
            case "clear":

                System.out.println(comannd[1]);
                break;


            case "info":

                System.out.println(comannd[1]);
                System.out.println(comannd[2]);
                System.out.println(comannd[3]);
                break;
            case "add":
                System.out.println(comannd[1]);

                break;
            case "update":

                if(comannd.length==7){
                    for(int i=1;i<comannd.length;i++){
                        System.out.println(comannd[i]);
                        System.out.println();
                    }
                    Validation validation = new Validation();
                    LabWork t = obj.getObject();
                    Scanner scanner = new Scanner(System.in);
                    switch (scanner.nextLine().trim()) {
                        case "1":
                            while (!validation.checkDiscipline(t)) ;
                            break;
                        case "2":
                            while (!validation.checkName(t)) ;
                            break;
                        case "3":
                            while (!validation.createCoordinates(t)) ;
                            break;
                        case "4":
                            while (!validation.setDifficulty(t)) ;
                            break;
                        case "5":
                            while (!validation.checkMinimalPoint(t)) ;
                            break;
                        default:
                            System.out.println("Введённое значение оказалось некорректным(");
                    }
                    obj.setLabWork(t);
                    client.writeHandle(obj);
                    System.out.println("Элемент обновлён");
                }
                else {
                    System.out.println(comannd[1]);
                }
                break;
            case "remove_by_id":
                System.out.println(comannd[1]);
                break;
            case "remove_first":
                System.out.println(comannd[1]);
//                handlerCommand.commandRemoveFirst();
                break;
            case "remove_any_by_minimal_point":
                System.out.println(comannd[1]);
                break;

            case "sum_of_minimal_point":

                System.out.println(comannd[1]);
//                handlerCommand.commandSumOfMinimalPoint();
                break;
            case "add_if_max":
                System.out.println(comannd[1]);

//                handlerCommand.commandAddIfMax(labwork);
                break;

            case "execute_script":
                if(checkCommndLine(1,comannd)){
                    break;
                }
//                handlerCommand.commandsExecuteScript(handlerCommand, comannd[1]);
                break;

            case "min_by_creation_date":
                System.out.println(comannd[1]);
//                handlerCommand.commandCreationDate();
                break;



            default:
                System.out.println("Такой команды не существует. Воскользуйтесь help для получения всех возможных команд");
                break;

        }
    }

    public boolean HandlerCommandWrite(Client client) throws IOException {
        this.client = client;
        Scanner scanner =new Scanner(System.in);
        comannd=scanner.nextLine().toLowerCase().trim().split(" ");
        ClientObject objWrite = new ClientObject();
        switch (comannd[0]){
            case "help":
                if(checkCommndLine(0,comannd)){
                    break;
                }
                objWrite.setCommand(comannd);
                client.writeHandle(objWrite);
                break;
            case "exit":
                if(checkCommndLine(0,comannd)){
                    break;
                }
                objWrite.setCommand(comannd);
                client.writeHandle(objWrite);
                System.out.println("Программа завершена, данные сохранены");
                flag = false;

                break;
            case "show":
                if(checkCommndLine(0,comannd)){
                    break;
                }
                objWrite.setCommand(comannd);
                client.writeHandle(objWrite);
                break;
            case "clear":
                if(checkCommndLine(0,comannd)){
                    break;
                }
                objWrite.setCommand(comannd);
                client.writeHandle(objWrite);
                break;

            case "info":
                if(checkCommndLine(0,comannd)){
                    break;
                }
                objWrite.setCommand(comannd);
                client.writeHandle(objWrite);
                break;
            case "add":
                if(checkCommndLine(0,comannd)){
                    break;
                }
                objWrite.setLabWork(commandAdd());
                objWrite.setCommand(comannd);
                client.writeHandle(objWrite);

                break;
            case "update":
                if(checkCommndLine(1,comannd)){
                    break;
                }
                try{
                    Long.parseLong(comannd[1]);
                    long id = Long.parseLong(comannd[1]);
                    objWrite.setCommand(comannd);
                    client.writeHandle(objWrite);
//                    handlerCommand.commandUpdate(id);
                }catch (NumberFormatException e){
                    System.out.println("Введённые данные не корректны");
                }
                break;
            case "remove_by_id":
                if(checkCommndLine(1,comannd)){
                    break;
                }
                try{
                    Long.parseLong(comannd[1]);
                    long id = Long.parseLong(comannd[1]);
                    objWrite.setCommand(comannd);
                    client.writeHandle(objWrite);
                    //handlerCommand.commandRemove(id);
                }catch (NumberFormatException e){
                    System.out.println("Введённые данные не корректны");
                }
                break;
            case "remove_first":
                if(checkCommndLine(0,comannd)){
                    break;
                }
                objWrite.setCommand(comannd);
                client.writeHandle(objWrite);
//                handlerCommand.commandRemoveFirst();
                break;
            case "remove_any_by_minimal_point":
                if(checkCommndLine(1,comannd)){
                    break;
                }
                try{
                    Double.parseDouble(comannd[1]);
                    Double mp = Double.parseDouble(comannd[1]);
                    objWrite.setCommand(comannd);
                    client.writeHandle(objWrite);
//                    handlerCommand.commandRemoveByMinimalPoint(mp);
                }catch (NumberFormatException e){
                    System.out.println("Введённые данные не корректны");
                }
                break;

            case "sum_of_minimal_point":
                if(checkCommndLine(0,comannd)){
                    break;
                }
                objWrite.setCommand(comannd);
                client.writeHandle(objWrite);
//                handlerCommand.commandSumOfMinimalPoint();
                break;
            case "add_if_max":
                if(checkCommndLine(0,comannd)){
                    break;
                }
                objWrite.setLabWork(commandAdd());
                objWrite.setCommand(comannd);
                client.writeHandle(objWrite);
//                handlerCommand.commandAddIfMax(labwork);
                break;

            case "execute_script":
                if(checkCommndLine(1,comannd)){
                    break;
                }
                objWrite.setCommand(comannd);
                client.writeHandle(objWrite);
//                handlerCommand.commandsExecuteScript(handlerCommand, comannd[1]);
                break;

            case "min_by_creation_date":
                if(checkCommndLine(0,comannd)){
                    break;
                }
                objWrite.setCommand(comannd);
                client.writeHandle(objWrite);
//                handlerCommand.commandCreationDate();
                break;
            case "data.json":
                objWrite.setCommand(comannd);
                client.writeHandle(objWrite);
                break;


            default:
                System.out.println("Такой команды не существует. Воскользуйтесь help для получения всех возможных команд");
                HandlerCommandWrite(client);
                break;

        }
        if(flag == true){
            return true;
        }
        else {
            return false;
        }
    }
    private boolean checkCommndLine(int arguments,String[] comanndLine) throws IOException {
        if (arguments != comanndLine.length - 1) {
            System.out.println("данная команда принемает " + arguments + " аргументов");
            HandlerCommandWrite(client);
            return true;
        } else {
            return false;
        }
    }
    public LabWork commandAdd()  {

        LabWork labWork = new LabWork();
        Validation validation = new Validation();
//        long size = collection.size();
//        labWork.setId(size);
        while (!validation.checkName(labWork));
        while (!validation.checkDiscipline(labWork));
        while (!validation.createCoordinates(labWork));
        while (!validation.setDifficulty(labWork));
        while (!validation.checkMinimalPoint(labWork));
        Date time = new Date();
        System.out.println(time);
        labWork.setCreationDate(time);
        return labWork;

    }
}

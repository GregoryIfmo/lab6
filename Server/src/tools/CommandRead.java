package tools;


import ClOb.ClientObject;
import ServerPackage.*;
import collection.LabWork;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class CommandRead {
    private String[] comannd;
    private HandlerCommand handlerCommand;
    private boolean flag = true;
    private String filleName;
    File file;

    public CommandRead(HandlerCommand handlerCommand) {
        this.handlerCommand = handlerCommand;
    }


    /**
     * Считывает команды из командной строки
     */

    public void reader(Server server) throws IOException, ClassNotFoundException {
        Server server1 = server;

//        server1.writeHandle();
        System.out.println("Введите команду. Для получения списка всех возможных команд введите help");
        while(flag){
//            Scanner scanner =new Scanner(System.in);
//            comannd=scanner.nextLine().toLowerCase().trim().split(" ");
            ClientObject object = server1.readHandle();
            comannd = object.getCommand();
            LabWork labwork = object.getObject();

            switch (comannd[0]){
                case "help":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    server.writeHandle(handlerCommand.commandsHelp());
                    break;
                case "exit":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }

                    handlerCommand.commandsSave();

                    break;
                case "show":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }

                    handlerCommand.commandShow(server1);

                    break;

                case "clear":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    server.writeHandle(handlerCommand.commandClear());
                    break;
                case "save":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    handlerCommand.commandsSave();
                    break;
                case "info":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    server.writeHandle(handlerCommand.commandsInfo());
                    break;
                case "add":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    server.writeHandle(handlerCommand.commandAdd(object.getObject()));

                    break;
                case "update":
                    if(checkCommndLine(1,comannd)){
                        break;
                    }
                    try{
                        Long.parseLong(comannd[1]);
                        long id = Long.parseLong(comannd[1]);
                        handlerCommand.commandUpdate(id, server);
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
                        server.writeHandle(handlerCommand.commandRemove(id));
                    }catch (NumberFormatException e){
                        System.out.println("Введённые данные не корректны");
                    }
                    break;
                case "remove_first":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    server.writeHandle(handlerCommand.commandRemoveFirst());
                    break;
                case "remove_any_by_minimal_point":
                    if(checkCommndLine(1,comannd)){
                        break;
                    }
                    try{
                        Double.parseDouble(comannd[1]);
                        Double mp = Double.parseDouble(comannd[1]);
                        server.writeHandle(handlerCommand.commandRemoveByMinimalPoint(mp));

                    }catch (NumberFormatException e){
                        System.out.println("Введённые данные не корректны");
                    }
                    break;

                case "sum_of_minimal_point":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    server.writeHandle(handlerCommand.commandSumOfMinimalPoint());
                    break;
                case "add_if_max":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    server.writeHandle(handlerCommand.commandAddIfMax(object.getObject()));

                    break;

                case "execute_script":
                    if(checkCommndLine(1,comannd)){
                        break;
                    }
                    handlerCommand.commandsExecuteScript(handlerCommand, comannd[1]);
                        break;

                case "min_by_creation_date":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    server.writeHandle(handlerCommand.commandCreationDate());

                    break;



                default:
                    System.out.println("Такой команды не существует. Воскользуйтесь help для получения всех возможных команд");
                    break;



            }
        }
        System.out.println("Программа завершена");


    }
    /**
     * Проверяет количество аргементов введеное пользователем
     * @param arguments количество аргументов
     * @param comanndLine команда введеная пользователем разбитая по пробелам
     * @return возвращает true если данная команда не имеет введеное количество аргементов
     */
    private boolean checkCommndLine(int arguments,String[] comanndLine){
        if(arguments!=comanndLine.length-1){
            System.out.println("данная команда принемает "+ arguments +" аргументов");
            return true;
        }else {
            return false;
        }

    }
    public void setFlag(boolean flag){
        this.flag = flag;
    }
}
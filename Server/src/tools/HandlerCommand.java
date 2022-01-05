package tools;

import ClOb.ClientObject;
import ServerPackage.Server;
import collection.*;
//import collection.LabWork;
//import collection.Discipline;
//import collection.Difficulty;


import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class HandlerCommand {
    private FileWorker fileWorker;

    private ArrayDeque<LabWork> collection;
    Gson g = new Gson();
    /**
     * Конструктор для задания обьекта, который работает с файлами и коллекции
     */

    public HandlerCommand(ArrayDeque<LabWork> collection, FileWorker fileWorker) {
        this.collection = collection;
        this.fileWorker = fileWorker;

    }


    private ArrayList<String> allcomands = new ArrayList<String>();

    {
        allcomands.add("0");
        allcomands.add("help                        : вывести справку по доступным командам");
        allcomands.add("info                        : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        allcomands.add("show                        : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        allcomands.add("add {element}               : добавить новый элемент в коллекцию");
        allcomands.add("update id {element}         : обновить значение элемента коллекции, id которого равен заданному");
        allcomands.add("remove_by_id id             : удалить элемент из коллекции по id");
        allcomands.add("clear                       : очистить коллекцию");
        allcomands.add("execute_script file_name    : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        allcomands.add("exit                        : завершить программу и сохранить коллекцию в файл");
        allcomands.add("remove_first                : удалить из коллекции первый элемент");
        allcomands.add("add_if_max                  : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        allcomands.add("remove_any_by_minimal_point minimalPoint         : удалить из коллекции один элемент, значение поля minimalPoint которого эквивалентно заданному");
        allcomands.add("sum_of_minimal_point        : вывести сумму значений поля minimalPoint для всех элементов коллекции");
        allcomands.add("min_by_creation_date        : вывести любой объект из коллекции, значение поля creationDate которого является минимальным");

    }
    Scanner scanner=new Scanner(System.in);

    /**
     * Обрабатывает команду "Help"
     */
    public ClientObject commandsHelp() {
        ClientObject commands = new ClientObject();
        String[] newcommands = new String[15];
//        for (String command : allcomands) {
//            System.out.println(command);
//
//
//        }
        for(int i = 0; i< newcommands.length; i++){
            newcommands[i]= allcomands.get(i);
        }
        newcommands[0]="help";
        commands.setCommand(newcommands);
        return commands;
    }
    /**
     * Обрабатывает команду "Info"
     */
    public ClientObject commandsInfo() {
        ClientObject obj = new ClientObject();
        String[] str = new String[4];
        str[0]="info";
        str[1]= "Колличество элементов в коллекции           : "+Integer.toString(collection.size());
        str[2] ="Дата загрузки коллекции из файла            : " +  fileWorker.dateDownloads;
        System.out.println("Колличество элементов в коллекции           : "+Integer.toString(collection.size()));
        System.out.println("Дата загрузки коллекции из файла            : " +  fileWorker.dateDownloads);
        if(fileWorker.datePreservation==null){
            str[3] ="Сохранения коллекции в файл в текущей сессии не происходило";
            System.out.println("Сохранения коллекции в файл в текущей сессии не происходило");
        }else {
            str[3]="Дата последнего сохранения коллекции в файл : " + fileWorker.datePreservation;
            System.out.println("Дата последнего сохранения коллекции в файл : " + fileWorker.datePreservation);
        }
        obj.setCommand(str);
        return obj;

    }

    /**
     * Обрабатывает команду "Show"
     */
    public void commandShow(Server server) throws IOException {
        Server serverShow = server;
        String[] commandShow = new String[]{"show", String.valueOf(collection.size())};
        ClientObject cS = new ClientObject();
        cS.setCommand(commandShow);
        serverShow.writeHandle(cS);
        try {
            for (LabWork t : collection) {
                commandShow[0] = "id лабораторной работы:" + t.getId() + ", дисциплина: " + t.getDiscipline() + "\n" + "название лабораторной работы: " + t.getName() + ", место: " + t.getCoordinates() + ", сложность: " + t.getDifficulty() + ", минимальный бал: " + t.getMinimalPoint()+", дата создания: "+ t.getCreationDate();
                cS.setCommand(commandShow);
                serverShow.writeHandle(cS);
                System.out.println("id лабораторной работы:" + t.getId() + ", дисциплина: " + t.getDiscipline() + "\n" + "название лабораторной работы: " + t.getName() + ", место: " + t.getCoordinates() + ", сложность: " + t.getDifficulty() + ", минимальный бал: " + t.getMinimalPoint()+", дата создания: "+ t.getCreationDate());
            }
            commandShow[0] ="Все элементы коллекции успешно выведены";
            cS.setCommand(commandShow);
            serverShow.writeHandle(cS);
            System.out.println("Все элементы коллекции успешно выведены");
        }catch (NullPointerException e){
            commandShow[0] ="Коллекция пуста";
            cS.setCommand(commandShow);
            serverShow.writeHandle(cS);
            System.out.println("Коллекция пуста");
        }

    }

    public ClientObject commandAdd(LabWork labWork)  {

          LabWork labWorkadd = labWork;
//        Validation validation = new Validation(collection);
          long size = collection.size();
          labWorkadd.setId(size);
//        while (!validation.checkName(labWork));
//        while (!validation.checkDiscipline(labWork));
//        while (!validation.createCoordinates(labWork));
//        while (!validation.setDifficulty(labWork));
//        while (!validation.checkMinimalPoint(labWork));
//        Date time = new Date();
//        System.out.println(time);
//        labWork.setCreationDate(time);
        collection.addLast(labWorkadd);
        ClientObject obj = new ClientObject();
        String[] str = new String[2];
        str[0]="add";
        str[1]="Элемент успешно добавлен";
        obj.setCommand(str);
        return obj;


    }
    public void ExCommandAdd(String name, String discipline, String selfStudy, String x, String y, String difficulty, String minp)  {

        LabWork labWork = new LabWork();
        Validation validation = new Validation(collection);
        long size = collection.size();
        labWork.setId(size);
        int checkEx = 0;
        try{

            labWork.setName(name);
            checkEx +=1;
            long val = Long.valueOf(selfStudy);
            Discipline dis = new Discipline(discipline, val);
            labWork.setDiscipline(dis);
            checkEx +=1;
            int x2 = Integer.valueOf(x);
            checkEx +=1;
            double y2 = Double.valueOf(y);
            checkEx +=1;
            Coordinates n = new Coordinates(x2,y2);
            labWork.setCoordinates(n);
            if(difficulty.equals("normal") ){
                labWork.setDifficulty(Difficulty.NORMAL);
                checkEx +=1;
            }
            else if(difficulty.equals("hopeless") ){
                labWork.setDifficulty(Difficulty.HOPELESS);
                checkEx +=1;
            }
            else if(difficulty.equals("terrible") ){
                labWork.setDifficulty(Difficulty.TERRIBLE);
                checkEx +=1;
            }
            else {
                System.out.println("Введён неверный формат сложности");
            }

            double mp = Double.valueOf(minp);
            labWork.setMinimalPoint(mp);
            checkEx +=1;
        }catch (NumberFormatException e){
            switch (checkEx){
                case 0:
                    System.out.println("Неверный формат данных для имени");
                    break;
                case 1:
                    System.out.println("Неверный формат данных для количества часов самообучения");
                    break;
                case 2:
                    System.out.println("Неверный формат данных для этажа");
                    break;
                case 3:
                    System.out.println("Неверный формат данных для номера кабинета");
                    break;
                case 4:
                    System.out.println("Неверный формат данных для сложности");
                    break;
                case 5:
                    System.out.println("Неверный формат данных для минимального балла");
                    break;
            }
        }

        Date time = new Date();
        System.out.println(time);
        labWork.setCreationDate(time);
        collection.addLast(labWork);

    }

    public void commandHead(){
        collection.element();

    }

    public void commandUpdate(Long id, Server server) throws IOException, ClassNotFoundException {

        int flag2= 0;
        for(LabWork t : collection) {
            if(t.getId()==id){
                flag2 = 1;

                ClientObject obj = new ClientObject();
                String[] str = new String[7];
                str[0]="update";
                str[1]="Введите номер поля, которое вы хотите изменить";
                str[2]="Дисциплина           - 1";
                str[3]="Название лабы        - 2";
                str[4]="Место                - 3";
                str[5]="Сложность            - 4";
                str[6]="Минимальный балл     - 5";
                obj.setCommand(str);
                obj.setLabWork(t);

                server.writeHandle(obj);
                collection.remove(t);
                obj =server.readHandle();
                t= obj.getObject();
                if(t == null){
                    System.out.println("empty obj");
                }
                else {
                    collection.add(t);
                }
                break;
            }

        }
        if(flag2==0){
            ClientObject obj = new ClientObject();
            String[] str = new String[2];
            str[0]="update";
            str[1]="Элемент с таким id не существует";
            obj.setCommand(str);
            server.writeHandle(obj);
            System.out.println("Элемент с таким id не существует");
        }
    }

    public ClientObject commandRemove(Long id){
        int flag = 0;
        ClientObject obj = new ClientObject();
        String[] str = new String[2];
        str[0]="remove_by_id";
        obj.setCommand(str);
        for(LabWork t : collection) {
            if (t.getId() == id) {
                flag =1;
                collection.remove(t);
                str[1]="Элемент успешно удалён";
                System.out.println("Элемент успешно удалён");
            }
        }
        int xxx =-1;
        for(LabWork g: collection) {
            xxx+=1;
            g.setId(xxx);

        }
        if(flag == 0){
            str[1]="Элемент с таким id не существует";
            System.out.println("Элемент с таким id не существует");
        }
        obj.setCommand(str);
        return obj;

    }

    public ClientObject commandClear(){
        collection.clear();
        ClientObject obj = new ClientObject();
        String[] str = new String[]{"clear","Элементы удалены"};
        obj.setCommand(str);
        System.out.println("Элементы удалены");
        return obj;
    }

    public ClientObject commandSumOfMinimalPoint(){
        int sum = 0;
        for(LabWork t : collection) {
            sum+=t.getMinimalPoint();
        }
        System.out.println("Сумма минимальных баллов равна: "+sum);
        ClientObject obj = new ClientObject();
        String[] str = new String[2];
        str[0]="sum_of_minimal_point";
        str[1]="Сумма минимальных баллов равна: "+sum;
        obj.setCommand(str);
        return obj;
    }

    public ClientObject commandRemoveByMinimalPoint(Double mp){
        int flag3 = 0;
        ClientObject obj = new ClientObject();
        String[] str = new String[2];
        obj.setCommand(str);
        str[0]="remove_any_by_minimal_point";
        for(LabWork t : collection) {
            if (t.getMinimalPoint() == mp) {
                flag3 =1;
                commandRemove(t.getId());
                str[1]="Элемент с id: "+t.getId()+" успешно удалён";
            }

        }
        if(flag3==0){
            str[1]="Элемент с таким mp не существует";
            System.out.println("Элемент с таким mp не существует");
        }
        obj.setCommand(str);
        return obj;

    }

    public ClientObject commandAddIfMax(LabWork labWork) {
        int x = collection.size();
        commandAdd(labWork);
        ClientObject obj = new ClientObject();
        String[] str = new String[2];
        str[0]="add_if_max";

        LabWork max = Collections.max(collection);
        if(max.getId()==x+1){
            str[1]="Элемент добавлен";
            System.out.println("Элемент добавлен");
            obj.setCommand(str);
            return obj;
        }else{
            for(LabWork t : collection) {
                if(t.getId()==x+1){
                    collection.remove(t);
                    str[1]="Элемент оказался меньше";
                    System.out.println("Элемент оказался меньше");
                    obj.setCommand(str);

                }

            }
            return obj;
        }
    }

    public void commandAddIfMaxEx(String name, String discipline, String selfStudy, String x, String y, String difficulty, String minp) {
        int xEx = collection.size();
        ExCommandAdd(name,discipline,selfStudy,x, y,difficulty, minp);
        LabWork max = Collections.max(collection);
        if(max.getId()==xEx+1){
            System.out.println("Элемент добавлен");
        }else{
            for(LabWork t : collection) {
                if(t.getId()==xEx+1){
                    collection.remove(t);
                    System.out.println("Элемент оказался меньше");
                }

            }
        }

    }

    public ClientObject commandRemoveFirst(){
        if(collection.size()==0){
            ClientObject obj = new ClientObject();
            String[] str = new String[2];
            str[0]="remove_first";
            str[1]="Коллекция пуста";
            obj.setCommand(str);
            return obj;
        }
       else {
            collection.removeFirst();
            int x =-1;
            for(LabWork g: collection){
                g.setId(x+1);
            }
            ClientObject obj = new ClientObject();
            String[] str = new String[2];
            str[0]="remove_first";
            str[1]="Первый элемент успешно удалён";
            obj.setCommand(str);
            return obj;
        }
    }

    public void commandsSave() {
        fileWorker.filleWrite();
    }

    public void commandsExecuteScript(HandlerCommand handlerCommand, String filleName) {
        CommandsExecuteScript commandsExecuteScript =new CommandsExecuteScript(filleName,handlerCommand,fileWorker);
        try {
            commandsExecuteScript.startRead();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        commandsExecuteScript=null;
        System.gc();
    }

    public ClientObject commandCreationDate(){
        if(collection.size()==0){
            ClientObject obj = new ClientObject();
            String[] str = new String[2];
            str[0]="min_by_creation_date";
            str[1]="коллекция пуста";
            obj.setCommand(str);
            return obj;
        }
        else {
            LabWork x =collection.element();
            Date min = x.getCreationDate();
            //long minminutes= ChronoUnit.MINUTES.between(min,min);
            for(LabWork e:collection){
//            long minutes = ChronoUnit.MINUTES.between(min, e.getCreationDate());
                Date min2 = e.getCreationDate();
                if(min.after(min2)){
                    min=e.getCreationDate();
                }
            }
            ClientObject obj = new ClientObject();
            String[] str = new String[2];
            str[0]="min_by_creation_date";
            str[1]="0";
            obj.setCommand(str);
            for(LabWork t: collection){
                if(t.getCreationDate() == min){
                    System.out.println("id лабораторной работы:" + t.getId() + ", дисциплина: " + t.getDiscipline() + "\n" + "название лабораторной работы: " + t.getName() + ", место: " + t.getCoordinates() + ", сложность: " + t.getDifficulty() + ", минимальный бал: " + t.getMinimalPoint()+", дата создания: "+ t.getCreationDate());
                    str[1]="id лабораторной работы:" + t.getId() + ", дисциплина: " + t.getDiscipline() + "\n" + "название лабораторной работы: " + t.getName() + ", место: " + t.getCoordinates() + ", сложность: " + t.getDifficulty() + ", минимальный бал: " + t.getMinimalPoint()+", дата создания: "+ t.getCreationDate();
                }
            }
            obj.setCommand(str);
            return obj;
        }

    }
}

package tools;

import ServerPackage.Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandsExecuteScript {
    private String filleName;
    File file;
    HandlerCommand handlerCommand;
    FileWorker fileWorker;
    private ArrayList<String> commands = new ArrayList<String>();
    Scanner scanner;
    String[] comannd;
    ArrayList<File> st = new ArrayList<File>();
    int flag=0;

    public CommandsExecuteScript(String filleName,HandlerCommand handlerCommand, FileWorker fileWorker){
        this.filleName=filleName;
        this.handlerCommand=handlerCommand;
        this.fileWorker=fileWorker;
    }
    public void startRead() throws FileNotFoundException {
        file=new File(filleName);
        for( File n: st){
            if(file.equals(n)){
                flag =1;
                break;
            }
        }
        st.add(file);
        if(fileWorker.fileCheckAccessReader(file)){

            scanner=new Scanner(file);
            while (scanner.hasNextLine()){

                commands.add(scanner.nextLine());
            }
            if(flag ==1){
                System.out.println("Обнаружена рекурсия");
            }
            else {
                startTreatment();
            }

        }

    }
    private void startTreatment(){
        int addflag = 0;
        while (commands.size()>0){

            comannd=commands.get(0).toLowerCase().trim().split(" ");

            switch (comannd[0]){
                case "help":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    handlerCommand.commandsHelp();
                    break;
                case "exit":

                    break;
                case "show":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
//                    handlerCommand.commandShow(server1);
                    break;

                case "clear":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    handlerCommand.commandClear();

                    break;


                case "save":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    handlerCommand.commandsSave();

                    break;

                case "execute_script":
                    if(checkCommndLine(1,comannd)){
                        break;
                    }
                    if (filleName.equals(comannd[1])){
                        System.out.println("Обнаружена рекурсия");

                    }else {

                        handlerCommand.commandsExecuteScript(handlerCommand, comannd[1]);
                    }



                    break;
                case "info":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    handlerCommand.commandsInfo();
                    break;

                case "add":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }

                    ArrayList<String[]> comannds = new ArrayList<String[]>();
                    for(int i = 1; i<8; i++){

                            comannds.add(commands.get(i).toLowerCase().trim().split(" "));


                    }

                    handlerCommand.ExCommandAdd(comannds.get(0)[0],comannds.get(1)[0],comannds.get(2)[0],comannds.get(3)[0],comannds.get(4)[0],comannds.get(5)[0],comannds.get(6)[0]);
//
                    addflag = 1;
                    break;
                case "update":
                    if(checkCommndLine(1,comannd)){
                        break;
                    }
                    try{
                        Long.parseLong(comannd[1]);
                        long id = Long.parseLong(comannd[1]);
//                        handlerCommand.commandUpdate(id);
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
                        handlerCommand.commandRemove(id);
                    }catch (NumberFormatException e){
                        System.out.println("Введённые данные не корректны");
                    }
                    break;
                case "remove_first":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    handlerCommand.commandRemoveFirst();
                    break;
                case "remove_any_by_minimal_point":
                    if(checkCommndLine(1,comannd)){
                        break;
                    }
                    try{
                        Double.parseDouble(comannd[1]);
                        Double mp = Double.parseDouble(comannd[1]);
                        handlerCommand.commandRemoveByMinimalPoint(mp);
                    }catch (NumberFormatException e){
                        System.out.println("Введённые данные не корректны");
                    }
                    break;

                case "sum_of_minimal_point":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    handlerCommand.commandSumOfMinimalPoint();
                    break;
                case "add_if_max":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    ArrayList<String[]> comanndsMax = new ArrayList<String[]>();
                    for(int i = 1; i<8; i++){

                        comanndsMax.add(commands.get(i).toLowerCase().trim().split(" "));


                    }
                    handlerCommand.commandAddIfMaxEx(comanndsMax.get(0)[0],comanndsMax.get(1)[0],comanndsMax.get(2)[0],comanndsMax.get(3)[0],comanndsMax.get(4)[0],comanndsMax.get(5)[0],comanndsMax.get(6)[0]);
                    addflag =1;
                    break;


                case "min_by_creation_date":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    handlerCommand.commandCreationDate();
                    break;


                default:
                    System.out.println("команды <"+comannd[0]+"> не существует ");
                    break;



            }
            commands.remove(0);

            if(addflag == 1){
                commands.remove(0);
                commands.remove(0);
                commands.remove(0);
                commands.remove(0);
                commands.remove(0);
                commands.remove(0);
                commands.remove(0);
                addflag = 0;
            }
        }
    }
    /**
     * проверка на соответствие количества аргументов
     * @param arguments количество аргументов которое принимает команда
     * @param comanndLine масиив команд
     */
    private boolean checkCommndLine(int arguments,String[] comanndLine){
        if(arguments!=comanndLine.length-1){
            System.out.println("Комманда"+ comanndLine[0] +" принемает "+ arguments +" аргумента");
            return true;
        }else {
            return false;
        }

    }

}


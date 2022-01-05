package tools;

import collection.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ArrayDeque;
import java.util.Scanner;


public class Validation {
    private ArrayDeque<LabWork> collection;
    private LabWork labWork;
    private Discipline discipline;
    Scanner scanner = new Scanner(System.in);

    public Validation(ArrayDeque<LabWork>collection){
        this.collection = collection;
        discipline = new Discipline();
        labWork = new LabWork();
//        ZoneId zone = ZoneId.of("GMT+3");
//        ZonedDateTime time = ZonedDateTime.now(zone);
        Date time = new Date();
        labWork.setCreationDate(time);

    }

    public boolean checkName(String name){
        if(name.length()>0 && labWork.setName(name) && name.replace(" ","").length()>0){
            return true;
        }else{
            System.out.println("name не должно быть пустой строкой или быть равным null");
            return false;}
    }
    public boolean checkName(LabWork labWork){

        System.out.println("Введите название лабораторной работы");
        String name = scanner.nextLine();
        if(name.length()>0 && labWork.setName(name)&& name.replace(" ","").length()>0){
            return true;
        }else{
            System.out.println("name не должно быть пустой строкой или быть равным null");
            return false;
        }

    }


    public boolean createCoordinates(LabWork labWork){
        Coordinates coordinates = new Coordinates();
        System.out.println("Пожалуйста, введите этаж и номер аудитории через Enter. Номер аудитории не должен превышать значение 705.");
        try{
            int x=Integer.parseInt(scanner.nextLine());
            double y = Double.parseDouble(scanner.nextLine());
            if(coordinates.setX(x) && coordinates.setY(y)){
                labWork.setCoordinates(coordinates);
                return true;
            }
            else {return false;}
        }catch (NumberFormatException e){
            System.out.println("Неправильный формат значений, попробуйте ещё");
            return false;
        }
    }

    public boolean setDifficulty(LabWork labWork){
        System.out.println("Выберите из предложенных вариантов сложности:Normal, Hopeless, Terrible. Введите в командную строку");
        switch (scanner.nextLine().toLowerCase()){
            case "normal":
                labWork.setDifficulty(Difficulty.NORMAL);
                return true;

            case "hopeless":
                labWork.setDifficulty(Difficulty.HOPELESS);
                return true;

            case "terrible":
                labWork.setDifficulty(Difficulty.TERRIBLE);
                return true;

            default:
                System.out.println("Считанной сложности не существует. ");
                return false;

        }
    }
    public boolean setDifficulty(String difficulty){
        switch (difficulty.toLowerCase()){
            case "normal":
                labWork.setDifficulty(Difficulty.NORMAL);
                return true;

            case "hopeless":
                labWork.setDifficulty(Difficulty.HOPELESS);
                return true;

            case "terrible":
                labWork.setDifficulty(Difficulty.TERRIBLE);
                return true;

            default:
                System.out.println("Считанной сложности не существует. ");
                return false;

        }
    }

    public boolean checkDiscipline(LabWork labWork){
        Discipline discipline = new Discipline();
        System.out.println("Введите название дисциплины");
        byte checkPoint =0;
        while (checkPoint==0){
            String name = scanner.nextLine();
            if(name.replace(" ","").length()>0 && name !=null){
                discipline.setName(name);
                checkPoint=1;
            }
            else {
                System.out.println("Имя не было введено, попробуйте ещё");
            }
        }
        try{
            System.out.println("Введите количество часов самообучения");
            Long selfStudyHours = Long.parseLong(scanner.nextLine());
            discipline.setSelfStudyHours(selfStudyHours);
            labWork.setDiscipline(discipline);
            return true;
        }catch (NumberFormatException e){
            System.out.println("Вы ввели не число");
            return false;
        }


    }

    public boolean checkMinimalPoint(LabWork labWork){
        System.out.println("Введите минимальное значение баллов");
        try{
            double x = Double.parseDouble(scanner.nextLine());
            labWork.setMinimalPoint(x);
            if(x>0){
                labWork.setMinimalPoint(x);
                return true;
            }
            else {
                System.out.println("Значение должно быть больше нуля");
                return false;
            }
        }catch (NumberFormatException e){
            System.out.println("Вы ввели не число");
            return false;
        }

    }







}

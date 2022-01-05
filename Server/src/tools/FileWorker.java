package tools;

import collection.LabWork;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class FileWorker {
    private String fileName;
    public Gson g = new Gson();
    private ArrayDeque<LabWork> collection;
    Date datePreservation;
    Date dateDownloads;


    public FileWorker(String fileName, ArrayDeque<LabWork> collection){
        this.collection=collection;
        this.fileName=fileName;

    }
    Scanner scanner=new Scanner(System.in);



    /**
     * Считывает файл и записывает данные в коллекцию
     */
    public boolean filleReader() throws IOException {
        File file = new File(fileName);
        if (fileCheckAccessReader(file)) {
            InputStreamReader inputStreamReader;
            BufferedReader bufferedReader = null;
            inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(inputStreamReader);
            try {
                while (bufferedReader.ready()) {
                    String s = bufferedReader.readLine();
                    LabWork labWork = g.fromJson(s, LabWork.class);
                    collection.addLast(labWork);
                }
                bufferedReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            fixNameFileRead();
            filleReader();
        }
        dateDownloads=new Date();
        return true;
    }
//        File file=new File(fileName);
//        if(fileCheckAccessReader(file)) {
//
//            try {
//                Scanner scanner = new Scanner(file);
//                try{
//                    while (scanner.hasNextLine()) {
//                        LabWork labwork1 = g.fromJson(scanner.nextLine(), LabWork.class);;
//                        collection.addLast( labwork1);
//                    }
//                    scanner.close();
//                    return true;
//                }catch (NullPointerException e){
//
//                }
//            } catch (FileNotFoundException e) {
//                System.err.println("Файла по данному пути не существует" );
//
//            }
//        }else {fixNameFileRead();
//
//            if (fileName.equals("!exit")){
//                return false;
//
//            }else { filleReader();}
//        }
//        dateDownloads=new Date();
//        return true;
//    }
    /**
     * Записывает коллекцию в файл
     */
    public void filleWrite() {
        File check = new File(fileName);
        if (fileCheckAccessWrite(check)) {
            try {
                FileWriter writer = new FileWriter(check);
                for (LabWork element : collection) {

                    String colectJson = g.toJson(element) + "\n";
                    writer.write(colectJson);
                }
                writer.flush();
                writer.close();
            } catch (FileNotFoundException e) {
                System.err.println("К сожалению, файла по указанному адресу не существует.");
            } catch (IOException e) {
                System.out.println("Невозможно записать данные в файл");
            }
            System.out.println("Коллекция успешно сохранена");
            datePreservation = new Date();
        }else {
            fixNameFileWrite();
            filleWrite();
        }

//        for(LabWork T: collection){
//            String s = "";
//            System.out.println(T);
//            String jsonString = g.toJson(T);
//            System.out.println(jsonString);
//            System.out.println(s);
//        }
//        LabWork t = collection.getFirst();
//        System.out.println(t);
//        String jsonString = gson.toJson(t);
//        System.out.println(jsonString);
//        try (FileWriter writer = new FileWriter("data.json")){
//            String jsonString = gson.toJson(t);
//            System.out.println(jsonString);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        File check=new File(fileName);
//        if (fileCheckAccessWrite(check)) {
//            try {
//                String text = "";
//                FileOutputStream clear = new FileOutputStream(fileName);
//                clear.write(text.getBytes());
//                FileOutputStream fos = new FileOutputStream(fileName, true);
//                LabWork t = collection.getFirst();
//                try (FileWriter writer = new FileWriter("../data.json")){
//                    gson.toJson(t, writer);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
////                for( LabWork t: collection) {
////                    try (FileWriter writer = new FileWriter("../data.json")){
////                        gson.toJson(t, writer);
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                }
//
//            } catch (FileNotFoundException e) {
//                System.err.println("К сожалению, файла по указанному адресу не существует.");
//            } catch (IOException e) {
//                System.out.println("Невозможно записать данные в файл");
//            }
//            System.out.println("Коллекция успешно сохранена");
//            datePreservation = new Date();
//        }else {fixNameFileWrite();
//            if (fileName.equals("!exit")){
//
//            }else { filleWrite();}
//
//        }
    }
    /**
     * Проверяет файл на возможность чтения файла
     * @param file файл с которого считываются данные
     */
    public boolean fileCheckAccessReader(File file) {
        if (fileName.length()!=0) {
            if (file.exists()) {
                if (file.canRead()) {
                    return true;
                } else {
                    System.out.println("К сожалению у вас нет прав на чтение этого файла  ");
                    return false;
                }
            } else {
                System.out.println("Файла с указанным именем не существует");
                return false;
            }
        }else{
            System.out.println("Вы не ввели название файла. Пожалуйста введите его ");
            return false;
        }
    }
    /**
     * Просит ввести откоректированное название файла для чтения
     */
    private void  fixNameFileRead(){
        System.out.println("Пожалуйста, введите другое название файла.Для выхода из программы напишите !exit");
        Scanner scanner=new Scanner(System.in);
        fileName=scanner.nextLine();


    }
    /**
     * Просит ввести откоректированное название файла для запист
     */
    private void fixNameFileWrite(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Если хотите изменить файл для записи введите его название, если хотите закрыть режим сохранения введите !exit");
        fileName=scanner.nextLine();

    }
    /**
     * Проверяет файл на возможность записи
     * @param file файл с которого считываются данные
     */
    private boolean fileCheckAccessWrite(File file){
        if (file.exists()){
            if (file.canWrite()){
                return true;

            }else {
                System.out.println("К сожалению, у вас нет прав на запись в этот файл");
                return false;
            }

        }else {
            System.out.println("К сожалению файла с указанныи именем не существет ");
            return false;
        }

    }

}




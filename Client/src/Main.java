import ClOb.ClientObject;
import collection.LabWork;
import ClientPackage.*;
import tools.HandlerCommand;

import java.io.IOException;
import java.net.PortUnreachableException;
import java.util.Scanner;

public class Main {
//    private static final Gson Gson = new Gson();
    private static String filleName;
//    private static boolean flag = true;
    private boolean flag =true;


    public Main(){    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        if(args.length==0){
//            while (filleName==null){
//                filleName=" ";
//                while (filleName.trim().length()==0){
//                    System.out.println("К сожалению вы не ввели название файла или ввели только пробел.Пожалуйста, введите корректное  название файла ");
//                    Scanner scanner = new Scanner(System.in);
//                    filleName=scanner.nextLine();
//                }
//            }
//        }else {
//            filleName=args[0];
//        }
        String[] namefille = new String[]{"data.json"};
//        namefille[0] = filleName;
        Client client = new Client();
        ClientObject fileName = new ClientObject();



        fileName.setCommand(namefille);
//        LabWork lb1 = new LabWork();
//        fileName.setLabWork(lb1);
//        while(flag){
//            try{
//
//            }catch (NumberFormatException e){
//
//            }
//        }
        boolean flag = true;
        while(flag){
            try{
                client.writeHandle(fileName);
                client.readHandle();
                flag = false;

            }catch (PortUnreachableException e){
                System.out.println("Сервер временно недоступен, повторное подключение через 500 мСек ");
                Thread.sleep(500);
            }catch (NullPointerException e){
                System.out.println("Сервер временно недоступен, повторное подключение через 500 мСек ");
                Thread.sleep(500);
            }
        }

        System.out.println("Введите команду. Для получения списка всех возможных команд введите help");
        HandlerCommand handlerCommand = new HandlerCommand();
        flag = true;
        while(flag){
            try{
                flag = handlerCommand.HandlerCommandWrite(client);
                if(flag == false){
                    break;
                }
                ClientObject answer = client.readHandle();
                handlerCommand.HandlerCommandRead(answer, client);
            }catch (PortUnreachableException e){
                System.out.println("Сервер временно недоступен, повторное подключение через 500 мСек ");
                System.out.println("Ведите название файла");
                Thread.sleep(500);
            }catch (NullPointerException e){
                System.out.println("Сервер временно недоступен, повторное подключение через 500 мСек ");
                System.out.println("Ведите название файла");
                Thread.sleep(500);
            }
        }
//        ArrayDeque<LabWork> collection = new ArrayDeque<LabWork>();
//        FileWorker fileWorker = new FileWorker(filleName, collection);
//
//        HandlerCommand handlerCommand = new HandlerCommand(collection, fileWorker);
//        CommandRead commandRead = new CommandRead(handlerCommand);
//        if (fileWorker.filleReader()) {
//            commandRead.reader();
//        } else {
//            System.out.println("Программа завершена");
//        }




    }
}

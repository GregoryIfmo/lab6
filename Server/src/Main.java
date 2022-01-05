import ClOb.ClientObject;
import ServerPackage.Server;
import collection.LabWork;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayDeque;

import tools.CommandRead;
import tools.FileWorker;
import tools.HandlerCommand;

public class Main {
    private static final Gson Gson = new Gson();
    private static String filleName;

    public Main(){    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server1 = new Server();
        server1.setConnection();
        ClientObject obgFile= server1.readHandle();
        filleName = obgFile.getCommand()[0];
        System.out.println(filleName);
//        if(args.length==0){
//            while (filleName==null){
//                filleName=" ";
//                while (filleName.trim().length()==0){
////                    System.out.println("К сожалению вы не ввели название файла или ввели только пробел.Пожалуйста, введите корректное  название файла ");
////                    Scanner scanner = new Scanner(System.in);
////                    filleName=scanner.nextLine();
////                    System.out.println("Пожалуйста, введите корректное  название файла ");
////                    ClOb.ClientObject obgFile= server1.readHandle();
////                    filleName=obgFile.getCommand()[0];
//                }
//            }
//        }else {
//            filleName=args[0];
//        }

        ArrayDeque<LabWork> collection = new ArrayDeque<LabWork>();
        FileWorker fileWorker = new FileWorker(filleName, collection);

        HandlerCommand handlerCommand = new HandlerCommand(collection, fileWorker);
        CommandRead commandRead = new CommandRead(handlerCommand);
        if (fileWorker.filleReader()) {
            commandRead.reader(server1);
        } else {
            System.out.println("Программа завершена");
        }
    }
}

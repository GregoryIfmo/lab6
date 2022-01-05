package ServerPackage;

import java.io.*;
import java.net.*;
import ClOb.ClientObject;

public class Server {
    private InetSocketAddress address;//local address of server
    private DatagramSocket dSock;//local datagram socket which helps to send and receive data to datagramPacket
    private SocketAddress remoteAddr;//address of client for send reply
    private byte[] data;

    public void connection() throws IOException, ClassNotFoundException, InterruptedException {
        setConnection();
//        while (true){
//            readHandle();
//            Thread.sleep(500);
//            writeHandle();
//            Thread.sleep(1000);
//        }
    }
    public void writeHandle(ClientObject object) throws  IOException {
        ClientObject point = object;

        ByteArrayOutputStream baos = new ByteArrayOutputStream(5000);
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream (baos));

        oos.writeObject(point);
        oos.flush();
        oos.close();

        data = baos.toByteArray();


        DatagramPacket dp = new DatagramPacket(data, data.length,remoteAddr);
        dSock.send(dp);
        System.out.println("\nData was sent successful to: "+remoteAddr);
        System.out.println("Sent data length: "+ data.length);
    }

    public ClientObject readHandle() throws IOException, ClassNotFoundException {
        try{
            data = new byte[10000];
            DatagramPacket dp = new DatagramPacket(data, data.length);
            dSock.receive(dp);
            remoteAddr = dp.getSocketAddress();
            System.out.println("Received data from: " + remoteAddr);
            System.out.println("Received data length: "+ dp.getLength());

            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);

            ClientObject point = (ClientObject) ois.readObject();
            System.out.println("Received data: " + point);
            return point;
        }catch (ClassNotFoundException e){
            System.out.println("ClassNotFoundException e");
            ClientObject ex = new ClientObject();
            String[] ex1 = new String[]{"33"};
            ex.setCommand(ex1);
            return  ex;
        }

    }

    public void setConnection() {
        try{
            address = new InetSocketAddress( 5000);
            dSock = new DatagramSocket(address);
            System.out.println("ServerPackage.Server was started...\n");
        }catch (SocketException e){
            System.err.println("Here is an exception when you try to set a connection!");
        }

    }
}





package ClientPackage;

import ClOb.ClientObject;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.PortUnreachableException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Client {
    private final DatagramChannel channel;
    private final InetSocketAddress remoteAddr = new InetSocketAddress("localhost",5000); //field for address of client (when write to object)

    public Client() throws IOException {
        channel = DatagramChannel.open();
        channel.connect(remoteAddr);
    }

    public void connection() throws IOException, InterruptedException {
        while (true){
//            writeHandle();
//            Thread.sleep(500);
//            readHandle();
//            Thread.sleep(1000);
        }

    }
    public ClientObject readHandle() throws IOException {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(10000);
            channel.receive(buffer);

            ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array());
            ObjectInputStream ois = new ObjectInputStream(bais);
            ClientObject point = (ClientObject) ois.readObject();

//            System.out.println("\nReceived data from: " + channel.getRemoteAddress());
//            System.out.println("Received data length: " + buffer.flip().limit());
//            System.out.println("Received data: " + point);
            buffer.clear();
            return point;
        }catch (PortUnreachableException e){
            System.err.println("No server with such port: "+ remoteAddr.getPort()+
                    "\nPlease check the number of ports " +
                    "\nAnd restart 'Server' and then 'ClientPackage.Client'!");
            return null;
        }catch (ClassCastException e) {
            System.err.println("The received data has another class UID!" +
                    "\nPlease check class of received data!");
            return null;
        }catch (ClassNotFoundException e){
            System.err.println("Doesn't find the such class of received data!");
            return null;
        }

    }

    public void writeHandle(ClientObject obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        ClientObject point = obj;
        oos.writeObject(point);
        ByteBuffer buffer = ByteBuffer.wrap(baos.toByteArray());

        channel.send(buffer,remoteAddr);
        System.out.println("Address of server: "+remoteAddr);
        System.out.println("Data was sent successful");
        System.out.println("Data length: "+buffer.array().length);
    }
}


package BasicClientServerConnections;

import java.util.*;
import java.io.*;
import java.net.*;

public class NewClient extends Thread
{
    public static ObjectInputStream ois;
    public static ObjectOutputStream oos;
    public static boolean available;
    public void run()
    {
        try{
            System.out.println("Client Thread " + Thread.currentThread().getId() + ": Waiting for connection");
            Server.connect = false;
            Socket client = Server.socket.accept();
            Server.connect = true;
            oos = new ObjectOutputStream(client.getOutputStream());
                    
            oos.writeObject("Server: Connection Successful");
                    
            System.out.println("Client Thread " + Thread.currentThread().getId() + ": Client Connected");
            ois = new ObjectInputStream(client.getInputStream());
            
            NewClientInput NCI = new NewClientInput();
            NCI.run();
            NewClientOutput NCO = new NewClientOutput();
            NCO.run();
            
            available =true;
            while(Server.available)
            {
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex)
                {
                    System.out.println("New Client Interrupted");
                    Thread.currentThread().interrupt();
                }
            }
            available = false;
            
            ois.close();
            oos.close();
            client.close();
            System.out.println("Client Thread " + Thread.currentThread().getId() + ": Ending Thread.");
        }
        catch (IOException ioe){
            System.out.println("Client Thread " + Thread.currentThread().getId() + ": Client Error Occured");
            System.out.println(ioe);

        }
    }
}
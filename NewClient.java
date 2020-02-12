package BasicClientServerConnections;

import java.util.*;
import java.io.*;
import java.net.*;

public class NewClient extends Thread
{
    public void run()
    {
        try{
            System.out.println("Client Thread: Waiting for connection");
            Server.connect = false;
            Socket client = Server.socket.accept();
            Server.connect = true;
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                    
            oos.writeObject("Connection Successful");
                    
            System.out.println("Client Thread: Client Connected");
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            
            while(!client.isClosed())
            {
                String message = null;
                try{message = (String) ois.readObject();}
                catch(Exception ioe){}
                if(message != null)
                {
                    System.out.println(message);
                }
                
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex)
                {
                    System.out.println("Thread Error");
                    Thread.currentThread().interrupt();
                }
                System.out.println(client.isClosed());
            }
            ois.close();
            oos.close();
            client.close();
            System.out.println("Client Thread: Client Disconnected");
        }
        catch (IOException ioe){
            System.out.println("Client Thread: Client Error Occured");
            System.out.println(ioe);

        }
    }
}
package BasicClientServerConnections;

import java.util.*;
import java.io.*;
import java.net.*;

public class NewClient extends Thread
{
    public void run()
    {
        try{
            System.out.println("Client Thread " + Thread.currentThread().getId() + ": Waiting for connection");
            Server.connect = false;
            Socket client = Server.socket.accept();
            Server.connect = true;
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                    
            oos.writeObject("Connection Successful");
                    
            System.out.println("Client Thread " + Thread.currentThread().getId() + ": Client Connected");
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            
            //NewClientInput NCI = new NewClientInput();
            //NCI.run(client);
            
            boolean disconnected = true;
            while(!client.isClosed() && disconnected)
            {
                String message = null;
                try{message = (String) ois.readObject();}
                catch(Exception ioe){
                    System.out.println("Client Thread " + Thread.currentThread().getId() + ": Client Disconnected");
                    ois.close();
                    disconnected=false;
                }
                if(message != null)
                {
                    System.out.println(message);
                }
                else
                {
                    disconnected=true;
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
            }
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
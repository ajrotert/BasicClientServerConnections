package BasicClientServerConnections;

import java.util.*;
import java.io.*;
import java.net.*;

public class ClientInput extends Thread
{
    public void run()
    {
        ObjectInputStream ois = Client.ois;
        try{
        boolean disconnected = true;
            while(disconnected)
            {
                String message = null;
                try{message = (String) ois.readObject();}
                catch(Exception ioe){
                    System.out.println("Server Input Thread " + Thread.currentThread().getId() + ": Server Disconnected");
                    ois.close();
                    disconnected=false;
                }
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
            }
        }
        catch (IOException ioe){
            System.out.println("Server Input Thread " + Thread.currentThread().getId() + ": Server Error Occured");
            System.out.println(ioe);

        }
    }
}

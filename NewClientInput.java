package BasicClientServerConnections;

import java.util.*;
import java.io.*;
import java.net.*;

public class NewClientInput extends Thread
{
    public void run(){
        ObjectInputStream ois = NewClient.ois;
        
        try{
        boolean disconnected = true;
            while(disconnected)
            {
                String message = null;
                try{message = (String) ois.readObject();}
                catch(Exception ioe){
                    System.out.println("Client Input Thread " + Thread.currentThread().getId() + ": Client Disconnected");
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
            System.out.println("Client Input Thread " + Thread.currentThread().getId() + ": Client Error Occured");
            System.out.println(ioe);

        }
    }
}

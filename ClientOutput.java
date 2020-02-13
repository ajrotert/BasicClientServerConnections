package BasicClientServerConnections;

import java.util.*;
import java.io.*;
import java.net.*;

public class ClientOutput extends Thread
{
    ObjectOutputStream oos = Client.oos;
    public void run(){
        try{
        while(Client.available)
            {
                if(Client.send!=null)
                {
                    oos.flush();
                    oos.writeObject(Client.send);
                    Client.send = null;
                }
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex)
                {
                    System.out.println("Server Interrupted");
                    Thread.currentThread().interrupt();
                }
            }
        }
        catch(Exception e){
          System.err.println(e);
        }
    }
}

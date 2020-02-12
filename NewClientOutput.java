package BasicClientServerConnections;

import java.util.*;
import java.io.*;
import java.net.*;

public class NewClientOutput extends Thread
{
    public void run(){
        ObjectOutputStream oos = NewClient.oos;
        int local_send = Server.send_number;
        try{
        while(NewClient.available)
            {
                if(Server.send!=null && Server.send_number> local_send)
                {
                    oos.flush();
                    oos.writeObject(Server.send);
                    local_send++;
                }
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex)
                {
                    System.out.println("Client Output Interrupted");
                    Thread.currentThread().interrupt();
                }
            }
        }
        catch(Exception e){}
    }

}

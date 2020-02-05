import java.util.*;
import java.io.*;
import java.net.*;

public class NewClient extends Thread
{
    public void run()
    {
        try{
        Server.connect = false;
        Socket client = Server.socket.accept();
        Server.connect = true;
        PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
                
        pout.println("Connection Successful");
                
        System.out.println("Client Connected");
        client.close();
        }
        catch (IOException ioe){
            System.out.println("Client Error Occured");
            System.out.println(ioe);

        }
    }
}
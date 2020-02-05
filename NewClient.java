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
            PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
                    
            pout.println("Connection Successful");
                    
            System.out.println("Client Thread: Client Connected");
            client.close();
            System.out.println("Client Thread: Client Disconnected");
        }
        catch (IOException ioe){
            System.out.println("Client Thread: Client Error Occured");
            System.out.println(ioe);

        }
    }
}
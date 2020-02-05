import java.net.*;
import java.io.*;

public class Server
{
    public static boolean available;
    public static boolean connect;
    public static ServerSocket socket;
    public static void main(String[] args){
        available = true;
        connect = true;
        boolean next = true;
        //Server controls allow you to stop new connections
        ServerControls controls = new ServerControls();
        NewClient cl = null;
        
        controls.start(); 
        
        try{
            socket = new ServerSocket(6013);
        }
        catch (IOException ioe){
            System.out.println("Client Error Occured");
            System.out.println(ioe);
            next = false;
        }
        if(next)
        {
            while(available)
            {
                //New client can be connected if old one client is waiting
                if(connect){
                    System.out.println("New Client Tread");
                    cl = new NewClient();
                    cl.start();
                }
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                
            }
            cl.stop();
        }
        controls.stop();
        try{
            socket.close();
        }
        catch (IOException ioe){
            System.out.println("Could not close socket");
            System.out.println(ioe);
        }

        System.out.println("Server Stopped");
    }
}

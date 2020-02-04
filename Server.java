import java.net.*;
import java.io.*;

public class Server
{
    public static boolean available;
    public static void main(String[] args){
        available = true;
        ServerControls controls = new ServerControls();
        controls.start(); 
        try{
            ServerSocket socket = new ServerSocket(6013);

            while(available){
                Socket client = socket.accept();
                System.out.println("Waiting for connection");
                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
                
                pout.println("Connection Successful");
                
                System.out.println("Client Connected");
                client.close();
            }
        }
        catch(IOException ioe)
        {
            System.err.println(ioe);
        }
        
        System.out.println("Server Stopped");
    }
}

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
            
            String message ="No Message Recieved";
            try{message = (String) ois.readObject();}
            catch(Exception ioe){}
            System.out.println(message);
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
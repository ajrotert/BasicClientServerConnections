package BasicClientServerConnections;

import java.net.*;
import java.io.*;
import java.util.*;

public class Client
{
    public static String IPADDRESS;
    public static int PORT;
    public static boolean available; 
    public static String send;
    public static InetAddress inetAddress;
    public static ObjectInputStream ois;
    public static ObjectOutputStream oos;
    
    public static void main(String[] args) throws Exception
    {
        PORT = 6013;
        available = true;
        send = null;
        Scanner input = new Scanner(System.in);
        try{
            inetAddress = InetAddress.getLocalHost();
            System.out.println("IP: " + inetAddress.getHostAddress());
            System.out.println("Public IP: " + getNetworkAddress());
            System.out.println("Port: " + PORT);
        }
        catch(Exception e){
            System.out.println("Could not find IP address");
        }
        System.out.print("Enter an IP Address (127.0.0.1): ");
        IPADDRESS = input.nextLine();
        //ObjectInputStream ois;
        //ObjectOutputStream oos;
        ClientControls control = new ClientControls();
        control.start();
        try{
            Socket socket = new Socket(IPADDRESS, PORT);

            ois = new ObjectInputStream(socket.getInputStream());
            String Recieved = (String) ois.readObject();
            System.out.println(Recieved);
            
            oos = new ObjectOutputStream(socket.getOutputStream());

            oos.writeObject("Client Connected: " + getNetworkAddress());
            
            ClientInput CI = new ClientInput();
            CI.start();
            ClientOutput CO = new ClientOutput();
            CO.start();
            
            while(available)
            {
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
            
            oos.close();
            ois.close();
            socket.close();
        }
        catch (IOException ioe){
            System.err.println(ioe);
        }
        System.out.println("Ended Session");
    }
    public static String getNetworkAddress(){
        String network_IP = ""; 
        try
        { 
            URL url_name = new URL("http://bot.whatismyipaddress.com"); 
  
            BufferedReader sc = 
            new BufferedReader(new InputStreamReader(url_name.openStream())); 
  
            // reads system IPAddress 
            network_IP = sc.readLine().trim(); 
        } 
        catch (Exception e) 
        { 
            System.out.println("Cannot find network IP address");
        } 
        return network_IP;
    }
}
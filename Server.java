package BasicClientServerConnections;

import java.net.*;
import java.io.*;

public class Server
{
    private static final String BuildNumber = "1.0.1";
    public static boolean available;
    public static boolean connect;
    public static InetAddress inetAddress;
    public static ServerSocket socket;
    public static final int PORT = 6013;
    public static void main(String[] args){
        System.out.println("Version: " + BuildNumber);
        available = true;
        connect = true;
        boolean next = true;
        //Server controls allow you to stop new connections
        ServerControls controls = new ServerControls();
        inetAddress = null;
        
        try{
            inetAddress = InetAddress.getLocalHost();
            System.out.println("IP: " + inetAddress.getHostAddress());
            System.out.println("Public IP: " + getNetworkAddress());
            System.out.println("Port: " + PORT);
        }
        catch(Exception e){
            System.out.println("Could not find IP address");
        }
        
        NewClient cl = null;
        
        controls.start(); 
        
        
        try{
            socket = new ServerSocket(PORT);
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
                    System.out.println("Server Interrupted");
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

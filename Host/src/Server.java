import java.net.*;
import java.io.*;

public class Server
{
    private final String BuildNumber = "1.0.1";
    public static boolean available;
    public static boolean connect;
    public static ServerSocket socket;
    public static final int PORT = 6013;
    public static String send;
    public static int send_number;
    public static Main main_ref;
    
    public InetAddress inetAddress;
    boolean next = true;

    public Server(Main reference)
    {
    	main_ref = reference;
    	System.out.println("Version: " + BuildNumber);
    	main_ref.UpdateText("Version: " + BuildNumber + "\n");
        available = true;
        connect = true;
        send = null;
        send_number=0;
        inetAddress = null;

        try{
            inetAddress = InetAddress.getLocalHost();
            String IP = "IP: " + inetAddress.getHostAddress();
            String IPP = "Public IP: " + getNetworkAddress();
            String POR = "Port: " + PORT;
            System.out.println(IP);
            System.out.println(IPP);
            System.out.println(POR);
            
            main_ref.UpdateText(IP + "\n" + IPP + "\n" + POR + "\n");
        }
        catch(Exception e){
            System.out.println("Could not find IP address");
        }
        
    }
    public void setup(){
    	long start = System.currentTimeMillis();

        NewClient cl = null;
        try{
            socket = new ServerSocket(PORT);
        	System.out.println("Socket Started");
        	main_ref.UpdateText("Socket Started" +"\n");
        }
        catch (IOException ioe){
            System.out.println("Server Error Occured");
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
                    main_ref.UpdateText("New Client Tread\n");
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
                    main_ref.UpdateText("Server Interrupted\n");
                    Thread.currentThread().interrupt();
                }
                
            }
            cl.interrupt();
        }
        
        try{
            socket.close();
        }
        catch (IOException ioe){
            System.out.println("Could not close socket");
            main_ref.UpdateText("Could not close socket\n");
            System.out.println(ioe);
        }

        long end = System.currentTimeMillis();
        System.out.println("Elapsed Time: " + ((end - start) / 1000));
        Server.main_ref.UpdateText("Server Thread(" + Thread.currentThread().getId() + ") Elapsed Time: " + ((end - start) / 1000) + "s\n");
        
        System.out.println("Server Stopped");
        main_ref.UpdateText("Server Stopped\n");
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
        	
        	try{
        		URL url_name = new URL("http://checkip.amazonaws.com"); 
        		  
                BufferedReader sc = 
                new BufferedReader(new InputStreamReader(url_name.openStream())); 
      
                // reads system IPAddress 
                network_IP = sc.readLine().trim(); 
        	}
        	catch(Exception ee)
        	{
        		System.out.println("Cannot find network IP address");
                main_ref.UpdateText("Cannot find network IP address\n");
        	}
            
        } 
        return network_IP;
    }
}

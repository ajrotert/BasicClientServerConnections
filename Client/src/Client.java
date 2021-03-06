import java.net.*;
import java.io.*;

public class Client
{
    public static String IPADDRESS;
    public static int PORT;
    public static boolean available; 
    public static String send;
    public static InetAddress inetAddress;
    public static ObjectInputStream ois;
    public static ObjectOutputStream oos;
    public static Main main_ref;
    
    public Client(Main reference)
    {
    	main_ref = reference;
    	System.out.println("Created");
    	PORT = 6013;
        available = true;
        send = null;

        String out = "";
        try{
            inetAddress = InetAddress.getLocalHost();
            String IP = inetAddress.getHostAddress();
            String NA = getNetworkAddress();
            System.out.println("(Client) IP: " + IP);
            System.out.println("(Client) Public IP: " + NA);
            System.out.println("(Client) Port: " + PORT);
            out+= "IP: " + IP + "\n";
            out+= "Public IP: " + NA + "\n";
            out+= "Port: " + PORT + "\n";
            main_ref.UpdateText(out);
        }
        catch(Exception e){
            System.out.println("(Client) Could not find IP address");
            out+= "Could not find IP address\n";
            main_ref.UpdateText(out);
        }
    }
    public void setup()
    {
        long start = System.currentTimeMillis();

    	String out = "";
        
        System.out.println("(Client) Enter an IP Address (127.0.0.1): ");
        out+="Enter an IP Address (127.0.0.1):\n";
        main_ref.UpdateText(out);
                
        while(!Main.connected){
        //Wait for an IP Address to be connected
        	try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                System.out.println("Client) Client Output Interrupted");
                out+= "Client Output Interrupted\n";
                main_ref.UpdateText(out);
                Thread.currentThread().interrupt();
                Main.connected = true;
            }
        	IPADDRESS = Main.IP_CONNECT;
        }
        
        try{
            Socket socket = new Socket(IPADDRESS, PORT);

            ois = new ObjectInputStream(socket.getInputStream());
            String Recieved = "";
            
            //Initial connection message sent from the host
            try{
            Recieved = (String) ois.readObject();
            }
            catch(Exception e){
            	Recieved = e.toString();
            }
            
            System.out.println("(Client) " + Recieved);
            
            out = Recieved + "\n";
            main_ref.UpdateText(out);
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
                	System.out.println("Client) Client Output Interrupted (2)");
                    out = "Client Output Interrupted (2)\n";
                    main_ref.UpdateText(out);
                    Thread.currentThread().interrupt();
                    available = false;
                }
            }
            
            oos.close();
            ois.close();
            socket.close();
            CI.interrupt();
            CO.interrupt();
        }
        catch (IOException ioe){
            main_ref.UpdateText("Connection Failed\n");
            main_ref.UpdateInput();
        }
        
   	   	long end = System.currentTimeMillis();
   	   	System.out.println("Client Thread Elapsed Time: " + ((end - start) / 1000));
        main_ref.UpdateText("Client Thread Elapsed Time: " + ((end - start) / 1000) + "s\n");

        System.out.println("(Client) Ended Session");
        out ="Client Session Ended\n";
        main_ref.UpdateText(out);
    }
        
        
    public static String getNetworkAddress(){
        String network_IP = ""; 

	        try
	        { 
	            URL url_name = new URL("http://bot.whatismyipaddress.com"); 
	  
	            BufferedReader sc = 
	            new BufferedReader(new InputStreamReader(url_name.openStream())); 
	  
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
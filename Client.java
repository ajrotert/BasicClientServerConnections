import java.net.*;
import java.io.*;
import java.util.*;

public class Client
{
    public static String IPADDRESS;
    public static int PORT;
    public static void main(String[] args) throws Exception
    {
        PORT = 6013;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an IP Address (127.0.0.1): ");
        IPADDRESS = input.nextLine();
        try{
            Socket socket = new Socket(IPADDRESS, PORT);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String Recieved = (String) ois.readObject();
            System.out.println(Recieved);
            
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            oos.writeObject("Client Connected: " + getNetworkAddress());

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

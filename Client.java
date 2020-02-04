import java.net.*;
import java.io.*;
import java.util.*;

public class Client
{
    public static String IPADDRESS;
    public static int PORT;
    public static void main(String[] args)
    {
        PORT = 6013;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an IP Address (127.0.0.1): ");
        IPADDRESS = input.nextLine();
        try{
            Socket socket = new Socket(IPADDRESS, PORT);
            InputStream in = socket.getInputStream();
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));
            
            String line;
            while((line = bin.readLine()) != null)
            {
                System.out.println(line);
            }
            socket.close();
        }
        catch (IOException ioe){
            System.err.println(ioe);
        }
        System.out.println("Ended Session");
    }
}

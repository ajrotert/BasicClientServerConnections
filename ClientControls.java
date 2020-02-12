package BasicClientServerConnections;
import java.net.*;
import java.io.*;
import java.util.*;

public class ClientControls extends Thread
{
    private static final String CLIST = "Commands:\n\t(E) Exit Session\n\t(T) Thread ID\n\t(C) Command List\n\t(P) Print IP Address\n\t(S) Send IP Address\n\t(M) Send a message\n\t";
    public void run(){
        Scanner input = new Scanner(System.in);
        
        System.out.println(CLIST);

        try
        {
            String usr = input.nextLine();
            while(!(usr.equals("E") || usr.equals("e")))
            {
                if(usr.equals("T") || usr.equals("t"))
                    System.out.println("Thread ID: " + Thread.currentThread().getId());
                if(usr.equals("P") || usr.equals("p"))
                {
                    System.out.println("IP: " + Client.inetAddress.getHostAddress());
                    System.out.println("Public IP: " + Client.getNetworkAddress());
                }
                if(usr.equals("S") || usr.equals("s"))
                    Client.send = "Client: " + Client.getNetworkAddress();
                if(usr.equals("C") || usr.equals("c"))
                        System.out.println(CLIST);
                if(usr.equals("M") || usr.equals("m"))
                {
                    System.out.print("Enter a message: ");
                    Client.send = "Client: " + input.nextLine();
                }
                usr = input.nextLine();
            }
            Client.available = false;
        }
        catch(Exception e)
        {
        }
    }
}

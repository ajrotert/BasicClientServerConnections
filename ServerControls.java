package BasicClientServerConnections;

import java.util.*;
import java.net.*;
import java.net.InetAddress;

public class ServerControls extends Thread
{
    private static final String CLIST = "Commands:\n\t(S) Stop server\n\t(T) Thread ID\n\t(C) Command List\n\t(P) Port Number\n\t(I) IP Address\n\t(M) Send message to all clients.\n\t";
    public void run() 
    {
        Scanner input = new Scanner(System.in);
        
        System.out.println(CLIST);

        try
        {
            String usr = input.nextLine();
            while(!(usr.equals("S") || usr.equals("s")))
            {
                if(usr.equals("T") || usr.equals("t"))
                    System.out.println("Thread ID: " + Thread.currentThread().getId());
                if(usr.equals("I") || usr.equals("i"))
                {
                    System.out.println("IP: " + Server.inetAddress.getHostAddress());
                    System.out.println("Public IP: " + Server.getNetworkAddress());
                }
                if(usr.equals("P") || usr.equals("p"))
                    System.out.println("Port: " + Server.PORT);
                if(usr.equals("C") || usr.equals("c"))
                        System.out.println(CLIST);
                if(usr.equals("m") || usr.equals("M"))
                {
                    System.out.println("Server Controls: Enter a message to send: ");
                        Server.send = "Server: "+ input.nextLine();
                        Server.send_number++;
                }
                usr = input.nextLine();
            }
            Server.available = false;
        }
        catch(Exception e)
        {
        }
    }
}
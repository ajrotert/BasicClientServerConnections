import java.util.*;
public class ServerControls extends Thread
{
    public void run()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("ID: " + Thread.currentThread().getId());
        System.out.println("Press S to stop server (Press Enter Twice): ");
        try
        {
            while(input.nextLine().equals("S") || input.nextLine().equals("s"))
            {
                System.out.println("Press S to stop server (Press Enter Twice): ");
            }
            Server.available = false;
        }
        catch(Exception e)
        {
        }
        System.out.println("Server Stopping");
    }
}
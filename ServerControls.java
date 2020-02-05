import java.util.*;

public class ServerControls extends Thread
{
    public void run()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("ID: " + Thread.currentThread().getId());
        System.out.println("Press S to stop server: ");
        try
        {
            String usr = input.nextLine();
            while(!(usr.equals("S") || usr.equals("s")))
            {
                System.out.println("Press S to stop server: ");
                usr = input.nextLine();
            }
            Server.available = false;
        }
        catch(Exception e)
        {
        }
    }
}
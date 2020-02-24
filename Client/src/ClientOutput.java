import java.io.*;

public class ClientOutput extends Thread
{
    ObjectOutputStream oos = Client.oos;
    public void run(){
        try{
        while(Client.available)
            {
                if(Client.send!=null)
                {
                    oos.flush();
                    oos.writeObject(Client.send);
                    Client.send = null;
                }
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex)
                {
                    System.out.println("(Client Output) Server Interrupted");
                    Client.main_ref.UpdateText("(Client Output) Server Interrupted\n");
                    Thread.currentThread().interrupt();
                }
            }
        }
        catch(Exception e){
          System.err.println(e);
        }
    }
}

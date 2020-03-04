import java.io.*;

public class ClientOutput extends Thread
{
    ObjectOutputStream oos = Client.oos;
    public void run(){
        try{
     	   long start = System.currentTimeMillis();

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
  	   long end = System.currentTimeMillis();
 	   System.out.println("Elapsed Time: " + ((end - start) / 1000));
   	   Client.main_ref.UpdateText("Client Output Thread Elapsed Time: " + ((end - start) / 1000) + "s\n");

        }
        catch(Exception e){
          System.err.println(e);
        }
    }
}

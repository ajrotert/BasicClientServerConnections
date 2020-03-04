import java.io.*;

public class ClientInput extends Thread
{
    public void run()
    {
        ObjectInputStream ois = Client.ois;
        try{
        	
        	long start = System.currentTimeMillis();

        	boolean disconnected = true;
            while(disconnected)
            {
                String message = null;
                try{message = (String) ois.readObject();}
                catch(Exception ioe){
                    System.out.println("(Client Input) Server Input Thread " + Thread.currentThread().getId() + ": Server Disconnected");
                    Client.main_ref.UpdateText("Client Input Thread " + Thread.currentThread().getId() + ": Disconnected from Server" + "\n");
                    ois.close();
                    disconnected=false;
                }
                if(message != null)
                {
                    System.out.println("(Client Input)" +message);
                    Client.main_ref.UpdateText(message + "\n");
                }
                
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex)
                {
                    System.out.println("(Client Input) Thread Error");
                    Thread.currentThread().interrupt();
                }
            }
       	   long end = System.currentTimeMillis();
       	   System.out.println("Client Input Thread Elapsed Time: " + ((end - start) / 1000));
       	   Client.main_ref.UpdateText("Client Input Thread Elapsed Time: " + ((end - start) / 1000) + "s\n");
        }
        catch (IOException ioe){
            System.out.println("(Client Input) Server Input Thread " + Thread.currentThread().getId() + ": Server Error Occured");
            Client.main_ref.UpdateText("(Client Input) Server Input Thread " + Thread.currentThread().getId() + ": Server Error Occured\n");
            System.out.println(ioe);

        }
    }
}

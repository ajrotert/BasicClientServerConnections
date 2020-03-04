import java.io.*;

public class NewClientInput extends Thread
{
    public void run(){
        ObjectInputStream ois = NewClient.ois;
        long start = System.currentTimeMillis();
		    
        try{

        boolean disconnected = true;
            while(disconnected)
            {
                String message = null;
                try{message = (String) ois.readObject();}
                catch(Exception ioe){
                    System.out.println("Client Input Thread " + Thread.currentThread().getId() + ": Client Disconnected");
                    Server.main_ref.UpdateText("Client Input Thread " + Thread.currentThread().getId() + ": Client Disconnected\n");
                    ois.close();
                    disconnected=false;
                }
                if(message != null)
                {
                    System.out.println(message);
                    Server.main_ref.UpdateText(message+"\n");
                }
                
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex)
                {
                    System.out.println("Thread Error");
                    Server.main_ref.UpdateText("Thread Error\n");
                    Thread.currentThread().interrupt();
                }
            }
        }
        catch (IOException ioe){
            System.out.println("Client Input Thread " + Thread.currentThread().getId() + ": Client Error Occured");
            Server.main_ref.UpdateText("Client Input Thread " + Thread.currentThread().getId() + ": Client Error Occured\n");
            System.out.println(ioe);

        }
	    long end = System.currentTimeMillis();
	    System.out.println("New Client input thread Elapsed Time: " + ((end - start) / 1000));
	    Server.main_ref.UpdateText("New Client Input Thread(" + Thread.currentThread().getId() + ") Elapsed Time: " + ((end - start) / 1000) + "s\n");
    }
}

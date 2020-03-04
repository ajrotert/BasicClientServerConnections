import java.io.*;

public class NewClientOutput extends Thread
{
    public void run(){
    	long start = System.currentTimeMillis();

        ObjectOutputStream oos = NewClient.oos;
        int local_send = Server.send_number+1;
        try{

        while(NewClient.available)
            {
                if(Server.send!=null && Server.send_number>= local_send)
                {
                    System.out.println("New Client Output " + Thread.currentThread().getId() + ": Message Recieved");
                    Server.main_ref.UpdateText("New Client Output " + Thread.currentThread().getId() + ": Message Recieved\n");
                    oos.writeObject(Server.send);
                    oos.flush();
                    local_send++;
                }
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex)
                {
                    System.out.println("Client Output Interrupted");
                    Server.main_ref.UpdateText("Client Output Interrupted\n");
                    Thread.currentThread().interrupt();
                }
            }
        }
        catch(Exception e){
        System.out.println("ERROR");}
        
	    long end = System.currentTimeMillis(); 
	    System.out.println("Elapsed Time: " + ((end - start) / 1000));
	    Server.main_ref.UpdateText("New Client Output(" + Thread.currentThread().getId() + ") Thread Elapsed Time: " + ((end - start) / 1000) + "s\n");
    }

}

import java.io.*;

public class NewClientOutput extends Thread
{
    public void run(){
        ObjectOutputStream oos = NewClient.oos;
        int local_send = Server.send_number+1;
        try{

        while(NewClient.available)
            {
                if(Server.send!=null && Server.send_number>= local_send)
                {
                    System.out.println("New Client Output: Message Recieved");
                    Server.main_ref.UpdateText("New Client Output: Message Recieved\n");
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
    }

}

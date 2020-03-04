import java.io.*;
import java.net.*;

public class NewClient extends Thread{
	public static ObjectInputStream ois;
    public static ObjectOutputStream oos;
    public static boolean available;
    public void run()
    {
    	long start = System.currentTimeMillis();

        try{
            System.out.println("Client Thread " + Thread.currentThread().getId() + ": Waiting for connection");
            Server.main_ref.UpdateText("Client Thread " + Thread.currentThread().getId() + ": Waiting for connection\n");
            Server.connect = false;
            Socket client = Server.socket.accept();
            Server.connect = true;
            oos = new ObjectOutputStream(client.getOutputStream());
                    
            oos.writeObject("Host: Connection Successful");
                    
            System.out.println("Client Thread " + Thread.currentThread().getId() + ": Client Connected");
            Server.main_ref.UpdateText("Client Thread " + Thread.currentThread().getId() + ": Client Connected\n");
            ois = new ObjectInputStream(client.getInputStream());
            
            System.out.println("Client Thread " + Thread.currentThread().getId() + ": Input Started");
            Server.main_ref.UpdateText("Client Thread " + Thread.currentThread().getId() + ": Input Started\n");
            NewClientInput NCI = new NewClientInput();
            NCI.start();
            System.out.println("Client Thread " + Thread.currentThread().getId() + ": Output Started");
            Server.main_ref.UpdateText("Client Thread " + Thread.currentThread().getId() + ": Output Started\n");
            NewClientOutput NCO = new NewClientOutput();
            NCO.start();
            
            available =true;
            while(Server.available && !client.isClosed())
            {
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex)
                {
                    System.out.println("New Client Interrupted");
                    Server.main_ref.UpdateText("New Client Interrupted\n");
                    Thread.currentThread().interrupt();
                }
            }
            available = false;
            
            ois.close();
            oos.close();
            client.close();
            System.out.println("Client Thread " + Thread.currentThread().getId() + ": Ending Thread.");
            Server.main_ref.UpdateText("Client Thread " + Thread.currentThread().getId() + ": Ending Thread.\n");
        }
        catch (IOException ioe){
            System.out.println("Client Thread " + Thread.currentThread().getId() + ": Client Error Occured");
            Server.main_ref.UpdateText("Client Thread " + Thread.currentThread().getId() + ": Client Error Occured\n");
            System.out.println(ioe);

        }
	    long end = System.currentTimeMillis();
	    System.out.println("New Client Elapsed Time: " + ((end - start) / 1000));
	    Server.main_ref.UpdateText("New Client Thread(" + Thread.currentThread().getId() + ") Elapsed Time: " + ((end - start) / 1000) + "s\n");
    }

}

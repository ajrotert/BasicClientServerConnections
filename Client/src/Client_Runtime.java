
public class Client_Runtime extends Thread {

	public void run(){
		System.out.println("(Client_Runtime) Runtime Started");
		
		Main.client.setup();
		
		//Keeps Thread Alive
		//Probably Better Methods
		boolean run = true;
		
	   long start = System.currentTimeMillis();
		
		while(run)
		{
			try
	        {
	            Thread.sleep(1000);
	        }
	        catch(Exception ignore){run = false;}
		}
		
	    long end = System.currentTimeMillis();
		
	    System.out.println("Elapsed Time: " + ((end - start) / 1000));
	    
		System.out.println("(Client_Runtime) Runtime Ended");
	}
}

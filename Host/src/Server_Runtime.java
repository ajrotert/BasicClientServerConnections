
public class Server_Runtime extends Thread {

	public void run(){
		System.out.println("(Server_Runtime) Runtime Started");
		long start = System.currentTimeMillis();

		Main.server.setup();
		
		//Keeps Thread Alive
		//Probably Better Methods
		boolean run = true;
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
		
		System.out.println("(Server_Runtime) Runtime Ended");
	}
}

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 
public class Main extends Application {
 
    Stage window;
    Button button;
    Label InputLabel;
    Label OutputLabel;
    TextField inputs;
    static Client client;
    public static String IP_CONNECT;
    public static Boolean connected;
    private static Client_Runtime CR;
    
    private static final String CLIST = "Commands:\n\t(E) Exit Session\n\t(T) Thread ID\n\t(C) Clear Output\n\t(P) Print IP Address\n\t(S) Send IP Address to Host\n\t(...) Send a message to the host\n\t";
 
    public static void main(String[] args) {
    	connected = false;
    	IP_CONNECT = "";

        CR = new Client_Runtime();

        launch(args); // It calls start method defined bellow...
        System.out.println("END OF PROGRAM");
        CR.interrupt();
    }
    public void UpdateText(String text)
    {
    	Platform.runLater( () -> {
    	OutputLabel.setText(OutputLabel.getText() + text);
    	});
    }
    public void UpdateInput()
    {
    	Platform.runLater( () -> {
    	InputLabel.setText("");
    	});
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Client Computer");
                
        InputLabel = new Label(CLIST);
        OutputLabel = new Label();
        button = new Button("Send");
        inputs = new TextField();
        button.setOnAction(e->{
        String usr = inputs.getText();
        String out = OutputLabel.getText();
        if(!usr.equals(""))
        {
	        if(usr.equals("E") || usr.equals("e"))
	        {
	        	Client.available = false;
	        	connected = false;
	        	UpdateInput();
	        }
	        else if(usr.equals("T") || usr.equals("t"))
	        {
	            System.out.println("(Main) Thread ID: " + Thread.currentThread().getId());
	            out += "Thread ID: " + Thread.currentThread().getId();
	            OutputLabel.setText(out);
	        }
	        else if(usr.equals("P") || usr.equals("p"))
	        {
	            System.out.println("(Main) IP: " + Client.inetAddress.getHostAddress());
	            System.out.println("(Main) Public IP: " + Client.getNetworkAddress());
	            out+= "\nIP: " + Client.inetAddress.getHostAddress() + "\nPublic IP: " + Client.getNetworkAddress() + "\n";
	            OutputLabel.setText(out);
	        }
	        else if(usr.equals("S") || usr.equals("s"))
            {
                Client.send = "Client: " + Client.getNetworkAddress();
                out+= "Sent\n";
	            OutputLabel.setText(out);
            }
	        else if(usr.equals("C") || usr.equals("c"))
	        {
	                System.out.println(CLIST);
	                out= "";
	                OutputLabel.setText(out);
	        }
	        else
	        {
	        	if(connected)
	                Client.send = "Client: "+ usr;
	        	else
	        	{
	        		IP_CONNECT = usr;
	        		connected=true;
	        	}
	        }
            inputs.setText("");
        }

        });
        
        VBox layout = new VBox();
      
        layout.getChildren().addAll(OutputLabel, InputLabel, inputs, button);
 
        Scene scene = new Scene(layout, 350, 400);
 
        scene.setFill(Color.DARKGRAY);
        
        window.setScene(scene);
        window.show();
        client = new Client(this);
        CR.start();
        System.out.println("Setup Complete");
    }
}
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.*;
import javafx.stage.Stage;
 
public class Main extends Application {
 
    Stage window;
    Button button;
    Label InputLabel;
    Label OutputLabel;
    TextField inputs;
    ScrollPane scroller;
    static Server server;
    private static Server_Runtime SR;
    
    private static final String CLIST = "Commands:\n\t(S) Stop server\n\t(T) Thread ID\n\t(C) Clear Screen\n\t(P) Port Number\n\t(I) IP Address\n\t(...) Send message to all clients.\n\t";
 
    public static void main(String[] args) {
        SR = new Server_Runtime();

        launch(args); // It calls start method defined bellow...
        System.out.println("END OF PROGRAM");
        Server.available = false;
        SR.interrupt();
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
        window.setTitle("Host Computer");
        
        InputLabel = new Label(CLIST);
        OutputLabel = new Label();
        button = new Button("Send");
        inputs = new TextField();
        scroller = new ScrollPane();
        button.setOnAction(e->{
        String usr = inputs.getText();
        String out = OutputLabel.getText();
	        if(usr.equals("S") || usr.equals("s"))
	        {
	        	Server.available = false;
	        	UpdateInput();
	        }
	        else if(usr.equals("T") || usr.equals("t"))
	        {
	            System.out.println("Thread ID: " + Thread.currentThread().getId());
	            out += "Thread ID: " + Thread.currentThread().getId();
	            OutputLabel.setText(out + "\n");
	        }
	        else if(usr.equals("I") || usr.equals("i"))
	        {
	            System.out.println("IP: " + server.inetAddress.getHostAddress());
	            System.out.println("Public IP: " + Server.getNetworkAddress());
	            out+= "\nIP: " + server.inetAddress.getHostAddress() + "\nPublic IP: " + Server.getNetworkAddress() + "\n";
	            OutputLabel.setText(out);
	        }
	        else if(usr.equals("P") || usr.equals("p"))
	        {
	            System.out.println("Port: " + Server.PORT);
	            out+="\nPort: " + Server.PORT;
	            OutputLabel.setText(out);
	        }
	        else if(usr.equals("C") || usr.equals("c"))
	        {
	                System.out.println(CLIST);
	                out = "" ;
	                OutputLabel.setText(out);
	        }
	        else
	        {
	                Server.send = "Server: "+ usr;
	                Server.send_number++;
	        }
	        inputs.setText("");

        });
        
        VBox layout = new VBox();
      
        scroller.setContent(OutputLabel);
        scroller.setHbarPolicy(ScrollBarPolicy.NEVER);
        scroller.setMinHeight(200);
        scroller.setMaxHeight(200);
        
        layout.getChildren().addAll(scroller, InputLabel, inputs, button);
 
        Scene scene = new Scene(layout, 350, 400);
 
        window.setScene(scene);
        window.show();

        server = new Server(this);
        SR.start();
    }
}
package RemoteApp;
	
import RemoteApp.JNI.KillProcCplusplus;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.WindowEvent;
import RemoteApp.network.MultiCastStatus;
import RemoteApp.network.socket.SocketView;
import RemoteApp.view.WindowScreen;


public class Main extends Application {
    
    
    private static ThreadContainer tc;
    
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/RemoteClientDoc.fxml"));
			Scene scene = new Scene(root,440,415);			
			primaryStage.initStyle(StageStyle.DECORATED);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Remote Client App");
			primaryStage.setResizable(false);
			primaryStage.show();
			
                        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent e) {
                            try {
                                    System.out.println("main");
                                    SocketView.closeSecSocket();
                                    SocketView.closeSocket();
                                    SocketView.closeServerSocket();
                                    MultiCastStatus.interruptSocket();
                                    ThreadContainer.getMulticast().interrupt();
                                    ThreadContainer.getUithread().interrupt();   
                                    KillProcCplusplus.killJavaProcessJni();
                                } catch(Exception ex) {
                                        
                                } finally {
                                
                            }
                                
                            }
                        });
                         
		} catch(Exception ex) {
                    Logger.getLogger(WindowScreen.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
                 
	}
        
        
        //later use
        public static void setFinalThreadContainer(ThreadContainer t) {
            tc = t;
        }
        
      
        
}

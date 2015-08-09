
package RemoteApp.model;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import RemoteApp.Constants;
import RemoteApp.RemoteClientController;
import RemoteApp.ThreadContainer;
import RemoteApp.model.serializables.MultiCast;
import RemoteApp.network.ConnectToServer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



public class ListViewModel   {
    
    private String hostname;
    private String ip;
    private int port;
    private boolean active;
    private boolean password;
    private Button button;
    
    
    public ListViewModel(String h,String ipa, int p,boolean act,boolean passwd) {
        hostname = h;
        this.ip = ipa;
        port = p;
        active = act;
        password = passwd;
        
        button = new Button("connect      ",new ImageView("icons/desktopicon.png"));   
        
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(port != 5900) {
                    if(Constants.isConnected == false) {
                        Constants.thishost = RemoteClientController.ip;
                        MultiCast mc = new MultiCast(ip,hostname,active," ",port,password);
                
                        if(password == false) {
                            ConnectToServer.setPassword("");
                            ThreadContainer threadcontainer = new ThreadContainer();
                            threadcontainer.setConnectToServerThreadRunnableAndStart(new ConnectToServer(mc,threadcontainer));
                        } else {                   
                            TextInputDialog dialog = new TextInputDialog("");
				dialog.setTitle("           ");
				dialog.setHeaderText("Enter password              ");

				Optional<String> result = dialog.showAndWait();
				
				if (result.isPresent()){
					
					if(result.get().length() <=0) {
						
					} else {
						String passwd = result.get();
						ConnectToServer.setPassword(passwd);
                                                ThreadContainer threadcontainer = new ThreadContainer();
                                                threadcontainer.setConnectToServerThreadRunnableAndStart(new ConnectToServer(mc,threadcontainer));
					}
					
				}
                    
                        }
                    } else {
                        
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Warning Dialog");
                        alert.setHeaderText("First Disconnect from remote pc");
                        alert.setContentText("press disconnect button to disconnect from remote pc \nor just close the window");
                        alert.showAndWait();
                        
                    }
                }
            }
        });
        
        button.setFont(Font.font("FontAwesome", FontWeight.BOLD, 12));     
        if(active == true) {
            button.setText("unavailable");
            button.setDisable(true);      
            button.getStylesheets().add("css/listviewitemconnect.css");
        } else {
            button.getStylesheets().add("css/listviewitemunavailable.css");
        }
    }
    
    public String getHostname() {
        return hostname;
    }
    
    public String getIp() {
     return ip;   
    }
    
    public int getPort() {
        return port;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public boolean isPasswordRequired() {
     return password;   
    }
    
    public String password() {
        if(password == false) {
            return "No";
        }
        return "Yes";
    }
    
    public Button getButton() {
       return button;
    }
    
    
    @Override
    public String toString() {
        return getClass().getName() + "["+"hostname="+hostname+",ip="+ip+",port="+port+",active="+active+",password="+password+"]";
    }
    
   
    
    
}

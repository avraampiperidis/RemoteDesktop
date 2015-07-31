package RemoteApp;


import RemoteApp.network.SocketConnection;
import java.awt.Dimension;
import java.awt.Toolkit;


public class StartServer {
        
    public static int width;
    public static int height;
    
    
    public StartServer() {
        
    }
    
    public static void startServer() {
        
        if(Constants.isFullScreen() == false) {
            Constants.setFullscreen(true);
            Constants.setBufferimage(true);
            Constants.setScreenArea(true);
                      
        }
        
        Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int)fullScreen.getWidth()/Constants.getScreenDivider();
        height = (int)fullScreen.getHeight()/Constants.getScreenDivider();
           
       
        ThreadContainer.setsocketConnectionAndRun(new SocketConnection());
        
        
    }
    
    
}

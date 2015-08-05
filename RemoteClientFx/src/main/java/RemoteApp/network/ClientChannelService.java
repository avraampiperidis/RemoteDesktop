package RemoteApp.network;


import RemoteApp.Constants;
import RemoteApp.KeyInterface;
import RemoteApp.view.WindowScreen;
import RemoteApp.model.serializables.ClientStatus;
import RemoteApp.model.serializables.ImageBlock;
import RemoteApp.model.serializables.MouseKeyboardStatus;
import RemoteApp.model.serializables.ScreenInfo;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.spec.SecretKeySpec;
import RemoteApp.ThreadContainer;
import RemoteApp.model.serializables.MultiCast;
import RemoteApp.network.socket.SocketView;


public class ClientChannelService implements Runnable,KeyInterface {
    
    private static String remotehost;
    
    private static  int port;
    
    private ScreenInfo screen;
    
    private ThreadContainer tc;
    private static Constants con;
    
    public ClientChannelService(int p,ScreenInfo screen,MultiCast mc,ThreadContainer tc,Constants c) {
        
        port = mc.getPort();
        this.screen = screen;
        remotehost = mc.getHostName();
        WindowScreen.windowsScreen(screen,mc,tc,c);
        this.tc = tc;
        con = c;
    }
    
    
    
    @Override
    public void run() {
        try {           
            SocketView.setServerSocket(port);
            while(!Thread.currentThread().isInterrupted()) {
            synchronized (this) {
                
                if(con.isBlockInputImage() == true) {
                
                SocketView.serverSocketAccept();
                
                Key secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
                Cipher cipher = Cipher.getInstance(TRANSFORMATION);
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                
                Object tempob = (Object)SocketView.getObjectInputStream();
                if(tempob instanceof SealedObject) {      
                    SealedObject temp = (SealedObject)tempob;
                    if(temp.getObject(cipher) instanceof ImageBlock) {
                        final ImageBlock image  =(ImageBlock)temp.getObject(cipher);
                        if(image != null) {
                            
                            new Thread() {
                                @Override
                                public void run() {
                                    WindowScreen.drawBlockOnPanel(image);
                                }
                                
                            }.start();
                            
                        }
                    }                                    
                }           
              }    
            }   
            }
            
        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(ClientChannelService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            SocketView.closeSocket();
            SocketView.closeServerSocket();
        }   
    } 
    
    
        
    
    public static void sendClientStatus(ClientStatus cs) {
        try {
            SocketView.setSecSocket(remotehost,port);
            SocketView.secWriteobject(cs);        
        } catch (IOException ex) {
             Logger.getLogger(ClientChannelService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    
    public  static void sendMouseKeyboardStatusObject(MouseKeyboardStatus mks) {
        
        try {
            SocketView.setSecSocket(remotehost,port);          
            SocketView.secWriteobject(mks);
            con.setBlockImage(true);
        } catch (IOException ex) {
             Logger.getLogger(ClientChannelService.class.getName()).log(Level.SEVERE, null, ex);
            try {            
              SocketView.getServerSocket().close();
              SocketView.getSecSocket().close();             
            } catch (IOException ex1) {
                Logger.getLogger(ClientChannelService.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
        
    }
    
    
    
}

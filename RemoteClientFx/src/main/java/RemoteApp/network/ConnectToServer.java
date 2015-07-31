package RemoteApp.network;


import RemoteApp.Constants;
import RemoteApp.ThreadContainer;
import RemoteApp.model.serializables.ClientStatus;
import RemoteApp.model.serializables.ScreenInfo;
import java.io.IOException;
import java.net.InetAddress;
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
import RemoteApp.model.serializables.MultiCast;
import RemoteApp.network.socket.SocketView;


public class ConnectToServer implements Runnable {

    
    private static  String password;
    private boolean errorex = false;
    
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    private static final String KEY = "kdif(34&4']qvDF@";
    private int port;
    private String serverip;
    private MultiCast mc;
    private ThreadContainer tc;
    
    public ConnectToServer(MultiCast mc,ThreadContainer tc) {
        port = mc.getPort();
        serverip = mc.getIp();
        this.mc = mc;
        this.tc = tc;
    }
    
    
    @Override
    public void run() {       
        
        try {
            
            Key secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            
            String ip = null;
            if(Constants.thishost.isEmpty()) {
                ip = InetAddress.getLocalHost().getHostAddress();
            } else {
                ip = Constants.thishost;
            }
            
            
            ClientStatus cs = new ClientStatus(false,password,true,false,ip,null);
            SealedObject sealed = new SealedObject(cs,cipher);
            
            SocketView.setSocket(serverip,port);
            SocketView.writeObject(sealed);
            
            
            SocketView.setServerSocket(port);
            SocketView.getServerSocket().setReuseAddress(true);
            Constants cons = new Constants();
            if(cons.isRunningconnectToServer() == false) {
                cons.setRunningconnectToServer(true);
                cons.setClientChannelService(true);
            }
            
            while(!Thread.currentThread().isInterrupted()) {
                
            System.out.println("accept");
            
            SocketView.serverSocketAccept();
            
            System.out.println("acceptted");
            Object tempob = (Object)SocketView.getObjectInputStream();     
            ScreenInfo screen;
                
            cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            
            
               if(tempob instanceof SealedObject) {
                    SealedObject temp = (SealedObject)tempob;
                    if(temp.getObject(cipher) instanceof ClientStatus) {
                       ClientStatus clientstatus = (ClientStatus)temp.getObject(cipher);
                        if(clientstatus.isokToConnect()) {
                            screen = clientstatus.getScreen();
                        
                            SocketView.getServerSocket().close();
                            
                            cons.setRunningconnectToServer(false);          
                            tc.setclientChannelServiceThreadRunnableAndStart(new  ClientChannelService(port,screen,mc,tc,cons));
                            break;
                        } else {
                            break;
                        }
                }                  
              }
            }
            
                
        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(ConnectToServer.class.getName()).log(Level.SEVERE, null, ex);
            errorex = true;
        } finally {
            try {
                if(SocketView.getServerSocket() != null) {
                    SocketView.getServerSocket().close();
                }
                if(errorex == true) {
                    errorex = false;
                    //if exception occurs!
                    // still empty
                }
            } catch(IOException ex) {
                Logger.getLogger(ConnectToServer.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
        
       
    }
    
    
    
    public static void setPassword(String p) {
        password = p;
    }
    
    
    
}

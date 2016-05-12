package RemoteApp.network;



import RemoteApp.BufferImage;
import RemoteApp.Constants;
import RemoteApp.ScreenArea;
import RemoteApp.ThreadContainer;
import RemoteApp.view.RemoteServerForm;
import RemoteApp.model.serializables.MouseKeyboardStatus;
import RemoteApp.model.ImageChange;
import RemoteApp.model.serializables.ClientStatus;
import RemoteApp.model.serializables.ImageBlock;
import RemoteApp.model.serializables.ScreenInfo;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MenuItem;
import java.awt.Point;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import RemoteApp.MouseKeyboardFunctions;
import RemoteApp.network.socket.SocketView;


public class ServerChannelService implements Runnable  {

        
    private Dimension fullScreen;
    
    private int width;
    private int height;
    
    private static String host;
    private static int port;
    
    public static boolean shift = false;
    
    
    private static final Key secretKey = new SecretKeySpec(Constants.KEY.getBytes(), Constants.ALGORITHM);
    private static Cipher cipher;
    
    
    public ServerChannelService(ScreenInfo screen,Dimension fullscreen,String host) {
        
        try {
            cipher  =Cipher.getInstance(Constants.TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException ex) {
            Logger.getLogger(ServerChannelService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        width = screen.getBlockWidth();
        height = screen.getBlockHeight();
        
        this.fullScreen = fullscreen;
        
        ServerChannelService.host = host;
        port = Constants.getServerPort();
        
        
        
         ThreadContainer.setMouseKeyboardStatusThreadAndRun(new Runnable() {
            @Override
            public void run() {
                
                SocketView.setServerSocket();
                
                while(Constants.isServerChannelService()) {
                                        
                        try {
                            
                            SocketView.serversocketAccept();
                            
                            Object tempob = (Object)SocketView.getObjectInputStream();
                            if(tempob instanceof MouseKeyboardStatus) {
                                final MouseKeyboardStatus mks = (MouseKeyboardStatus)tempob;
                                new Thread() {
                                    @Override
                                    public void run() {
                                       MouseKeyboardFunctions.setMouseKeyboartStatusonScreenEvents(mks); 
                                    }
                                }.start();
                                  
                                
                            }
                            
                            if(tempob instanceof ClientStatus) {
                                ClientStatus cs = (ClientStatus)tempob;
                                if(cs.isDisconnected() == true) {
                                    Constants.setScreenArea(false);
                                    Constants.setFullscreen(false);
                                    Constants.setBufferimage(false);
                                    
                                    SocketView.closeSocket();
                                    SocketView.closesocketinfo();
                                    SocketView.closeServerSocket();
                                    Constants.status = true;
                                    Constants.setServerChannelService(false);
                                    System.out.println("shutdown!");
                                    
                                    RemoteServerForm.startServerbtn.setEnabled(true);
                                    RemoteServerForm.serverlabel.setText("");
                                    
                                    for(int i =0; i < RemoteServerForm.popup.getItemCount(); i++) {
                                    if(((MenuItem)RemoteServerForm.popup.getItem(i)).getName().equals("host")) {
                                    RemoteServerForm.popup.remove(i);
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            RemoteServerForm.startserver();
                                        }
                                    }.start();
                                    
                                    break;
                                        }
                                    }
                                    
                                    
                                }
                            }
                            
                        } catch (IOException | ClassNotFoundException ex) {
                            Logger.getLogger(ServerChannelService.class.getName()).log(Level.SEVERE, null, ex);
                            try {
                                if(SocketView.getSocketinfo() != null) {
                                    SocketView.getSocketinfo().close();
                                }
                            } catch (IOException ex1) {
                                Logger.getLogger(ServerChannelService.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        
                    
                }
                
            }
        });
        
        ThreadContainer.setBufferImageAndRun(new BufferImage());
        
        Point p = new Point(0,0);
        ImageChange fullimage = null;
        try {
            fullimage = new ImageChange(BufferImage.getStartUpScrenShot(),p);
        } catch (AWTException ex) {
             Logger.getLogger(ServerChannelService.class.getName()).log(Level.SEVERE, null, ex);
        }
        ServerChannelService.sendImage(fullimage);
        
        
        
        ThreadContainer.setFullScreenThreadAndRun(new Runnable() {
            @Override
            public void run() {
                try {                  
                   while(Constants.isFullScreen()) { 
                        Thread.sleep(3000);
                        Point p = new Point(0,0);
                        ImageChange fullimageScreen;
                        fullimageScreen = new ImageChange(BufferImage.getStartUpScrenShot(),p);                    
                        ServerChannelService.sendImage(fullimageScreen);
                   }
                } catch (AWTException | InterruptedException ex) {
                    Logger.getLogger(ServerChannelService.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    
                }
            }
        });
                        
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
             Logger.getLogger(ServerChannelService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
       
        
        ScreenArea[] screenAreas = new ScreenArea[25];

        int x=0;
        int y =0;
        
        int b =1;
        
        ThreadContainer.setScreenAreaThreadCount(25);
        
        for(int i =0; i < 25; i++) {
            if(x == 5) {
                y++;
                x =0;
            }
            screenAreas[i] = new ScreenArea(fullScreen,b);
            screenAreas[i].setBlock(width*x,height*y);
            
            
            ThreadContainer.setSpecificScreenAreaThread(i,screenAreas[i]);
            ThreadContainer.getSpacificScreenAreaThread(i).setName("screenAreaThread"+i);
            x++;
            delay();
        }
        
        
        
    }
    
    private void delay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
           Logger.getLogger(ServerChannelService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public synchronized  static void sendImage(ImageChange image) {       
                try {
                   
                    byte[] imageinbytes;
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(image.getImage(), "gif", baos);
                    baos.flush();
                    imageinbytes = baos.toByteArray();
                    baos.close();
                    
                    ImageBlock imagetoSend = new ImageBlock(imageinbytes,image.getPoint());
                    
                         SealedObject sealed = new SealedObject(imagetoSend,cipher);                 
                         SocketView.setScoket(host);
                         SocketView.writeObject(sealed);
                         
                } catch (IOException | IllegalBlockSizeException ex) {
                    Logger.getLogger(ServerChannelService.class.getName()).log(Level.SEVERE, null, ex);
                }
      
    }
    
   
    //unused
    @Override
    public  void run() {
        
    }
    
    
    
}

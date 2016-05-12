package RemoteApp.network;


import RemoteApp.Constants;
import RemoteApp.ThreadContainer;
import RemoteApp.model.serializables.ClientStatus;
import RemoteApp.model.serializables.ScreenInfo;
import RemoteApp.view.RemoteServerForm;

import java.awt.Dimension;
import java.awt.MenuItem;
import java.awt.Toolkit;

import java.io.*;
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
import RemoteApp.network.socket.SocketView;


public class SocketConnection implements Runnable {

    private static int width;
    private static int height;
    
    private static String host;
    private static String password;
    private static String remotehost="";
    private boolean errorex = false;
    
    
         
    
    @Override
    public void run() {
        
        
        Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int)fullScreen.getWidth()/Constants.getScreenDivider();
        height = (int)fullScreen.getHeight()/Constants.getScreenDivider();
        
        ScreenInfo screen = new ScreenInfo((int)fullScreen.getWidth(),(int)fullScreen.getHeight(),width,height);
        
        try {
            
            SocketView.setServerSocket();
            if(Constants.isStopserver() == false) {
                Constants.setStopServer(true);
            }
            while(Constants.isStopserver()) {
                            
                SocketView.serversocketAccept();
                  
                 Key secretKey = new SecretKeySpec(Constants.KEY.getBytes(), Constants.ALGORITHM);
                 Cipher cipher = Cipher.getInstance(Constants.TRANSFORMATION);
                 cipher.init(Cipher.DECRYPT_MODE, secretKey);
                                       
                 ClientStatus cs = null;
                 
                 Object tempob = (Object)SocketView.getObjectInputStream();
                 if(tempob instanceof SealedObject) {
                     SealedObject temp = (SealedObject)tempob;
                     if(temp.getObject(cipher) instanceof ClientStatus) {
                         ClientStatus client = (ClientStatus)temp.getObject(cipher);
                         if(client.isRequest() == true) {
                             if(client.getPassword().equals(password)) {
                                 cipher = Cipher.getInstance(Constants.TRANSFORMATION);
                                 cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                                 remotehost = client.getIp();
                                 ClientStatus csok = new ClientStatus(false,password,true,true,remotehost,screen);
                                 SealedObject sealed = new SealedObject(csok,cipher);
                                 System.out.println(client.getIp());
                                 SocketView.setScoket(remotehost);
                                 SocketView.writeObject(sealed);
                                 SocketView.closeServerSocket();
                                 SocketView.closeSocket();
                                 
                                 addtrayiconremotehost(remotehost);
                                 Constants.status = true;
                                 Constants.setServerChannelService(true);
                                 
                                 ThreadContainer.setServerChannelServiceAndRun(new ServerChannelService(screen,fullScreen,remotehost));
                                        
                                 Constants.setStopServer(false);
                                 
                                 break;
                                 
                             } else {
                                
                             }
                         }
                     }
                 }
                   
                    
                    }
                    
                } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | ClassNotFoundException | BadPaddingException ex) {
            Logger.getLogger(SocketConnection.class.getName()).log(Level.SEVERE, null, ex);
            errorex = true;
        } finally {
            if(errorex == true) {
                errorex = false;
            new Thread() {
                @Override
                public void run() {
                    //something went wrong restart server!
                    RemoteServerForm.startserver();
                }
                }.start();
            }
        }
    }
           
    
    public static void setPassword(String p) {
        password = p;
    }
    
    public static void setHost(String h) {
        host = h;
    }
    
    public static String getHost() {
        return host;
    }

    public  void addtrayiconremotehost(String ip) {
        for(int i =0; i < RemoteServerForm.popup.getItemCount(); i++) {
            if(((MenuItem)RemoteServerForm.popup.getItem(i)).getName().equals("start")) {
                RemoteServerForm.popup.remove(i);
                break;
            }
        }
        
        MenuItem newip = new MenuItem(ip+" is connected");
        newip.setName("host");
        newip.addActionListener(RemoteServerForm.listener);
        RemoteServerForm.popup.add(newip);
        
    }


}

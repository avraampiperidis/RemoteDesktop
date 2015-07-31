/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RemoteApp.network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import RemoteApp.Constants;
import RemoteApp.model.serializables.MultiCast;


public class MultiCastStatus implements Runnable {

    private static MulticastSocket s;
    
    
    @Override
    public void run() {
        
            try {
                           
                        String multicast = "224.0.0.1";
                        int port = 5900;
                        int bufsize = 1024 * 4;
                            
                        InetAddress group = InetAddress.getByName(multicast);
                        s = new MulticastSocket(port);
                        
                        s.joinGroup(group);
                            
                        while(!Thread.currentThread().isInterrupted()) {
                        byte[] buffer = new byte[bufsize];
                        s.receive(new DatagramPacket(buffer,bufsize,group,port));
                        ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                        ObjectInputStream obin = new ObjectInputStream(bais);
                                
                        Object ob = obin.readObject();
                            if(ob instanceof MultiCast) {
                                MultiCast mc = (MultiCast)ob;
                                    if(!(mc.getIp().equals(InetAddress.getLocalHost().getHostAddress()))) {
                                        
                                        Constants.addnetworkMap(mc.getIp().trim(), mc);
                                        System.out.println(mc.toString());
                                       
                                    }
                                }
                            }
                            
                        } catch (SocketException ex) {
                            Logger.getLogger(MultiCastStatus.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException | ClassNotFoundException ex) {
                            Logger.getLogger(MultiCastStatus.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                
            }
        
        
    }
    
    
    public static void interruptSocket() {
        if(s != null) {
            s.close();        
        }
    }
    
    
    
}


package RemoteApp.network;

import RemoteApp.Constants;
import RemoteApp.model.serializables.MultiCast;
import RemoteApp.view.RemoteServerForm;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static RemoteApp.view.RemoteServerForm.serveradapters;


public class MultiCastStatus implements Runnable {

    @Override
    public void run() {
        
        while(!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(3000);  
                    
                        String multicast = "255.255.255.255";
                        int port = Constants.getServerPort();
                        
                        InetAddress group = InetAddress.getByName(multicast);
                        
                        Logger.getLogger(this.getClass().getName()).log(Level.INFO, serveradapters.getSelectedItem().toString());
                        
                            MultiCast mc = new MultiCast(serveradapters.getSelectedItem().toString(),InetAddress.getLocalHost().getHostName(),Constants.status,"server",Constants.getServerPort(),Constants.isPasswordRequired());
                            
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            ObjectOutputStream oos = new ObjectOutputStream(baos);
                            oos.writeObject(mc);
                            byte[] data = baos.toByteArray();
 
                            DatagramSocket ds = new DatagramSocket();
                            DatagramPacket dp = new DatagramPacket(data,data.length,group,port);
                            ds.send(dp);
                            
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(RemoteServerForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(RemoteServerForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                Logger.getLogger(MultiCastStatus.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                    
                }
                
                
                
                }
        
    }
    
}

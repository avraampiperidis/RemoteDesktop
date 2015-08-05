
package RemoteApp.network;

import java.net.SocketException;
import java.util.ArrayList;


public interface NetInterface {
    
    public  ArrayList<String> getAllIp() throws SocketException;
    
}

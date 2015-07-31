
package RemoteApp.network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class NetworkInter {
    
    private static ArrayList<String> netips;
    
    public static ArrayList<String> getAllIp() throws SocketException {
        netips = new ArrayList<>();
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) {
            displayInterfaceInformation(netint);
        } 
        return netips;
    }
    
    
    private static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        if(netint.getName().matches("eth.") || netint.getName().matches("wlan.")) {
            
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
            for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                if(!inetAddress.toString().isEmpty() && !inetAddress.toString().contains(":")) {
                    netips.add(inetAddress.toString().replace('/',' ').trim());
                }
                
            }
        }
        
     }
    
    
}

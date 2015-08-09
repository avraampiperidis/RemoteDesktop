package RemoteApp;


import java.awt.Dimension;
import java.util.HashMap;
import RemoteApp.model.serializables.MultiCast;


public final class Constants {
    
    
    private static int port5900 = 5900;
    private Dimension dim;
    public static boolean isConnected = false;
    private boolean clientchannelservice = true;
    private boolean connectToServer = true;
    private boolean blockInputImage = true;
    public static String thishost = "";
    private static HashMap<String,MultiCast> networkmap = new HashMap<>();
    
    public Constants() {
        
    }
    
   
    
    public synchronized static void addnetworkMap(String key,MultiCast mc) {
        networkmap.put(key, mc);
    }
    
    public synchronized static HashMap<String,MultiCast> getNetworkmap() {
        return networkmap;
    }
    
    public static void resetHashMap() {
        networkmap.clear();
        
    }
    
    public synchronized  boolean isBlockInputImage() {
        return blockInputImage;
    }
    
    public synchronized  void setBlockImage(boolean b) {
        blockInputImage = b;
    }
    
    public  void setClientChannelService(boolean b) {
        clientchannelservice = b;
    }
    
    public  boolean isClientChannelService() {
        return clientchannelservice;
    }
    
    public  boolean isRunningconnectToServer() {
        return connectToServer;
    }
    
    public  void setRunningconnectToServer(boolean b){
        connectToServer = b;
    }
    
    public static int getPort() {
        return port5900;
    }
    
    public static void setServerPort(int p) {
        port5900 = p;
    }
      
    
    public  Dimension getDimension() {
        return dim;
    }
    
    public  void setDimension(Dimension d) {
        dim = d;
    }
    
    
    
    
    
}

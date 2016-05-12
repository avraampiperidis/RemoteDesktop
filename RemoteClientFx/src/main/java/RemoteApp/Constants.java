package RemoteApp;


import java.awt.Dimension;
import java.util.HashMap;
import RemoteApp.model.serializables.MultiCast;


public final class Constants {
    
    private Constants() {}
    
    
    private static int port = 5900;
    private static Dimension dim;
    public static boolean isConnected = false;
    private static boolean clientchannelservice = true;
    private static boolean connectToServer = true;
    private static boolean blockInputImage = true;
    public static String thishost = "";
    private static HashMap<String,MultiCast> networkmap = new HashMap<>();
    
    
    public  static void addnetworkMap(String key,MultiCast mc) {
        networkmap.put(key, mc);
    }
    
    public  static HashMap<String,MultiCast> getNetworkmap() {
        return networkmap;
    }
    
    public static void resetHashMap() {
        networkmap.clear();
        
    }
    
    public  static boolean isBlockInputImage() {
        return blockInputImage;
    }
    
    public static  void setBlockImage(boolean b) {
        blockInputImage = b;
    }
    
    public static void setClientChannelService(boolean b) {
        clientchannelservice = b;
    }
    
    public static boolean isClientChannelService() {
        return clientchannelservice;
    }
    
    public static boolean isRunningconnectToServer() {
        return connectToServer;
    }
    
    public static void setRunningconnectToServer(boolean b){
        connectToServer = b;
    }
    
    public static int getPort() {
        return port;
    }
    
    public static void setServerPort(int p) {
        port = p;
    }
      
    
    public static Dimension getDimension() {
        return dim;
    }
    
    public static void setDimension(Dimension d) {
        dim = d;
    }
    
    
    
    
    
}

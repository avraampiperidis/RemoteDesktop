package RemoteApp;

import java.util.HashMap;
import RemoteApp.model.serializables.MultiCast;


public final class Constants {
    
    private Constants() {}
    
    public static final String ALGORITHM = "AES";
    public static final String TRANSFORMATION = "AES";
    public static final String KEY = "kdif(34&4']qvDF@";
    
    
    private static int serverport = 5900;
    private static boolean stopServer = true;
    private static int screendivider = 5;
    private static boolean serverchannelservice = false;
    private static boolean screenArea = true;
    private static boolean fullscreen = true;
    private static boolean bufferimage = true;
    private static boolean blocksendimage = true;
    private static boolean passwordrequired = false;
    public static boolean status = true;
    
    private static HashMap<String,MultiCast> networkMap = new HashMap<>();
    
    
    public static void addNetworkMapKey(String key,MultiCast mc) {
        networkMap.put(key, mc);
    }
    
    public static HashMap<String,MultiCast> getnetworkMap() {
        return networkMap;
    }
    
    public static void setpasswordRequired(boolean b) {
        passwordrequired = b;
    }
    
    public static boolean isPasswordRequired() {
        return passwordrequired;
    }
    
    public synchronized static boolean isBlocksendImage() {
        return blocksendimage;
    }
    
    public synchronized static void setBlockSendImage(boolean b) {
        blocksendimage = b;
    }
       
    
    public static boolean isbufferimage() {
        return bufferimage;
    }
    
    public static void setBufferimage(boolean b) {
        bufferimage = b;
    }
    
    public static boolean isFullScreen() {
        return fullscreen;
    }
    
    public static void setFullscreen(boolean b) {
        fullscreen = b;
    }
    
    public static boolean isScreenArea() {
        return screenArea;
    }
    
    public static void setScreenArea(boolean b) {
        screenArea = b;
    }
    
    public static void setServerChannelService(boolean b) {
        serverchannelservice = b;
    }
    
    public static boolean isServerChannelService() {
        return serverchannelservice;
    }
    
    public static int getServerPort() {
        return serverport;
    }
    
    public static void setServerPort(int p) {
        serverport = p;
    }
    
    public synchronized static boolean isStopserver() {
       return stopServer;
    }
    
    public static void setStopServer(boolean b) {
        stopServer = b;
    }
    
    public synchronized static int getScreenDivider() {
        return screendivider;
    }
    
    
}

package RemoteApp;


public class ThreadContainer {
    
    private static Thread socketConnection;
    private static Thread serverChannelService;
    private static Thread bufferImage;
    private static Thread fullScreenThread;
    private static Thread getMouseKeyboardStatusThread;
    private static Thread screenAreaThreads[];
    private static Thread multicastthread;
    
    private static int screenAreaThreadCount;
    
    //---------------------------------------
    
    public static void setMulticast(Runnable r) {
        multicastthread = new Thread(r);
        multicastthread.start();
    }
    
    public static Thread getMulticast() {
        return multicastthread;
    }
    
    public static void setScreenAreaThreadCount(int c) {
        screenAreaThreadCount = c;
        screenAreaThreads = new Thread[c];
    }
    
    public static void setSpecificScreenAreaThread(int i,Runnable r) {
        screenAreaThreads[i] = new Thread(r);
        screenAreaThreads[i].start();
    }
    
    public static Thread getSpacificScreenAreaThread(int i) {
        return screenAreaThreads[i];
    }
    
    public static void setsocketConnectionAndRun(Runnable r) {
        socketConnection = new Thread(r);
        socketConnection.start();
    }
    
    public static Thread getSocketConnection() {
        return socketConnection;
    }
    
    public static void setServerChannelServiceAndRun(Runnable r) {
        serverChannelService = new Thread(r);
        serverChannelService.start();
    }
    
    public static Thread getServerChannelService() {
        return serverChannelService;
    }
    
    public static void setBufferImageAndRun(Runnable r) {
        bufferImage = new Thread(r);
        bufferImage.start();
    }
    
    public static Thread getBufferImage() {
        return bufferImage;
    }
    
    public static void setFullScreenThreadAndRun(Runnable r) {
        fullScreenThread = new Thread(r);
        fullScreenThread.start();
    }
    
    public static Thread getFullScreenThread() {
        return fullScreenThread;
    }
    
    public static void setMouseKeyboardStatusThreadAndRun(Runnable r) {
        getMouseKeyboardStatusThread = new Thread(r);
        getMouseKeyboardStatusThread.start();
    }
    
    public static Thread getMouseKeyboardStatusThread() {
        return getMouseKeyboardStatusThread;
    }
    
    
    public static int getScreenAreaThreadCount() {
        return screenAreaThreadCount;
    }
    
    
}

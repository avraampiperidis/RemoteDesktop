package RemoteApp;


public class ThreadContainer {
    
    
    private  Thread connecToServerThread;
    private  Thread clientChannelServiceThread;
    private  Thread mouseOverPanelThread;
    private static Thread multicastthread;
    private static Thread uithread;
    
    public ThreadContainer() {
        
    }
    
    
    public static void setUithread(Runnable r) {
        uithread = new Thread(r);
        uithread.setName("uithread");
        uithread.start();
    }
    
    public static Thread getUithread() {
        return uithread;
    }
    
    public static void setMulticast(Runnable r) {
        multicastthread = new Thread(r);
        multicastthread.setName("multicast");
        multicastthread.start();
    }
    
    public static Thread getMulticast() {
        return multicastthread;
    }
    
    public  Thread getConnectToServerThread() {
        return connecToServerThread;
    }
    
    public  void setConnectToServerThreadRunnableAndStart(Runnable r) {
        connecToServerThread = new Thread(r);
        connecToServerThread.setName("connectToServerThread");
        connecToServerThread.start();
        
    }
    
    public  Thread getclientChannelServiceThread() {
        return clientChannelServiceThread;
    }
    
    public  void setclientChannelServiceThreadRunnableAndStart(Runnable r) {
        clientChannelServiceThread = new Thread(r);
        clientChannelServiceThread.setName("channelservicethread");
        clientChannelServiceThread.start();
    }
    
    public  void setMouseOverPannelAndStart(Runnable r) {
        mouseOverPanelThread = new Thread(r);
        mouseOverPanelThread.setName("mouseoverpanelthread");
        mouseOverPanelThread.start();
    }
    
    public  Thread getMouseOverPanelThread() {
        return mouseOverPanelThread;
    }
    
    
    
}

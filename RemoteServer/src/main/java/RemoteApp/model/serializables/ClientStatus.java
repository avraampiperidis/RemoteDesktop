package RemoteApp.model.serializables;


import java.io.Serializable;


public class ClientStatus implements Serializable {
    
    private boolean disconnect = false;
    private String password = "";
    private boolean requestConnect = false;
    private boolean okToConnect = false;
    private String ip = "";
    private ScreenInfo screen;
    
    
    public ClientStatus(boolean dis,String pass,boolean req,boolean stat,String ip,ScreenInfo screen) {
        disconnect = dis;
        password = pass;
        requestConnect = req;
        okToConnect = stat;
        this.ip = ip;
        this.screen = screen;
    }
    
    
    public boolean isDisconnected() {
        return disconnect;
    }
    
    public String getPassword() {
        if(!(password.isEmpty())) {
            return password;
        }
        return "";
    }
    
    public boolean isRequest() {
        return requestConnect;
    }
    
    public boolean isokToConnect() {
        return okToConnect;
    }
    
    public String getIp() {
        return ip;
    }
    
    public ScreenInfo getScreen() {
        return screen;
    }
    
    @Override
    public String toString() {
        return getClass().getName() + "[disconnect="+disconnect
                +",password=****"
                +",requestToConnect="+requestConnect
                +",okToConnect="+okToConnect
                +",ip="+ip
                + screen()
                +"]";
    }
    
    public String screen() {
        if(screen != null) {
            return screen.toString();
        }
        return "";
    }
    
    
}

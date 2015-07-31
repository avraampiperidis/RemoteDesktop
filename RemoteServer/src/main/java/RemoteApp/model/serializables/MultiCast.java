
package RemoteApp.model.serializables;

import java.io.Serializable;


public class MultiCast implements Serializable {
    
    private final String ip;
    private final boolean active;
    private final String name;
    private final String role;
    private final int port;
    private final boolean passwordrequired;
    
    public MultiCast(String ip,String hostname,boolean b,String r,int p,boolean pass) {
        this.ip = ip;
        name = hostname;
        active = b;
        role = r;
        port = p;
        passwordrequired = pass;
    }
    
    public String getIp() {
        return ip;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public String getName() {
        return name;
    }
    
    public String getRole() {
        return role;
    }
    
    public int getPort() {
        return port;
    }
    
    public boolean isPasswordRequired() {
        return passwordrequired;
    }
    
    public String getHostName() {
        return name;
    }
    
    @Override
    public String toString() {
        return getClass().getName() + "[" + "ip="+ip+",active="+active+",name="+name+",role="+role+",port="+port+",passwordrequired="+passwordrequired+"]";
    }
    
    
}

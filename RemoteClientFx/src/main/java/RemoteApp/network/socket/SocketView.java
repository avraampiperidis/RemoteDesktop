
package RemoteApp.network.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SocketView {
    
    private static Socket secsocket;
    private static Socket socket;
    private static ServerSocket serverSocket;
    
    private static ObjectOutputStream obout;
    private static ObjectInputStream obin;
    private static String host;
    
    public  static void setSocket(String h,int port) throws IOException {
        host = h;
        socket = new Socket(host,port);      
    }
    
    public  static void setSecSocket(String h,int port) throws IOException {
        host = h;
        secsocket = new Socket(host,port);
    }
    
    public  static void setServerSocket(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
    
    
    
    public  static Socket getSocket() {
        return socket;
    }
    
    public  static Socket getSecSocket() {
        return secsocket;
    }
    
    public  static ServerSocket getServerSocket() {
        return serverSocket;
    }
    
   
    
    public  static void writeObject(Object ob) throws IOException { 
        
        obout = new ObjectOutputStream(socket.getOutputStream());
        obout.writeObject(ob);
        obout.flush();
        obout.close();
        socket.close();
    }
    
    public  static void secWriteobject(Object ob)  {
        try {
            obout = new ObjectOutputStream(secsocket.getOutputStream());
            obout.writeObject(ob);
            obout.flush();
            obout.close();
            secsocket.close();
        } catch (Exception ex) {
            Logger.getLogger(SocketView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  static Object getObjectInputStream() throws IOException {
        try {
            obin = new ObjectInputStream(socket.getInputStream());                
            return obin.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public  static void serverSocketAccept() throws IOException {
         socket = serverSocket.accept();
    }
    
   public static void closeSocket() {
       if(socket != null) {
           try {
               socket.close();
           } catch (IOException ex) {
               Logger.getLogger(SocketView.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
   
   public static void closeSecSocket() {
       if(secsocket != null) {
           try {
               secsocket.close();
           } catch (IOException ex) {
               Logger.getLogger(SocketView.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
   
   public static void closeServerSocket() {
       if(serverSocket != null) {
           try {
               serverSocket.close();
           } catch (IOException ex) {
               Logger.getLogger(SocketView.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
    
    
}

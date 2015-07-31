
package RemoteApp.network.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import RemoteApp.Constants;


public class SocketView {
    
    private static Socket socketinfo;
    private static Socket socket;
    private static ServerSocket serverSocket;
    
    private static ObjectOutputStream obout;
    private static ObjectInputStream obin;
    private static String host;
    
    public static void setDefaultServerSocket() {
        try {
            serverSocket = new ServerSocket(0);
        } catch (IOException ex) {
            Logger.getLogger(SocketView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int getServerSocketPort() {
        return serverSocket.getLocalPort();
    }
    
    public  static void setServerSocket() {
        try {
            serverSocket = new ServerSocket(Constants.getServerPort());
        } catch (IOException ex) {
            Logger.getLogger(SocketView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  static void setScoket(String h) throws IOException {
        host = h;
        socket = new Socket(host,Constants.getServerPort());
    } 
    
    public  static void serversocketAccept() {
        try {
            socketinfo = serverSocket.accept();
        } catch (IOException ex) {
            Logger.getLogger(SocketView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized static void writeObject(Object ob) throws IOException {
        obout = new ObjectOutputStream(socket.getOutputStream());
        obout.writeObject(ob);
        obout.flush();
        socket.close();
    }
    
    public static Object getObjectInputStream() throws IOException, ClassNotFoundException {
        obin = new ObjectInputStream(socketinfo.getInputStream());
        return obin.readObject();
    }
   
    public static void closeSocket() throws IOException {
        socket.close();
    }
    
    public static void closesocketinfo() throws IOException {
        socketinfo.close();
    }
    
    public static Socket getSocketinfo() {
        return socketinfo;
    }
    
    public static void closeServerSocket() throws IOException {
        serverSocket.close();
    }
    
    
}

package RemoteApp.view;

import RemoteApp.network.SocketConnection;
import RemoteApp.StartServer;
import RemoteApp.ThreadContainer;
import RemoteApp.network.MultiCastStatus;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import RemoteApp.Constants;
import RemoteApp.model.serializables.MultiCast;
import RemoteApp.network.NetworkInter;
import RemoteApp.network.socket.SocketView;





public class RemoteServerForm extends javax.swing.JFrame {

    public static PopupMenu popup;
    public static ActionListener listener;
    private static Thread tempthread;
   
    public RemoteServerForm() throws SocketException {
        initComponents();
        
        if(SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage("icon.png");
            
            listener = new ActionListener() {
               
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                   if(!e.getActionCommand().isEmpty())   { 
                   if(e.getActionCommand().equals("show")) {
                       setVisible(true);
                    } else if(e.getActionCommand().equals("hide")) {
                       setVisible(false);
                        } 
                 }                  
                }
            };
            
            popup = new PopupMenu();
            
            MenuItem showitem = new MenuItem("show");
            MenuItem hideitem = new MenuItem("hide");
            
            showitem.addActionListener(listener);
            hideitem.addActionListener(listener);
            
            popup.add(showitem);
            popup.add(hideitem);
           
            TrayIcon trayicon = new TrayIcon(image,"Remote Server",popup);
            trayicon.addActionListener(listener);
            
            
             try {
                tray.add(trayicon);
            } catch (AWTException ex) {
                Logger.getLogger(RemoteServerForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        ArrayList<String> ip = new ArrayList<>(); 
        ip = NetworkInter.getAllIp();
        for(String item : ip) {
             serveradapters.addItem(item);
        }
        ThreadContainer.setMulticast(new MultiCastStatus());
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startServerbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        passwordfield = new javax.swing.JTextField();
        serverlabel = new javax.swing.JLabel();
        serveradapters = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        startServerbtn.setText("Start Server");
        startServerbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startServerbtnActionPerformed(evt);
            }
        });

        jLabel1.setText("select network interface");

        jLabel2.setText("password");

        serverlabel.setForeground(new java.awt.Color(255, 0, 0));

        jMenu1.setText("File");

        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(passwordfield)
                    .addComponent(startServerbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(serverlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(serveradapters, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serveradapters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(startServerbtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serverlabel)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startServerbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startServerbtnActionPerformed
        
        if(!(passwordfield.getText().isEmpty())) {
            SocketConnection.setPassword(passwordfield.getText());
            Constants.setpasswordRequired(true);
        } else {
           SocketConnection.setPassword("");
           Constants.setpasswordRequired(false);
        }
            Constants.status = false;
            serverlabel.setText("*server started");
            startServerbtn.setEnabled(false);
            SocketConnection.setHost(serveradapters.getSelectedItem().toString());
            
            
            
           tempthread =  new Thread() {
                @Override
                public void run() {
                    try {
                        String multicast = "224.0.0.1";
                        int port = 5900;
                        int bufsize = 1024*4;
                        InetAddress group = InetAddress.getByName(multicast);
                        MulticastSocket s = new MulticastSocket(port);
                        s.joinGroup(group);
                        while(!Thread.currentThread().isInterrupted()) {
                            
                            byte[] buffer = new byte[bufsize];
                            s.receive(new DatagramPacket(buffer,bufsize,group,port));
                            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                            ObjectInputStream obin = new ObjectInputStream(bais);
                            Object ob = obin.readObject();
                            
                            if(ob instanceof MultiCast) {
                                MultiCast mc = (MultiCast)ob;
                                System.out.println(mc.toString());
                                if(!(mc.getIp().equals(InetAddress.getLocalHost().getHostAddress()))) {
                                    Constants.addNetworkMapKey(mc.getIp(), mc);
                                }
                            }
                        }
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(RemoteServerForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(RemoteServerForm.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        //interrupted!
                    }
                    
                }
            };
            tempthread.start();
            
            try {
            Thread.sleep(4000);
                } catch (InterruptedException ex) {
            Logger.getLogger(RemoteServerForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            tempthread.interrupt();
            
            setRandomAvailableport();
            
            StartServer.startServer();
            
            MenuItem serverstart = new MenuItem("Server listening on port "+Constants.getServerPort());
            
            popup.addSeparator();
            serverstart.setName("start");
            serverstart.addActionListener(listener);
            popup.add(serverstart);
            
            
            
        
    }//GEN-LAST:event_startServerbtnActionPerformed

    public static void startserver() {
         if(!(passwordfield.getText().isEmpty())) {
            SocketConnection.setPassword(passwordfield.getText());
            Constants.setpasswordRequired(true);
        } else {
           SocketConnection.setPassword("");
           Constants.setpasswordRequired(false);
        }
            
            serverlabel.setText("*server started");
            startServerbtn.setEnabled(false);
            SocketConnection.setHost(serveradapters.getSelectedItem().toString());
            
            
            
           tempthread =  new Thread() {
                @Override
                public void run() {
                    try {
                        String multicast = "224.0.0.1";
                        int port = 5900;
                        int bufsize = 1024*4;
                        InetAddress group = InetAddress.getByName(multicast);
                        MulticastSocket s = new MulticastSocket(port);
                        s.joinGroup(group);
                        while(!Thread.currentThread().isInterrupted()) {
                            
                            byte[] buffer = new byte[bufsize];
                            s.receive(new DatagramPacket(buffer,bufsize,group,port));
                            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                            ObjectInputStream obin = new ObjectInputStream(bais);
                            Object ob = obin.readObject();
                            
                            if(ob instanceof MultiCast) {
                                MultiCast mc = (MultiCast)ob;
                                System.out.println(mc.toString());
                                if(!(mc.getIp().equals(InetAddress.getLocalHost().getHostAddress()))) {
                                    Constants.addNetworkMapKey(mc.getIp(), mc);
                                }
                            }
                        }
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(RemoteServerForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(RemoteServerForm.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        //interrupted!
                    }
                    
                }
            };
            tempthread.start();
            
            try {
            Thread.sleep(4000);
                } catch (InterruptedException ex) {
            Logger.getLogger(RemoteServerForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            tempthread.interrupt();
            
            setRandomAvailableport();
            
            StartServer.startServer();
            Constants.status = false;
    }
    
    private static void setRandomAvailableport() {
        
        boolean isfound = true;
        while(isfound) {
            
            SocketView.setDefaultServerSocket();
            int port = SocketView.getServerSocketPort();
            
            Iterator<Entry<String,MultiCast>> it = Constants.getnetworkMap().entrySet().iterator();
            boolean same = false;
            while(it.hasNext()) {
                Entry<String,MultiCast> pair = it.next();
                if(pair.getValue().getPort() == port) {
                    same = true;
                }
            }
            if(same == false) {
                isfound = false;
                Constants.setServerPort(port);
            }
        }
        try {
            SocketView.closeServerSocket();
        } catch (IOException ex) {
            Logger.getLogger(RemoteServerForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
    
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private static javax.swing.JTextField passwordfield;
    public static javax.swing.JComboBox serveradapters;
    public static javax.swing.JLabel serverlabel;
    public static javax.swing.JButton startServerbtn;
    // End of variables declaration//GEN-END:variables
}

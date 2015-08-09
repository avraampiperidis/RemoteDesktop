package RemoteApp.view;


import RemoteApp.network.ClientChannelService;
import RemoteApp.Constants;
import RemoteApp.ThreadContainer;
import RemoteApp.model.serializables.ClientStatus;
import RemoteApp.model.serializables.ImageBlock;
import RemoteApp.model.serializables.MouseKeyboardStatus;
import RemoteApp.model.serializables.ScreenInfo;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import RemoteApp.Main;
import RemoteApp.model.serializables.MultiCast;



public class WindowScreen extends javax.swing.JFrame {

    private int width;
    private int height;
    private static Dimension dim;
    private static ScreenInfo screen;
    private static Robot robot;
    private String hostname;
    private int port;
    private ThreadContainer tc;
    private Constants con;
   
    public WindowScreen(final ScreenInfo screen,MultiCast mc,ThreadContainer tc,Constants c) {
        
        this.tc = tc;
        con = c;
        
        con.setDimension(new Dimension(screen.getFullWidth()+35,screen.getFullHeight()+65));
        initComponents();
        
        hostname = mc.getHostName();
        port = mc.getPort();
        
        width = screen.getFullWidth();
        height = screen.getFullHeight();
        
        WindowScreen.screen  = screen;
        
        dim = new Dimension(screen.getFullWidth(),screen.getFullHeight());
        
        Dimension thisScreen = Toolkit.getDefaultToolkit().getScreenSize();
        
        setSize(width+35,height+65);
        
        scrollpanelscreen.setPreferredSize(dim);
        scrollpanelscreen.setSize(screen.getFullWidth(),screen.getFullHeight());
        
        
        panel.setPreferredSize(dim);
        panel.setSize(screen.getFullWidth(),screen.getFullHeight());
        
        scrollpanelscreen.setViewportView(panel);
            
        
        tc.setMouseOverPannelAndStart(new Thread() {
            @Override
            public void run() {
                try {
                while(!Thread.currentThread().isInterrupted()) {
                    
                    Thread.sleep(500);
                    if(isMouseOverPanel() == true) {
                        con.setBlockImage(false);
                        int x = MouseInfo.getPointerInfo().getLocation().x - panel.getLocationOnScreen().x;
                        int y = MouseInfo.getPointerInfo().getLocation().y - panel.getLocationOnScreen().y;                           
                        MouseKeyboardStatus mks = new MouseKeyboardStatus(x,y,false,false,false,false,false,0,false,false,false,0);
                        ClientChannelService.sendMouseKeyboardStatusObject(mks);
                        
                    }
                  
                }
                } catch (InterruptedException ex) {
                     //myLogger(ex);
                 } finally {
                    //safe thread exit
               }
                
            }
        });
                
        
        
        panel.addMouseWheelListener(new MouseWheelListener() {            
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {               
               
                int notches = e.getWheelRotation();
                if (notches < 0) {
                    int x = MouseInfo.getPointerInfo().getLocation().x - panel.getLocationOnScreen().x;
                    int y = MouseInfo.getPointerInfo().getLocation().y - panel.getLocationOnScreen().y;                           
                    MouseKeyboardStatus mks = new MouseKeyboardStatus(x,y,false,false,false,true,false,-1,false,false,false,0);
                    ClientChannelService.sendMouseKeyboardStatusObject(mks);
                } else {
                    int x = MouseInfo.getPointerInfo().getLocation().x - panel.getLocationOnScreen().x;
                    int y = MouseInfo.getPointerInfo().getLocation().y - panel.getLocationOnScreen().y;                           
                    MouseKeyboardStatus mks = new MouseKeyboardStatus(x,y,false,false,false,true,false,1,false,false,false,0);
                    ClientChannelService.sendMouseKeyboardStatusObject(mks);
                }           
            }           
        });
        
        panel.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                    int x = MouseInfo.getPointerInfo().getLocation().x - panel.getLocationOnScreen().x;
                    int y = MouseInfo.getPointerInfo().getLocation().y - panel.getLocationOnScreen().y;                           
                    MouseKeyboardStatus mks = new MouseKeyboardStatus(x,y,false,false,false,false,false,1,true,false,false,0);
                    ClientChannelService.sendMouseKeyboardStatusObject(mks);            
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                    //do nothing!
            }
            
            

        });
        
        Main.setFinalThreadContainer(tc);
        
    }
    
    private static boolean isMouseOverPanel() {
        if(MouseInfo.getPointerInfo().getLocation().x >= panel.getLocationOnScreen().x && MouseInfo.getPointerInfo().getLocation().x <= panel.getLocationOnScreen().x + panel.getWidth()
                && MouseInfo.getPointerInfo().getLocation().y >= panel.getLocationOnScreen().y
                && MouseInfo.getPointerInfo().getLocation().y <= panel.getLocationOnScreen().y + panel.getHeight()) {
            return true;
        }
        return false;
    }
    
    private synchronized static Robot getRobot() {
        return robot;
    }
    
    
    public static void drawBlockOnPanel(final ImageBlock blockImage) {
      try {
        panel.getGraphics().drawImage(blockImage.getImage(), blockImage.getPoint().x, blockImage.getPoint().y, blockImage.getImage().getWidth(), blockImage.getImage().getHeight(), null);                   
        panel.validate();
      } catch(NullPointerException ex) {
          
      } catch (IOException ex) {
            Logger.getLogger(WindowScreen.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
          //do nothing
      }
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollpanelscreen = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        disconnectbtn = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 949, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );

        scrollpanelscreen.setViewportView(panel);

        jMenu1.setText("File");

        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        disconnectbtn.setText("disconnect");
        disconnectbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                disconnectbtnMouseClicked(evt);
            }
        });
        jMenuBar1.add(disconnectbtn);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollpanelscreen)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollpanelscreen)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseClicked
        if(SwingUtilities.isRightMouseButton(evt)) {
            int x = MouseInfo.getPointerInfo().getLocation().x - panel.getLocationOnScreen().x;
            int y = MouseInfo.getPointerInfo().getLocation().y - panel.getLocationOnScreen().y;                           
            MouseKeyboardStatus mks = new MouseKeyboardStatus(x,y,false,true,false,false,false,0,false,false,false,0);
            ClientChannelService.sendMouseKeyboardStatusObject(mks);
        } else if(SwingUtilities.isLeftMouseButton(evt)) {
            if(evt.getClickCount() == 2) {
                int x = MouseInfo.getPointerInfo().getLocation().x - panel.getLocationOnScreen().x;
                int y = MouseInfo.getPointerInfo().getLocation().y - panel.getLocationOnScreen().y;                           
                MouseKeyboardStatus mks = new MouseKeyboardStatus(x,y,false,false,false,false,true,0,false,false,false,0);
                ClientChannelService.sendMouseKeyboardStatusObject(mks);
            } else {
                int x = MouseInfo.getPointerInfo().getLocation().x - panel.getLocationOnScreen().x;
                int y = MouseInfo.getPointerInfo().getLocation().y - panel.getLocationOnScreen().y;                           
                MouseKeyboardStatus mks = new MouseKeyboardStatus(x,y,true,false,false,false,false,0,false,false,false,0);
                ClientChannelService.sendMouseKeyboardStatusObject(mks);
            }
        } else if(SwingUtilities.isMiddleMouseButton(evt)) {
            System.out.println("middle");
            int x = MouseInfo.getPointerInfo().getLocation().x - panel.getLocationOnScreen().x;
            int y = MouseInfo.getPointerInfo().getLocation().y - panel.getLocationOnScreen().y;                           
            MouseKeyboardStatus mks = new MouseKeyboardStatus(x,y,false,false,true,false,false,0,false,false,false,0);
            ClientChannelService.sendMouseKeyboardStatusObject(mks);
        }
        
        
    }//GEN-LAST:event_panelMouseClicked

    private void panelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseReleased
        int x = MouseInfo.getPointerInfo().getLocation().x - panel.getLocationOnScreen().x;
        int y = MouseInfo.getPointerInfo().getLocation().y - panel.getLocationOnScreen().y;                           
        MouseKeyboardStatus mks = new MouseKeyboardStatus(x,y,false,false,false,false,false,0,false,true,false,0);
        ClientChannelService.sendMouseKeyboardStatusObject(mks);
    }//GEN-LAST:event_panelMouseReleased

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        int x = MouseInfo.getPointerInfo().getLocation().x - panel.getLocationOnScreen().x;
        int y = MouseInfo.getPointerInfo().getLocation().y - panel.getLocationOnScreen().y;                           
        MouseKeyboardStatus mks = new MouseKeyboardStatus(x,y,false,false,false,false,false,0,false,false,true,evt.getKeyCode());
        ClientChannelService.sendMouseKeyboardStatusObject(mks);
        
    }//GEN-LAST:event_formKeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void disconnectbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disconnectbtnMouseClicked
              
       
       for(MouseMotionListener ls : panel.getMouseMotionListeners()) {
           panel.removeMouseMotionListener(ls);
       }
       
       for(MouseWheelListener ls : panel.getMouseWheelListeners()) {
           panel.removeMouseWheelListener(ls);
       }
        
       tc.getMouseOverPanelThread().interrupt();
       
      
        ClientStatus disconnectClient = new ClientStatus(true,"",false,false,hostname,null);
        ClientChannelService.sendClientStatus(disconnectClient);
       
       
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(WindowScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       tc.getConnectToServerThread().interrupt();
       tc.getclientChannelServiceThread().interrupt();
       
       con.setClientChannelService(false);
       
       
       this.dispose();
       Constants.isConnected = false;
       
        
    }//GEN-LAST:event_disconnectbtnMouseClicked

    
    public static void removeListenersFromPanel() {
        for(MouseMotionListener ls : panel.getMouseMotionListeners()) {
           panel.removeMouseMotionListener(ls);
       }
       
       for(MouseWheelListener ls : panel.getMouseWheelListeners()) {
           panel.removeMouseWheelListener(ls);
       }
    }
    
   
    
    
    public static void windowsScreen(final ScreenInfo screen,final MultiCast mc,final ThreadContainer tc,final Constants c) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WindowScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
               new WindowScreen(screen,mc,tc,c).setVisible(true);
               
            }
        });
        
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {               
                
                    
                    for(MouseMotionListener ls : panel.getMouseMotionListeners()) {
                    panel.removeMouseMotionListener(ls);
                    }
       
                    for(MouseWheelListener ls : panel.getMouseWheelListeners()) {
                    panel.removeMouseWheelListener(ls);
                    }
                    
                    tc.getMouseOverPanelThread().interrupt();
                      
                    ClientStatus disconnectClient = new ClientStatus(true,"",false,false,mc.getHostName(),null);
                    ClientChannelService.sendClientStatus(disconnectClient);
                  
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(WindowScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
                tc.getConnectToServerThread().interrupt();
                tc.getclientChannelServiceThread().interrupt();
       
                c.setClientChannelService(false);
                Constants.isConnected = false;
                
            }
        });
        
        
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu disconnectbtn;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private static javax.swing.JPanel panel;
    private static javax.swing.JScrollPane scrollpanelscreen;
    // End of variables declaration//GEN-END:variables

    

}

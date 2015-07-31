package RemoteApp;

import java.net.SocketException;
import java.util.logging.Level;
import RemoteApp.view.RemoteServerForm;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;


public class Main {
     
    private static final Logger logger = Logger.getLogger("Main");
    
    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {   
                
                try {   
                    new RemoteServerForm().setVisible(true);
                } catch (SocketException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        
        
    }
    
}

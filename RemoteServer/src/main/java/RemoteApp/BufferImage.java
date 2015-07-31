package RemoteApp;


import java.awt.*;
import java.awt.image.BufferedImage;



public class BufferImage implements Runnable {


    private static BufferedImage image;



    @Override
    public void run() {

        synchronized (this) {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle rect = new Rectangle(0, 0, (int) dim.getWidth(), (int) dim.getHeight());
            try {
                while (Constants.isbufferimage()) {
                          
                    image = new Robot().createScreenCapture(rect);
                    
                    Thread.sleep(700);
                }
             } catch (InterruptedException | AWTException e) {
                 
             } finally {
                
            }

        }

    }



    public static synchronized BufferedImage getImage() {
        return image;
    }

    public static synchronized BufferedImage getStartUpScrenShot() throws AWTException {
        
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle rect = new Rectangle(0, 0, (int) dim.getWidth(), (int) dim.getHeight());
            BufferedImage screenshotimage = new Robot().createScreenCapture(rect);
            return screenshotimage;
         
    }
    
    

}

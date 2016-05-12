package RemoteApp;


import java.awt.*;
import java.awt.image.BufferedImage;



public class BufferImage implements Runnable {


    private static BufferedImage image;



    @Override
    public void run() {

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle rect = new Rectangle(0, 0, (int) dim.getWidth(), (int) dim.getHeight());
            try {
                while (Constants.isbufferimage()) {
                          
                    image = new Robot().createScreenCapture(rect);
                    
                    Thread.sleep(350);
                }
             } catch (InterruptedException | AWTException e) {
                 
             } finally {
                
            }

    }



    public static  BufferedImage getImage() {
        return image;
    }

    public static  BufferedImage getStartUpScrenShot() throws AWTException {
        
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle rect = new Rectangle(0, 0, (int) dim.getWidth(), (int) dim.getHeight());
            BufferedImage screenshotimage = new Robot().createScreenCapture(rect);
            return screenshotimage;
         
    }
    
    

}

package RemoteApp;


import RemoteApp.network.ServerChannelService;
import RemoteApp.model.ImageChange;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ScreenArea implements   Runnable  {

    private int width;
    private int height;
    private int block;
    private Dimension screenblock;
    private Point point;
    private Rectangle rectangle;

    private BufferedImage bufferedImage;
    private BufferedImage tempimage;
    


    public ScreenArea(Dimension dimension, int block) {
        synchronized (this) {

            width = (int) dimension.getWidth() / Constants.getScreenDivider();
            height = (int) dimension.getHeight() / Constants.getScreenDivider();
            screenblock = new Dimension(width, height);

            this.block = block;

        }
    }


    public synchronized void setBlock(int w, int h) {

        point = new Point(w, h);
        rectangle = new Rectangle(point, screenblock);

    }

    public synchronized int getWidth() {
        return width;
    }

    public synchronized int getHeight() {
        return height;
    }

    private synchronized Rectangle getRectangle() {
        return rectangle;
    }

    public synchronized Point  getPoint() {
        return point;
    }



    @Override
    public void run() {
        
            try {               
                Thread.sleep(1200);
                bufferedImage = cropImage(BufferImage.getImage(),getRectangle());
                
                while(Constants.isScreenArea()) {
                    Thread.sleep(750);

                    tempimage = cropImage(BufferImage.getImage(), getRectangle());
                    if(comparebuffereimg(bufferedImage, tempimage)) {
                            
                    } else {                      
                                bufferedImage = tempimage;
                                ImageChange image = new ImageChange(tempimage,getPoint());
                                ServerChannelService.sendImage(image);         
                                
                    }

                }

            } catch (InterruptedException ex) {
                Logger.getLogger(ScreenArea.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                
            }

        

    }

    private synchronized   BufferedImage cropImage(BufferedImage img,Rectangle rect) {
        BufferedImage temp = img.getSubimage(rectangle.x,rectangle.y,rect.width,rect.height);
        return temp;
    }


    private synchronized int getBlock() {
        return block;
    }


    private synchronized boolean comparebuffereimg(BufferedImage img,BufferedImage img2) {


        if(img.getWidth() == img2.getWidth() && img.getHeight() == img2.getHeight()) {
            for(int x =0; x < img.getWidth(); x++) {
                for(int y=0; y < img.getHeight(); y++) {
                    if(img.getRGB(x,y) != img2.getRGB(x,y)) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }

        return true;

    }




}

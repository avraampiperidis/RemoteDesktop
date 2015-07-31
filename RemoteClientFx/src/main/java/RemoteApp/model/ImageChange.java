package RemoteApp.model;



import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ImageChange   {

    private SerializableBufferedImage image;
    private Point point;
    


    public ImageChange(BufferedImage image, Point point) {
        synchronized (this) {
            this.point = point;

        }
    }

    public void setBytes(byte[] bytes) {
        this.image = new SerializableBufferedImage(bytes);
    }

    public  BufferedImage getImage() {
        try {
            return image.get();
        } catch (IOException ex) {
            Logger.getLogger(ImageChange.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public  Point getPoint() {
        return point;
    }
    
    @Override
    public String toString() {
        return getClass().getName()+"[point x=" + point.x + " point y=" + point.y+"]";
    }


}

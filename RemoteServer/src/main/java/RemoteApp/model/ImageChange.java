
package RemoteApp.model;

import RemoteApp.model.serializables.SerializableBufferedImage;
import java.awt.*;
import java.awt.image.BufferedImage;


public class ImageChange  {

    private SerializableBufferedImage image;
    private Point point;


    public ImageChange(BufferedImage image, Point point) {
        synchronized (this) {
            this.image = new SerializableBufferedImage(image);
            this.point = point;

        }
    }


    public  BufferedImage getImage() {
        return image.get();
    }

    public  Point getPoint() {
        return point;
    }
    
    @Override
    public String toString() {
        return getClass().getName()+"[point x=" + point.x + " point y=" + point.y+"]";
    }


}

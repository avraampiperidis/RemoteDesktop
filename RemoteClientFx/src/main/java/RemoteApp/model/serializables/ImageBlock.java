
package RemoteApp.model.serializables;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;


public class ImageBlock implements Serializable {
    
    private transient BufferedImage image = null;
    private Point point;
    private byte[] bytes;
    
    public ImageBlock(byte[] bytes,Point p) {
        this.bytes = bytes;
        point = p;
    }
    
    public BufferedImage getImage() throws IOException {
        InputStream in = new ByteArrayInputStream(bytes);
        image =  ImageIO.read(in);
        return image;
    }
   
    
     public Point getPoint() {
        return point;
    }
   
    
}


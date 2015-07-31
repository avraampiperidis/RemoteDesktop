package RemoteApp.model;




import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;



public class SerializableBufferedImage   {
    
    
   private static final long serialVersionUID = 1L;

		private transient BufferedImage image = null;

		
		private byte[] imageinbytes;

		
		public SerializableBufferedImage(byte[] bytes)
			{
				imageinbytes = bytes;
			}

		
		
		public BufferedImage get() throws IOException
			{
				InputStream in = new ByteArrayInputStream(imageinbytes);
                                image = ImageIO.read(in);
				return image;
			}

		
    
}

package RemoteApp.model.serializables;

import java.awt.image.BufferedImage;
import java.io.Serializable;


public class SerializableBufferedImage implements Serializable {
    
    
   private static final long serialVersionUID = 1L;

		private transient BufferedImage image = null;

		private int imageWidth;
		private int imageHeight;
		private int[][] pixelArray;

		
		public SerializableBufferedImage(BufferedImage image)
			{
				set(image);
			}

		
		public void set(BufferedImage image)
			{
				this.image = image;

				imageWidth = image.getWidth();
				imageHeight = image.getHeight();

				pixelArray = new int[imageWidth][imageHeight];

				setPixelArray();
			}

		
		public BufferedImage get()
			{
				if (image == null)
					{
						image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
						for (int x = 0; x < imageWidth; x++)
							for (int y = 0; y < imageHeight; y++)
								image.setRGB(x, y, pixelArray[x][y]);
					}
				return image;
			}

		private void setPixelArray()
			{
				for (int x = 0; x < imageWidth; x++)
					for (int y = 0; y < imageHeight; y++)
						pixelArray[x][y] = image.getRGB(x, y);
			}

    
    
}

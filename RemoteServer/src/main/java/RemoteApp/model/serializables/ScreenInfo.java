package RemoteApp.model.serializables;



import java.io.Serializable;


public class ScreenInfo implements Serializable {

    private static final long serialVersionUID = 7526471155622776147L;
    
    private final int fullwidth;
    private final int fullheight;
    private final int blockwidth;
    private final int blockheight;

    public ScreenInfo(int fullw,int fullh,int blockw,int blockh) {
        fullwidth = fullw;
        fullheight = fullh;
        blockwidth = blockw;
        blockheight = blockh;
    }
    
    public int getFullWidth() {
        return fullwidth;
    }
    
    public int getFullHeight() {
        return fullheight;
    }
    
    public int getBlockWidth() {
        return blockwidth;
    }
    
    public int getBlockHeight() {
        return blockheight;
    }

    
    
    @Override
   public String toString() {
        return getClass().getName()+"[fullwidth="+fullwidth + ",fullheight="+fullheight+",blockwidth="+blockwidth+",blockheight="+blockheight+"]"; 
    }



}

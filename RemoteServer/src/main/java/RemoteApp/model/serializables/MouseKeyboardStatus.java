package RemoteApp.model.serializables;


import java.io.Serializable;


public class MouseKeyboardStatus implements Serializable {


    private int mousex;
    private int mousey;
    private boolean leftclick;
    private boolean rightclick;
    private boolean wheelclick;
    private boolean wheelscroll;
    private boolean doubleclick;
    private int WHEELROTATION;
    private boolean mousepressed;
    private boolean mousereleashed;
    private boolean keyevent;
    private int keycode;
    
    
    public MouseKeyboardStatus(int x,int y,boolean lclick,boolean rclick,boolean whclick,boolean whscroll,boolean dbclick,int wheelrot,boolean press,boolean releash,boolean key,int code) {
        synchronized (this) {
            mousex = x;
            mousey = y;
            
            leftclick = lclick;
            rightclick = rclick;
            wheelclick = whclick;
            wheelscroll = whscroll;
            doubleclick = dbclick;
            WHEELROTATION = wheelrot;
            mousepressed = press;
            mousereleashed = releash;
            keyevent = key;
            keycode = code;
        }
    }
    
    
    public synchronized int getx() {
        return mousex;
    }
    
    public synchronized int gety() {
        return mousey;
    }

    public boolean isLeftClicked() {
        return leftclick;
    }
    
    public boolean isRightClick() {
        return rightclick;
    }
    
    public boolean isWheelClick() {
        return wheelclick;
    }
    
    public boolean isWheelScrolled() {
        return wheelscroll;
    }
    
    public boolean isDoubleClicked() {
        return doubleclick;
    }
    
    public int getWHEELROTATION() {
        return WHEELROTATION;
    }
    
    public boolean isPressed() {
        return mousepressed;
    }
    
    public boolean isReleashed() {
        return mousereleashed;
    }

    public boolean isKeyEvent() {
        return keyevent;
    }
    
    public int getKeyCode() {
        return keycode;
    }
    
    
}

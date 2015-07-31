
package RemoteApp;

import com.sun.glass.events.KeyEvent;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import RemoteApp.model.serializables.MouseKeyboardStatus;
import RemoteApp.network.ServerChannelService;


public class MouseKeyboardFunctions {

    private static Robot robot;
    
    static {
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(MouseKeyboardFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean isMouseXYWithinBounds(int x, int y) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        if (x > dim.getWidth()) {
            return false;
        }
        if (y > dim.getHeight()) {
            return false;
        }
        return true;
    }

    public static synchronized void setMouseKeyboartStatusonScreenEvents(MouseKeyboardStatus mks) {
        if (isMouseXYWithinBounds(mks.getx(), mks.gety()) == true) {
            movemouse(mks.getx(), mks.gety());
            if (mks.isLeftClicked()) {
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
            }
            if (mks.isRightClick()) {
                robot.mousePress(InputEvent.BUTTON3_MASK);
                robot.mouseRelease(InputEvent.BUTTON3_MASK);
            }
            if (mks.isDoubleClicked()) {
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ServerChannelService.class.getName()).log(Level.SEVERE, null, ex);
                }
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
            }
            if (mks.isWheelClick()) {
                robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
            }
            if (mks.isWheelScrolled()) {
                if (mks.getWHEELROTATION() > 0) {
                    robot.mouseWheel(1);
                }
                if (mks.getWHEELROTATION() < 0) {
                    robot.mouseWheel(-1);
                }
            }
            if (mks.isPressed()) {
                robot.mousePress(InputEvent.BUTTON1_MASK);
            }
            if (mks.isReleashed()) {
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
            }
            if (mks.isKeyEvent()) {
                if (ServerChannelService.shift == false) {
                    if (mks.getKeyCode() == 16) {
                        ServerChannelService.shift = true;
                    } else {
                        robot.keyPress(mks.getKeyCode());
                        robot.keyRelease(mks.getKeyCode());
                    }
                } else {
                    ServerChannelService.shift = false;
                    typeSpecIalChar(mks.getKeyCode());
                }
            }
        }
    }

    private static void movemouse(int x, int y) {
        robot.mouseMove(x, y);
    }
    
    
    private static void typeSpecIalChar(int key) {
        
        if(key == 49) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_1);
            robot.keyRelease(KeyEvent.VK_1);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if(key == 50) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_2);
            robot.keyRelease(KeyEvent.VK_2);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if(key == 51) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_3);
            robot.keyRelease(KeyEvent.VK_3);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if(key == 52) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_4);
            robot.keyRelease(KeyEvent.VK_4);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if(key == 53) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_5);
            robot.keyRelease(KeyEvent.VK_5);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if(key == 54) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_6);
            robot.keyRelease(KeyEvent.VK_6);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if(key == 55) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_7);
            robot.keyRelease(KeyEvent.VK_7);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if(key == 56) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_8);
            robot.keyRelease(KeyEvent.VK_8);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if(key == 57) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_9);
            robot.keyRelease(KeyEvent.VK_9);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if(key == 48) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_0);
            robot.keyRelease(KeyEvent.VK_0);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if(key == 45) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_MINUS);
            robot.keyRelease(KeyEvent.VK_MINUS);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if(key == 61) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_EQUALS);
            robot.keyRelease(KeyEvent.VK_EQUALS);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if(key == 91) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
            robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if(key == 93) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
            robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if(key == 222) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_QUOTE);
            robot.keyRelease(KeyEvent.VK_QUOTE);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if(key == 59) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_SEMICOLON);
            robot.keyRelease(KeyEvent.VK_SEMICOLON);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if(key == 92) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_BACK_SLASH);
            robot.keyRelease(KeyEvent.VK_BACK_SLASH);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if(key == 47) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_SLASH);
            robot.keyRelease(KeyEvent.VK_SLASH);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if(key == 44) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_COMMA);
            robot.keyRelease(KeyEvent.VK_COMMA);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if(key == 46) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_PERIOD);
            robot.keyRelease(KeyEvent.VK_PERIOD);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        }
        
    }
    
    
    
    
}

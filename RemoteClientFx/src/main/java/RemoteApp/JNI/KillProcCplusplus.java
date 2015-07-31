
package RemoteApp.JNI;

import java.lang.management.ManagementFactory;


public class KillProcCplusplus {
    
    static String id;
    
    static {
        id = ManagementFactory.getRuntimeMXBean().getName();
        try {
        System.loadLibrary("RemoteClientFx");
        } catch(UnsatisfiedLinkError  ex) {
            
        } finally {
            
        }
    }
    
    private static native void killJavaProcess(int pid);
    
    public static void killJavaProcessJni() {
        try {
        killJavaProcess(Integer.valueOf(id.substring(0,id.indexOf("@"))));
        } catch(UnsatisfiedLinkError ex) {
            
        } finally {
            
        }
        
    }
    
    
    
    
}

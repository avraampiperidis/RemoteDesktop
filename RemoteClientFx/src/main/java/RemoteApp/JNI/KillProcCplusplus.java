
package RemoteApp.JNI;

import java.lang.management.ManagementFactory;


public class KillProcCplusplus {
    
    static String id;
    
    static {
        id = ManagementFactory.getRuntimeMXBean().getName();
        System.loadLibrary("RemoteClientFx");
    }
    
    private static native void killJavaProcess(int pid);
    
    public static void killJavaProcessJni() {
        
        killJavaProcess(Integer.valueOf(id.substring(0,id.indexOf("@"))));
        
        
    }
    
    
    
    
}

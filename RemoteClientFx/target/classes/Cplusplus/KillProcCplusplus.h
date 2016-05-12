#include <jni.h>

#ifndef _Included_KillProcCplusplus
#define _Included_KillProcCplusplus
#ifdef __cplusplus
extern "C" {
#endif
	/*
	* Class:     KillProcCplusplus
	* Method:    killJavaProcess
	* Signature: (jint i)V
	*/
	JNIEXPORT void JNICALL Java_RemoteApp_JNI_KillProcCplusplus_killJavaProcess(JNIEnv *, jobject,jint pid);

#ifdef __cplusplus
}
#endif
#endif
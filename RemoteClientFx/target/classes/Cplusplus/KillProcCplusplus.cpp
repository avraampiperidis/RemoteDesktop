
#include <jni.h>
#include "KillProcCplusplus.h"
#include <windows.h>

using namespace std;


JNIEXPORT void JNICALL Java_RemoteApp_JNI_KillProcCplusplus_killJavaProcess(JNIEnv *env, jobject obj, jint pid)
{

	HANDLE tmpHandle = OpenProcess(PROCESS_ALL_ACCESS, TRUE, pid);
	if (NULL != tmpHandle)
	{
		TerminateProcess(tmpHandle, 0);
	}

}
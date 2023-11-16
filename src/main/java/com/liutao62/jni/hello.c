#include <jni.h>
#include "com_liutao62_jni_HelloNative.h"

JNIEXPORT void JNICALL Java_Hello_sayHello (JNIEnv *env, jobject obj) {
    printf("Hello World! Java_Hello_sayHello\n");
}
#include "com_webagesolutions_jni_Multiplier.h"

JNIEXPORT jint JNICALL Java_com_webagesolutions_jni_Multiplier_multiply
(JNIEnv *env, jclass cls, jint a, jint b) {
  return a*b;
}

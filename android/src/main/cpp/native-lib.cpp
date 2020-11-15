//
// Created by QITMAC0000562 on 2019-08-29.
//
#include <jni.h>
#include <string>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <android/log.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_android_xknowledge_ndk_NativeLib_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


int8_t *m_ptr;
int32_t m_size;
int m_fd;

extern "C"
JNIEXPORT void JNICALL
Java_com_android_xknowledge_ndk_NativeLib_writeTest(JNIEnv *env, jobject instance) {

    std::string file = "/sdcard/a.txt";

    //打开文件
    m_fd = open(file.c_str(), O_RDWR | O_CREAT, S_IRWXU);

    //获得一页内存大小
    //Linux采用了分页来管理内存，即内存的管理中，内存是以页为单位,一般的32位系统一页为 4096个字节
    m_size = getpagesize();


    //将文件设置位 m_size这么大
    ftruncate(m_fd, m_size); // 100  1000 10000

    // m_size:映射区的长度。 需要是整数页个字节    byte[]
    m_ptr = (int8_t *) mmap(0, m_size, PROT_READ | PROT_WRITE, MAP_SHARED, m_fd,0);

    std::string data("asdsadasdasd");
    //将 data 的 data.size() 个数据 拷贝到 m_ptr
    memcpy(m_ptr, data.data(), data.size());

//    __android_log_print(ANDROID_LOG_ERROR, "mmap", "写入数据:%s", data.c_str());
}

extern "C"
JNIEXPORT void JNICALL
Java_com_android_xknowledge_ndk_NativeLib_readTest(JNIEnv *env, jobject instance) {
    //申请内存
    char *buf = static_cast<char *>(malloc(100));

    memcpy(buf, m_ptr, 100);

    std::string result(buf);
//    __android_log_print(ANDROID_LOG_ERROR, "MMKV", "读取数据:%s", result.c_str());

    //取消映射
    munmap(m_ptr, m_size);
    //关闭文件
    close(m_fd);
}
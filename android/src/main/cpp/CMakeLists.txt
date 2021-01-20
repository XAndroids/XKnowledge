# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.
#设置cmake要求饿最低版本号
cmake_minimum_required(VERSION 3.4.1)

#开启输出详细和链接信息
set(CMAKE_VERBOSE_MAKEFILE on)
message(STATUS "要打印的信息")
set(DIR ${CMAKE_SOURCE_DIR}/../jniLibs)
message(STATUS ${DIR})

aux_source_directory(. SOURCE)
message(STATUS ${SOURCE})


include_directories(ffmpeg)
include_directories(breakpad/src breakpad/src/common/android/include)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
#将native-lib.cpp编译到共享对象库中，并将其命名为libnative-lib.so，随后打包到apk中
#第一个参数，决定最终生成的共享库名称
#第二个参数，指定根据源文件斌阿姨出来是静态库，还是共享库，分别是STATIC/SHARED关键字
#第三个参数，指定源文件
add_library( # Sets the name of the library.
        native-lib

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        ${SOURCE})

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.
#指定系统库中搜索你需要的log库
#第一个参数：和Android NDK某个库建立关联关系
#第二个参数：Android NDK对应的库名称
find_library( # Sets the name of the path variable.
        log-lib
        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

#添加自己编译的ffmped库的AVUTILS so包
#add_library(AVUTILS SHARED IMPORTED)
#More tha#n one file was found with OS independent path 'lib/armeabi-v7a/libdlib.so'.
#移动至新文件夹cmakeLibs
#参考：https://stackoverflow.com/questions/62088079/error-when-building-project-with-ndk-support-after-updating-to-android-studio-4
#set_target_properties(AVUTILS PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/../cmakeLibs/${ANDROID_ABI}/libavutil.so)

set(libs ${CMAKE_SOURCE_DIR}/../cmakeLibs/${ANDROID_ABI}/)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -L${libs}")

#增加导入/breakpad/CMakeLists.txt
add_subdirectory(breakpad)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
#把NDK库和我们自己的原生库进行关联
target_link_libraries( # Specifies the target library.
        native-lib
        avfilter avformat avcodec avutil swresample swscale rtmp android z
        OpenSLES
        breakpad
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})
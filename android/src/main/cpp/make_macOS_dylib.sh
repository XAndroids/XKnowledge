#!/usr/bin/env bash
# 指定动态库名称（即cpp文件名）
name=native-lib

# 指定cpp目录
INTPUT=.
# 指定dylib输出目录
OUTPUT=../../../build/dylibs
mkdir -p ${OUTPUT}

# cpp编译成.o file
cc -c \
-I$JAVA_HOME/include/darwin \
-I$JAVA_HOME/include/ \
${INTPUT}/${name}.cpp \
-o ${OUTPUT}/lib${name}.o

# .o编译成.dylib file
g++ -dynamiclib -undefined suppress -flat_namespace ${OUTPUT}/*.o -o ${OUTPUT}/lib${name}.dylib

echo "生成dylib："${OUTPUT}/lib${name}.dylib
# androidStuff
Compile OpenSSL for android  : 


1) Download Openssl and extract 
2)export ANDROID_NDK_HOME=/some/where/android-sdk/ndk/20....
    PATH=$ANDROID_NDK_HOME/toolchains/llvm/prebuilt/linux-x86_64/bin:$PATH
    ./Configure android-arm64 -D__ANDROID_API__=21
   make SHLIB_VERSION_NUMBER= SHLIB_EXT=.so  ( this is to make .so file without a version 

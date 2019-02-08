package org.bytedeco.javacpp.presets;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;
import org.bytedeco.javacpp.tools.*;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;
import org.bytedeco.javacpp.annotation.Platform;

@Properties(
    value = @Platform(
        includepath = {"/usr/include/"},
        //preloadpath = {"/path/to/deps/"},
        linkpath = {"/usr/lib/x86_64-linux-gnu/"},
        cinclude={"videodev2.h"},
        include = {"libv4l2.h"},

        //preload = {"DependentLib"},
        link = {"v4l2"}
    ),
    target = "org.bytedeco.javacpp.v4l2"
)
public class v4l2 implements InfoMapper {
    public void map(InfoMap infoMap) {
        //infoMap.put(new Info("const char").valueTypes("byte").pointerTypes("@Cast(\"const char*\") BytePointer", "String"));
        //infoMap.put(new Info("__attribute__").skip());
        //infoMap.put(new Info("v4l2_rds_data").skip());
        //infoMap.put(new Info("tst").cppTypes().skip());
        //infoMap.put(new Info("videodev2.h").linePatterns("} ", ";").skip());
        //infoMap.put(new Info("__attribute__").cppText("#define __attribute__((packed)) ;"));
        //infoMap.put(new Info("__attribute__ ((packed))").cppTypes().annotations());
         //infoMap.put(new Info("((packed))").cast().valueTypes("int"));
         //infoMap.put(new Info("__attribute__ ((packed))").pointerTypes("IntList").define());
         //infoMap.put(new Info("__attribute__").valueTypes("@Cast(\"v4l2_buf_type\") int"));
        
         infoMap.put(new Info("__attribute__").annotations());
         infoMap.put(new Info("__attribute__ ((packed))").annotations());
         infoMap.put(new Info("((packed))").annotations());

         
        infoMap.put(new Info("EXPORTS", "NOINLINE", "LIBV4L_PUBLIC").cppTypes().annotations());
        infoMap.put(new Info("basic/types").cppTypes("signed", "unsigned", "char", "short", "int", "long", "bool", "float", "double",
"_Bool", "_Complex", "_Imaginary", "complex", "imaginary"));
        infoMap.put(new Info("ssize_t", "ptrdiff_t", "intptr_t", "uintptr_t", "off_t").cast().valueTypes("long").pointerTypes("SizeTPointer"));
        infoMap.put(new Info("__u8", "__u16", "__uint16_t")
                       .cast().valueTypes("short").pointerTypes("ShortPointer", "ShortBuffer", "short[]"));
                       
        //infoMap.put(new Info ("...").pointerTypes("IntPointer", "IntBuffer", "int[]"));   
        //infoMap.put(new Info ("...").valueTypes("int").pointerTypes("IntPointer", "IntBuffer", "int[]", "Pointer"));   
        //infoMap.put(new Info("...").valueTypes("@Cast(\"unsigned long int\") long"));
        //infoMap.put(new Info("...").cppText("unsigned long int fmt"));
        infoMap.put(new Info("v4l2_ioctl").javaText("public static native int v4l2_ioctl(int fd, @Cast(\"unsigned long int\") long request, Pointer fmt); \n public static native int v4l2_ioctl(int fd, @Cast(\"unsigned long int\") long request, int fmt);"));

        infoMap.put(new Info("v4l2_mmap").javaText("public static native Pointer v4l2_mmap(Pointer start, @Cast(\"size_t\") long length, int prot, int flags, int fd, @Cast(\"int64_t\") long offset);"));
                       
        infoMap.put(new Info("socklen_t", "clockid_t", "useconds_t", "id_t", "gid_t", "uid_t", "pid_t", "mode_t",
                             "__socklen_t", "__clockid_t", "__useconds_t", "__id_t", "__gid_t", "__uid_t", "__pid_t", "__mode_t",
                             "error_t", "__u32", "__uint32_t", "__s32", "__le32")
                       .cast().valueTypes("int").pointerTypes("IntPointer", "IntBuffer", "int[]"));

         infoMap.put(new Info("clock_t", "dev_t", "off_t", "intptr_t", "rlim_t", "ssize_t",
                             "__clock_t", "__dev_t", "__off_t", "__intptr_t", "__rlim_t", "__ssize_t",
                             "__blkcnt_t", "__blksize_t", "__ino_t", "__nlink_t", "__time_t", "__timer_t", "__suseconds_t",
                             "__syscall_slong_t", "__syscall_ulong_t", "__CPU_MASK_TYPE", "__kernel_long_t", "__kernel_ulong_t")
                       .cast().valueTypes("long").pointerTypes("SizeTPointer"));

         infoMap.put(new Info("off64_t", "rlim64_t", "__off64_t", "__rlim64_t", "__blkcnt64_t", "greg_t", "__ino64_t", "__u64", "__uint64_t", "__s64")
.cast().valueTypes("long").pointerTypes("LongPointer", "LongBuffer", "long[]"));

};
        @Name("timeval")
    public static class timeval extends Pointer {
        
        static {
            Loader.load();
        }
        
        public timeval() {
            allocate();
        }
        
        public timeval(int size) {
            allocateArray(size);
        }
        
        public timeval(Pointer p) {
            super(p);
        }
        
        private native void allocate();
        private native void allocateArray(int size);

        public native long tv_sec(); public native timeval tv_sec (long tv_sec);
        public native long tv_usec(); public native timeval tv_usec(long tv_usec);
};
 

        @Name("timespec")
    public static class timespec extends Pointer {
        
        static {
            Loader.load();
        }
        
        public timespec() {
            allocate();
        }
        
        public timespec(int size) {
            allocateArray(size);
        }
        
        public timespec(Pointer p) {
            super(p);
        }
        
        private native void allocate();
        private native void allocateArray(int size);

        public native long tv_sec(); public native timespec tv_sec (long tv_sec);
        public native long tv_nsec(); public native timespec tv_nsec(long tv_nsec);
};

//         @Name("v4l2_rds_data")
//     public static class v4l2_rds_data extends Pointer {
//         
//         static {
//             Loader.load();
//         }
//         
//         public v4l2_rds_data() {
//             allocate();
//         }
//         
//         public v4l2_rds_data(int size) {
//             allocateArray(size);
//         }
//         
//         public v4l2_rds_data(Pointer p) {
//             super(p);
//         }
//         
//         private native void allocate();
//         private native void allocateArray(int size);
// 
//         public native short lsb(); public native v4l2_rds_data lsb (short lsb);
//         public native short msb(); public native v4l2_rds_data msb (short msb);
//         public native short block(); public native v4l2_rds_data block(short block);
// 
// };
    
}

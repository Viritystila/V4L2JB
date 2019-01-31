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
        include = {"libv4l2.h"},
        //preload = {"DependentLib"},
        link = {"v4l2"}
    ),
    target = "org.bytedeco.javacpp.v4l2"
)
public class v4l2 implements InfoMapper {
    public void map(InfoMap infoMap) {
        //infoMap.put(new Info("const char").valueTypes("byte").pointerTypes("@Cast(\"const char*\") BytePointer", "String"));
        infoMap.put(new Info("EXPORTS", "NOINLINE", "LIBV4L_PUBLIC").cppTypes().annotations());
        infoMap.put(new Info("basic/types").cppTypes("signed", "unsigned", "char", "short", "int", "long", "bool", "float", "double",
"_Bool", "_Complex", "_Imaginary", "complex", "imaginary"));
        infoMap.put(new Info("ssize_t", "ptrdiff_t", "intptr_t", "uintptr_t", "off_t").cast().valueTypes("long").pointerTypes("SizeTPointer"));
    }
}

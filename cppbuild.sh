#!/bin/bash
# This file is meant to be included by the parent cppbuild.sh script

if [[ -z "$PLATFORM" ]]; then
    pushd ..
    bash cppbuild.sh "$@" v4l2
    popd
    exit
fi

mkdir -p $PLATFORM/include
mkdir -p $PLATFORM/include/linux

mkdir -p $PLATFORM/lib
download https://raw.githubusercontent.com/torvalds/linux/master/include/uapi/linux/videodev2.h videodev2.h
wget -O compiler.h https://raw.githubusercontent.com/torvalds/linux/master/include/linux/compiler_types.h 
sed -i -e "s+__attribute__ ((packed))+ ;+g" videodev2.h
r="$(uname -r)"
#sed -i -e 's+linux/compiler.h+/usr/src/linux-headers-'$r'/include/linux/compiler.h+g' videodev2.h
#sed -i -e 's+linux/compiler.h+/home/mikael/compiler.h+g' videodev2.h

#cd $PLATFORM
cp videodev2.h $PLATFORM/include
cp compiler.h $PLATFORM/include/linux

#cp /usr/src/linux-headers-$r/include/linux/compiler.h $PLATFORM/include/linux


cd ../..

#!/bin/bash
# This file is meant to be included by the parent cppbuild.sh script

if [[ -z "$PLATFORM" ]]; then
    pushd ..
    bash cppbuild.sh "$@" v4l2
    popd
    exit
fi

mkdir -p $PLATFORM/include
mkdir -p $PLATFORM/lib
#cd $PLATFORM
cp ../videodev2.h $PLATFORM/include

cd ../..

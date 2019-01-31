#!/bin/bash
# This file is meant to be included by the parent cppbuild.sh script

if [[ -z "$PLATFORM" ]]; then
    pushd ..
    bash cppbuild.sh "$@" v4l2
    popd
    exit
fi

cd ../..

#!/bin/sh
export PATH=${axoloti_runtime}/platform_osx/bin:$PATH
echo "Compiling module... with ${axoloti_firmware}"
cd "${axoloti_firmware}"
make -f Makefile.module $1

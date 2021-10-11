# Build For Zedboard on "Ubuntu 18.04.6 LTS"

1. Install deps:
````
 sudo apt-get install gawk wget git-core diffstat unzip texinfo gcc-multilib \
     build-essential chrpath socat cpio python python3 python3-pip python3-pexpect \
     xz-utils debianutils iputils-ping libsdl1.2-dev xterm  gcc-arm-linux-gnueabi qemu \
      gcc-arm-linux-gnueabihf gcc-none-linux-gnueabi gcc-none-linux-gnueabi
````
2. `git submodule update --init --recursive`
3. `source ./oe-init-build-env`
4. In `morty/build/conf/local.conf`, comment out `MACHINE ??= "qemux86"` and add
    `MACHINE ??= "zedboard-zynq7"`
5. ADD `/home/lorenzogomez/morty/meta-xilinx` and  `/home/lorenzogomez/morty/meta-openembedded/meta-oe` to `bblayers.conf` file.
   Do note the full paths might be different on your machine.
6. cd to `morty`(root dir of repo) and run `make minimal-image`
   


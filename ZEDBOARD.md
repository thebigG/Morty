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
5. ADD `/home/lorenzogomez/morty/meta-xilinx` and  `/home/lorenzogomez/morty/meta-openembedded/meta-oe` to `/home/lgomez/morty/build/conf/bblayers.conf` file.
   In the end it should look something like this:
  ```
    # POKY_BBLAYERS_CONF_VERSION is increased each time build/conf/bblayers.conf
# changes incompatibly
POKY_BBLAYERS_CONF_VERSION = "2"

BBPATH = "${TOPDIR}"
BBFILES ?= ""

BBLAYERS ?= " \
  /home/lgomez/morty/meta \
  /home/lgomez/morty/meta-poky \
  /home/lgomez/morty/meta-yocto-bsp \
  /home/lgomez/morty/meta-xilinx \
  /home/lgomez/morty/meta-openembedded/meta-oe \ 
  "
   
   Do note the full paths might be different on your machine.
```


6. cd to `morty`(root dir of repo) and run `make minimal-image`
   
Notes:

# Development of custom layers:
```
  155  devtool deploy-target hellocmake qemu-zynq7
  156  devtool add recipe /home/lgomez/morty/meta-hello-cmake/
  157  devtool build hellocmake
  158  devtool add hellocmake /home/lgomez/morty/meta-hello-cmake/
  159  devtool build hellocmake
  160  devtool deploy-target hellocmake qemu-zynq7
  161  devtool build-image core-image-minimal
  162  devtool -h
  184  devtool build-image core-image-minimal
  192  devtool build-image core-image-minimal
  204  devtool build-image core-image-minimal
  205  devtool deploy-target hellocmake qemu-zynq7
  206  devtool build hellocmaker
  207  devtool build hellocmake
  208  devtool deploy-target hellocmake qemu-zynq7
  239  devtool build hellocmake
  241  devtool build
  242  devtool build -h
  243  MACHINE=qemu-zynq7 devtool build corecore-image-minimal 
  244  MACHINE=qemu-zynq7 devtool build core-image-minimal 
  245  MACHINE=qemu-zynq7 devtool build-image core-image-minimal 
  246  MACHINE=qemu-zynq7 devtool build hellocmake 
  247  devtool deploy-target hellocmake qemu-zynq7 
  277  devtool add hellocmake /home/lgomez/morty/meta-hello-cmake/
  278  devtool edit-recipe hellocmake
  300  MACHINE=qemu-zynq7 devtool build hellocmake 
  337  MACHINE=qemu-zynq7 devtool build hellocmake 
  359  MACHINE=qemu-zynq7 devtool build hellocmake 
  361  devtool deploy-target hellocmake qemu-zynq7
  362  devtool -s deploy-target hellocmake qemu-zynq7
  363  devtool  deploy-target hellocmake qemu-zynq7 -s
  364  devtool  deploy-target -s hellocmake qemu-zynq7 
  365  devtool  deploy-target -s hellocmake root@192.168.7.6:~
  372  devtool  deploy-target -s hellocmake root@192.168.7.6:~
  374  devtool  deploy-target -s hellocmake root@192.168.7.6
  375  devtool  deploy-target -s hellocmake 192.168.7.6
  457  devtool build-image core-image-minimal
  458  MACHINE=ACHINE=qemu-zynq7 devtool build-image core-image-minimal
  459  MACHINE=qemu-zynq7 devtool build-image core-image-minimal
  464  MACHINE=qemu-zynq7 devtool build hellocmake

```

# Files to copy to SD card:

These files are usually in `tmp/deploy/images/zedboard-zynq7` after building the image.
```
  Copy the following to FAT partition:
  ./tmp/deploy/images/zedboard-zynq7/boot.bin
  uImage 
  uEnv.txt
  uImage-zynq-zed.dtb 
  core-image-minimal-zedboard-zynq7-20211107015917.rootfs.cpio.gz.u-boot
  u-boot.img

  Untar core-image-minimal-zedboard-zynq7.tar.gz into the ext3 partition:
  core-image-minimal-zedboard-zynq7.tar.gz
```
# Loading the bit stream to zedboard:
Once it boots, you can use the following commands:

```
Assuming the bit stream is called "custom_ip.bit"

fatload mmc 0 ${loadbit_addr} custom_ip.bit
fpga loadb 0 ${loadbit_addr} $filesize
```

Another way of loading the bit stream via linux:  
`cat bitstream.bit > /dev/xdevcfg`  
To know the state after the FPGA load:
```
$ cat /sys/class/xdevcfg/xdevcfg/device/prog_done
1
```

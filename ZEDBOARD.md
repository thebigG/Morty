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

To rebuild cmake project without having to build the kernel again:
```
1. cd ~/morty/build/tmp/work/cortexa9hf-neon-poky-linux-gnueabi/recipes-gpio/1.0-r0/build
2. Pull repo(update sources)
3. Call make
```

Generating DTS(Device Tree Sources) files
```
rlwrap: warning: your $TERM is 'xterm-256color' but rlwrap couldn't find it in the terminfo database. Expect some problems.

****** Xilinx Software Commandline Tool (XSCT) v2016.2
  **** Build date : Jun  2 2016-16:54:05
    ** Copyright 1986-2016 Xilinx, Inc. All Rights Reserved.


xsct% hsi                                                                                                                                                                                                          
wrong # args: should be "hsi subcommand ?argument ...?"
xsct% hsi::get_cells * -filter {IP_TYPE==PROCESSOR}                                                                                                                                                                
ERROR: [Hsi 55-1593] Current Hardware Design is not set.
ERROR: [Hsi 55-1501] No Hardware designs opened. Open a hardware design first
ERROR: [Common 17-39] 'hsi::get_cells' failed due to earlier errors.

xsct% hsi open_hw_design /home/lgomez/hardware_output/                                                                                                                                                             
design_ps_pl_wrapper.bit  design_ps_pl_wrapper.hdf  ps7_init.c                ps7_init.h                ps7_init.html             ps7_init.tcl              ps7_init_gpl.c            ps7_init_gpl.h
xsct% hsi open_hw_design /home/lgomez/hardware_output/
design_ps_pl_wrapper.bit  design_ps_pl_wrapper.hdf  ps7_init.c                ps7_init.h                ps7_init.html             ps7_init.tcl              ps7_init_gpl.c            ps7_init_gpl.h
xsct% hsi open_hw_design /home/lgomez/hardware_output/design_ps_pl_wrapper.hdf 
design_ps_pl_wrapper                                                                                                                                                                                               
xsct% hsi::get_cells * -filter {IP_TYPE==PROCESSOR}                                                                                                                                                                
ps7_cortexa9_0 ps7_cortexa9_1
xsct% hsi set_repo_path /home/lgomez/device-tree-xlnx                                                                                                                                                              
INFO: [Hsi 55-1698] elapsed time for repository loading 0 seconds                                                                                                                                                  
xsct% hsi create_sw_design device-tree -os device_tree -proc ps7_cortexa9_0                                                                                                                                        
device-tree
xsct% hsi generate_target -dir my_dts                                                                                                                                                                              
WARNING: ps7_ethernet_0: No reset found                                                                                                                                                                            
WARNING: ps7_usb_0: No reset found                                                                                                                                                                                 
xsct% hsi close_hw_design                                                                                                                                                                                          
Display all 253 possibilities? (y or n)
xsct% hsi close_hw_design /home/lgomez/hardware_output/design_ps_pl_wrapper.hdf
WARNING: [Hsi 55-1568] The Hardware design name provided is not opened
ERROR: [Hsi 55-1448] Error: running current_hw_design.
ERROR: [Common 17-39] 'hsi::close_hw_design' failed due to earlier errors.

xsct% exit                                                                                                                                                                                                         
exit
```
```

To create a custom xilinx layer(this is super useful for custom things like customs device trees):
```
export LAYER=example # your layer name goes here
# From Yocto root directory
source setupsdk
# Optional: Make a backup copy of local.conf and bblayers.conf
cp conf/local.conf conf/local.conf.bk
cp conf/bblayers.conf conf/bblayers.conf.bk
# Add a default MACHINE in local.conf or specify on the command line for bitbake, e.g. MACHINE=zcu102-zynqmp
echo 'MACHINE ?= "zcu102-zynqmp"' >> conf/local.conf
# Create your layer in the sources directory
cd ../sources
bitbake-layers create-layer meta-$LAYER
# Create custom layer directory structure for Xilinx components
cd meta-$LAYER
mkdir -p conf/machine recipes-kernel/linux-xlnx recipes-bsp/u-boot/u-boot-xlnx recipes-bsp/device-tree/files \
recipes-bsp/hdf/files recipes-bsp/fsbl/files recipes-bsp/pmu-firmware/files
# Add new layer with bitbake-layers or edit bblayers.conf manually
cd ../../build
bitbake-layers add-layer ../sources/meta-$LAYER
```


```
ERROR: external-hdf-1.0-r0 do_fetch: Fetcher failure: Unable to find file file:///yocto/hw_config/design_ps_pl_wrapper.hdf anywhere. The paths that were searched were:
    /home/lgomez/morty/meta-xilinx-tools/recipes-bsp/hdf/external-hdf-1.0/poky
    /home/lgomez/morty/meta-xilinx-tools/recipes-bsp/hdf/external-hdf/poky
    /home/lgomez/morty/meta-xilinx-tools/recipes-bsp/hdf/files/poky
    /home/lgomez/morty/meta-xilinx-tools/recipes-bsp/hdf/external-hdf-1.0/zedboard-zynq7
    /home/lgomez/morty/meta-xilinx-tools/recipes-bsp/hdf/external-hdf/zedboard-zynq7
    /home/lgomez/morty/meta-xilinx-tools/recipes-bsp/hdf/files/zedboard-zynq7
    /home/lgomez/morty/meta-xilinx-tools/recipes-bsp/hdf/external-hdf-1.0/armv7a
    /home/lgomez/morty/meta-xilinx-tools/recipes-bsp/hdf/external-hdf/armv7a
    /home/lgomez/morty/meta-xilinx-tools/recipes-bsp/hdf/files/armv7a
    /home/lgomez/morty/meta-xilinx-tools/recipes-bsp/hdf/external-hdf-1.0/zynq
    /home/lgomez/morty/meta-xilinx-tools/recipes-bsp/hdf/external-hdf/zynq
    /home/lgomez/morty/meta-xilinx-tools/recipes-bsp/hdf/files/zynq
    /home/lgomez/morty/meta-xilinx-tools/recipes-bsp/hdf/external-hdf-1.0/arm
    /home/lgomez/morty/meta-xilinx-tools/recipes-bsp/hdf/external-hdf/arm
    /home/lgomez/morty/meta-xilinx-tools/recipes-bsp/hdf/files/arm
    /home/lgomez/morty/meta-xilinx-tools/recipes-bsp/hdf/external-hdf-1.0/
    /home/lgomez/morty/meta-xilinx-tools/recipes-bsp/hdf/external-hdf/
    /home/lgomez/morty/meta-xilinx-tools/recipes-bsp/hdf/files/
    /home/lgomez/morty/build/downloads
```

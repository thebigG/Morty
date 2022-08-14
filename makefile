#makefile tailored for zynq7 Zedboard
#Be sure to run oe-init-build-env script before running this makefile
#This file also serves as documentation in case I forget things

.ONESHELL:
.SHELLFLAGS += -e
BUILD_DIR   := build

qemu:
	@cd $(BUILD_DIR)
	@MACHINE=qemu-zynq7 bitbake core-image-minimal

qemu-sdk:
	@MACHINE=qemu-zynq7 bitbake core-image-minimal -c populate_sdk

qemu-layer:
	@cd $(BUILD_DIR)
	@MACHINE=qemu-zynq7 bitbake core-image-minimal

run-qemu:
	@cd  $(BUILD_DIR)
	@runqemu qemu-zynq7

clean:
	@cd $(BUILD_DIR)
	@MACHINE=qemu-zynq7 bitbake -c clean $(BB_RECIPE)

minimal-image:
	@cd $(BUILD_DIR)
	@bitbake core-image-minimal

boot-bin:
	@/opt/Xilinx/SDK/2016.2/bin/bootgen -w -image hw_config/zedboard.bif  -arch zynq -o build/tmp/deploy/images/zedboard-zynq7/boot.bin

#Useful for loading fpga with xdevcfg 
boot-bin-split:
	@/opt/Xilinx/SDK/2016.2/bin/bootgen -split bin -w -image hw_config/zedboard.bif  -arch zynq

#For the tftp server you need to add `/etc/default/tftp-hpa`:
#TFTP_USERNAME="lgomez"
#TFTP_DIRECTORY="/tftpboot"
#TFTP_ADDRESS="0.0.0.0:69"
#TFTP_OPTIONS="--secure"
#RUN_DAEMON="yes" # maybe no need for that
#OPTIONS="-l -s /var/lib/tftpboot"

#sudo apt-get install tftpd-hpa
tftp-server:
	sudo service tftpd-hpa start

#FILE_NAME is relative to the tftp server directory such as /tftpboot or /srv/tftp. Very useful for transfering files to board.
#NOTE:If running inside VM, make sure that your network is bridged.
tftp-client-copy:
	tftp -g -r $(FILE_NAME) $(SERVER_IP)

#Add meta-skeleton directory to make your life easier.
yocto-layer:
	yocto-layer create $(LAYER_NAME)

#makefile tailored for zynq7 Zedboard
#Be sure to run oe-init-build-env script before running this makefile
#This file also serves as documentation in case I forget things

.ONESHELL:
.SHELLFLAGS += -e
BUILD_DIR   := build 

qemu:
	@cd $(BUILD_DIR)
	@MACHINE=qemu-zynq7 bitbake core-image-minimal

qemu-layer:
	@cd $(BUILD_DIR)
	@MACHINE=qemu-zynq7 bitbake $(LAYER)

run-qemu:
	@cd  $(BUILD_DIR)
	@runqemu qemu-zynq7

clean:
	@cd $(BUILD_DIR)
	@MACHINE=qemu-zynq7 bitbake -c clean $(BB_RECIPE)

#For the tftp server you need to add `/etc/default/tftp-hpa`:
#TFTP_USERNAME="lgomez"
#TFTP_DIRECTORY="/tftpboot"
#TFTP_ADDRESS="0.0.0.0:69"
#TFTP_OPTIONS="--secure"
#RUN_DAEMON="yes" # maybe no need for that
#OPTIONS="-l -s /var/lib/tftpboot"

tftp-server:
	sudo service tftp-hpa start

#FILE_NAME is relative to the tftp server directory such as /tftpboot. Very useful for transfering files to board.
tftp-client-copy:
	tftp -g -r $(FILE_NAME) $(SERVER_IP)

#Add meta-skeleton directory to make your life easier. 
yocto-layer:
	yocto-layer create $(LAYER_NAME)
        

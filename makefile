.ONESHELL:
.SHELLFLAGS += -e
BUILD_DIR   := build 


qemu:
	@cd $(BUILD_DIR)
	@MACHINE=qemu-zynq7 bitbake core-image-minimal

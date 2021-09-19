#makefile tailored for zynq7 Zedboard
#Be sure to run oe-init-build-env script before running this makefile

.ONESHELL:
.SHELLFLAGS += -e
BUILD_DIR   := build 


qemu:
	@cd $(BUILD_DIR)
	@MACHINE=qemu-zynq7 bitbake core-image-minimal

run-qemu:
	@cd  $(BUILD_DIR)
	@runqemu qemu-zynq7

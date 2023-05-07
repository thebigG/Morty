SUMMARY = "Linux kernel Development Source"
DESCRIPTION = "Development source linux kernel. When built, this recipe packages the \
source of the preferred virtual/kernel provider and makes it available for full kernel \
development or external module builds"

SECTION = "kernel"

LICENSE = "GPLv2"

inherit linux-kernel-base

# Whilst not a module, this ensures we don't get multilib extended (which would make no sense)
inherit module-base

# We need the kernel to be staged (unpacked, patched and configured) before
# we can grab the source and make the source package. We also need the bits from
# ${B} not to change while we install, so virtual/kernel must finish do_compile.
do_install[depends] += "virtual/kernel:do_shared_workdir"
# Need the source, not just the output of populate_sysroot
do_install[depends] += "virtual/kernel:do_install"

# There's nothing to do here, except install the source where we can package it
do_fetch[noexec] = "1"
do_unpack[noexec] = "1"
do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"
deltask do_populate_sysroot

S = "${STAGING_KERNEL_DIR}"
B = "${STAGING_KERNEL_BUILDDIR}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

KERNEL_BUILD_ROOT="${nonarch_base_libdir}/modules/"

do_install() {
        kerneldir=${D}${KERNEL_SRC_PATH}
        install -d $kerneldir

        #
        # Copy the staging dir source (and module build support) into the devsrc structure.
        # We can keep this copy simple and take everything, since a we'll clean up any build
        # artifacts afterwards, and the extra i/o is not significant
        #
        cd ${B}
        find . -type d -name '.git*' -prune -o -path '.debug' -prune -o -type f -print0 | cpio --null -pdlu $kerneldir
        cd ${S}
	find . -type d -name '.git*' -prune -o -type d -name '.kernel-meta' -prune -o -type f -print0 | cpio --null -pdlu $kerneldir

        # Explicitly set KBUILD_OUTPUT to ensure that the image directory is cleaned and not
        # The main build artifacts. We clean the directory to avoid QA errors on mismatched
        # architecture (since scripts and helpers are native format).
        KBUILD_OUTPUT="$kerneldir"
        oe_runmake -C $kerneldir CC="${KERNEL_CC}" LD="${KERNEL_LD}" clean _mrproper_scripts
        # make clean generates an absolute path symlink called "source"
        # in $kerneldir points to $kerneldir, which doesn't make any
        # sense, so remove it.
        if [ -L $kerneldir/source ]; then
            bbnote "Removing $kerneldir/source symlink"
            rm -f $kerneldir/source
        fi

        # As of Linux kernel version 3.0.1, the clean target removes
        # arch/powerpc/lib/crtsavres.o which is present in
        # KBUILD_LDFLAGS_MODULE, making it required to build external modules.
        if [ ${ARCH} = "powerpc" ]; then
                mkdir -p $kerneldir/arch/powerpc/lib/
                cp ${B}/arch/powerpc/lib/crtsavres.o $kerneldir/arch/powerpc/lib/crtsavres.o
        fi

        # Remove fixdep/objtool as they won't be target binaries
        for i in fixdep objtool; do
                if [ -e $kerneldir/tools/objtool/$i ]; then
                        rm -rf $kerneldir/tools/objtool/$i
                fi
        done

        chown -R root:root ${D}
}

# Ensure we don't race against "make scripts" during cpio
do_install[lockfiles] = "${TMPDIR}/kernel-scripts.lock"

FILES_${PN} = "${KERNEL_BUILD_ROOT} ${KERNEL_SRC_PATH}"
FILES_${PN}-dbg += "${KERNEL_BUILD_ROOT}*/build/scripts/*/.debug/*"

RDEPENDS_${PN} = "bc python3 flex bison ${TCLIBC}-utils"
# 4.15+ needs these next two RDEPENDS
RDEPENDS_${PN} += "openssl-dev util-linux"
# and x86 needs a bit more for 4.15+
RDEPENDS_${PN} += "${@bb.utils.contains('ARCH', 'x86', 'elfutils', '', d)}"
# 5.8+ needs gcc-plugins libmpc-dev
RDEPENDS_${PN} += "gcc-plugins libmpc-dev"

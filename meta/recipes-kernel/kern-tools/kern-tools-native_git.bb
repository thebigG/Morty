SUMMARY = "Tools for managing Yocto Project style branched kernels"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://tools/kgit;beginline=5;endline=9;md5=9c30e971d435e249624278c3e343e501"

DEPENDS = "git-native"

SRCREV = "b46b1c4f0973bf1eb09cf1191f5f4e69bcd0475d"
PR = "r12"
PV = "0.2+git${SRCPV}"

inherit native

SRC_URI = "git://git.yoctoproject.org/yocto-kernel-tools.git"
S = "${WORKDIR}/git"
UPSTREAM_CHECK_COMMITS = "1"

do_configure() {
	:
}

do_compile() { 
	:
}

do_install() {
	oe_runmake DESTDIR=${D}${bindir} install
}

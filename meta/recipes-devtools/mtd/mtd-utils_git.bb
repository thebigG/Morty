SUMMARY = "Tools for managing memory technology devices"
HOMEPAGE = "http://www.linux-mtd.infradead.org/"
SECTION = "base"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3 \
                    file://include/common.h;beginline=1;endline=17;md5=ba05b07912a44ea2bf81ce409380049c"

inherit autotools pkgconfig update-alternatives

DEPENDS = "zlib e2fsprogs util-linux"
RDEPENDS_mtd-utils-tests += "bash"

PV = "2.1.2"

SRCREV = "7b986779342021bda87c04da3bf729718736d8ab"
SRC_URI = "git://git.infradead.org/mtd-utils.git \
           file://add-exclusion-to-mkfs-jffs2-git-2.patch \
           "

S = "${WORKDIR}/git"

EXTRA_OECONF += "--enable-install-tests"

# xattr support creates an additional compile-time dependency on acl because
# the sys/acl.h header is needed. libacl is not needed and thus enabling xattr
# regardless whether acl is enabled or disabled in the distro should be okay.
PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'xattr', d)} lzo jffs ubifs"
PACKAGECONFIG[lzo] = "--with-lzo,--without-lzo,lzo"
PACKAGECONFIG[xattr] = "--with-xattr,--without-xattr,acl"
PACKAGECONFIG[crypto] = "--with-crypto,--without-crypto,openssl"
PACKAGECONFIG[jffs] = "--with-jffs,--without-jffs"
PACKAGECONFIG[ubifs] = "--with-ubifs,--without-ubifs"
PACKAGECONFIG[zstd] = "--with-zstd,--without-zstd,zstd"

CPPFLAGS_append_riscv64  = " -pthread -D_REENTRANT"

EXTRA_OEMAKE = "'CC=${CC}' 'RANLIB=${RANLIB}' 'AR=${AR}' 'CFLAGS=${CFLAGS} ${@bb.utils.contains('PACKAGECONFIG', 'xattr', '', '-DWITHOUT_XATTR', d)} -I${S}/include' 'BUILDDIR=${S}'"

ALTERNATIVE_${PN} = "flash_eraseall"
ALTERNATIVE_LINK_NAME[flash_eraseall] = "${sbindir}/flash_eraseall"
# Use higher priority than busybox's flash_eraseall (created when built with CONFIG_FLASH_ERASEALL)
ALTERNATIVE_PRIORITY[flash_eraseall] = "100"

do_install () {
	oe_runmake install DESTDIR=${D} SBINDIR=${sbindir} MANDIR=${mandir} INCLUDEDIR=${includedir}
}

PACKAGES =+ "mtd-utils-misc mtd-utils-tests"
PACKAGES =+ "${@bb.utils.contains("PACKAGECONFIG", "jffs", "mtd-utils-jffs2", "", d)}"
PACKAGES =+ "${@bb.utils.contains("PACKAGECONFIG", "ubifs", "mtd-utils-ubifs", "", d)}"

FILES_mtd-utils-jffs2 = "${sbindir}/mkfs.jffs2 ${sbindir}/jffs2dump ${sbindir}/jffs2reader ${sbindir}/sumtool"
FILES_mtd-utils-ubifs = "${sbindir}/mkfs.ubifs ${sbindir}/ubi*"
FILES_mtd-utils-misc = "${sbindir}/nftl* ${sbindir}/ftl* ${sbindir}/rfd* ${sbindir}/doc* ${sbindir}/serve_image ${sbindir}/recv_image"
FILES_mtd-utils-tests = "${libexecdir}/mtd-utils/*"

BBCLASSEXTEND = "native nativesdk"

# git/.compr.c.dep:46: warning: NUL character seen; rest of line ignored
# git/.compr.c.dep:47: *** missing separator.  Stop.
PARALLEL_MAKE = ""

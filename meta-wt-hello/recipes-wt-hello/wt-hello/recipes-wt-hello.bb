SUMMARY = "Simple C++ program using Boost"
SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
FILES_${PN} += "/usr/share/Wt \
		/usr/lib/cmake "

inherit cmake

DEPENDS = "boost"
SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/thebigG/wt-example.git;branch=main;protocol=http"

S = "${WORKDIR}/git"

BBCLASSEXTEND = "native"

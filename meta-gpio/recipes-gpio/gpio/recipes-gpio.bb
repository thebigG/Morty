SUMMARY = "Simple C++ program using Boost"
SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
FILES_${PN} += "/usr/lib/cmake "

inherit cmake

do_install() {
    install -d ${D}${bindir}
    install -m 0755 simple_gpio ${D}${bindir}
}

DEPENDS = "boost"
SRCREV = "${AUTOREV}"
SRC_URI = "gitsm://github.com/thebigG/simple_gpio.git;branch=main;protocol=http"

S = "${WORKDIR}/git"

BBCLASSEXTEND = "native"

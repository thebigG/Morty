inherit cargo

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SRC_URI = "gitsm://github.com/thebigG/gpio_pedals.git;branch=main;protocol=http"
SRCREV = "${AUTOREV}"

SUMMARY = "Hello World by Cargo for Rust"
HOMEPAGE = "https://github.com/meta-rust/rust-hello-world"
LICENSE = "MIT | Apache-2.0"

S = "${WORKDIR}/git"

BBCLASSEXTEND = "native"

# Minimal makefile for Sphinx documentation
#

# You can set these variables from the command line, and also
# from the environment for the first two.
SPHINXOPTS    ?=
SPHINXBUILD   ?= sphinx-build
SOURCEDIR     = .
BUILDDIR      = _build
DESTDIR       = final

ifeq ($(shell if which $(SPHINXBUILD) >/dev/null 2>&1; then echo 1; else echo 0; fi),0)
$(error "The '$(SPHINXBUILD)' command was not found. Make sure you have Sphinx installed")
endif

# Put it first so that "make" without argument is like "make help".
help:
	@$(SPHINXBUILD) -M help "$(SOURCEDIR)" "$(BUILDDIR)" $(SPHINXOPTS) $(O)

.PHONY: help Makefile clean publish

MANUALS = $(DOC)/$(DOC).html $(DOC)/eclipse
FIGURES = figures
STYLESHEET = $(DOC)/*.css

endif

ifeq ($(DOC),yocto-project-qs)
XSLTOPTS = --xinclude
ALLPREQ = html eclipse tarball
TARFILES = yocto-project-qs.html qs-style.css \
           figures/yocto-project-transp.png \
           eclipse
MANUALS = $(DOC)/$(DOC).html $(DOC)/eclipse
FIGURES = figures
STYLESHEET = $(DOC)/*.css
endif

ifeq ($(DOC),mega-manual)
XSLTOPTS = --stringparam html.stylesheet mega-style.css \
           --stringparam  chapter.autolabel 1 \
           --stringparam  section.autolabel 1 \
           --stringparam  section.label.includes.component.label 1 \
           --xinclude
ALLPREQ = html tarball

	ifeq ($(BRANCH),edison)
TARFILES = mega-manual.html mega-style.css figures/yocto-environment.png \
        figures/building-an-image.png  \
	figures/using-a-pre-built-image.png \
	figures/poky-title.png \
	figures/adt-title.png figures/bsp-title.png \
	figures/kernel-title.png figures/kernel-architecture-overview.png \
	figures/app-dev-flow.png figures/bsp-dev-flow.png \
        figures/dev-title.png figures/git-workflow.png \
        figures/index-downloads.png figures/kernel-dev-flow.png \
	figures/kernel-example-repos-edison.png \
	figures/kernel-overview-1.png figures/kernel-overview-2.png \
	figures/kernel-overview-3-edison.png \
	figures/source-repos.png figures/yp-download.png \
	figures/wip.png
	else ifeq ($(BRANCH),denzil)
TARFILES = mega-manual.html mega-style.css figures/yocto-environment.png \
        figures/building-an-image.png  \
	figures/using-a-pre-built-image.png \
	figures/poky-title.png \
	figures/adt-title.png figures/bsp-title.png \
	figures/kernel-title.png figures/kernel-architecture-overview.png \
	figures/app-dev-flow.png figures/bsp-dev-flow.png \
        figures/dev-title.png figures/git-workflow.png \
        figures/index-downloads.png figures/kernel-dev-flow.png \
	figures/kernel-example-repos-denzil.png \
	figures/kernel-overview-1.png figures/kernel-overview-2.png \
	figures/kernel-overview-3-denzil.png \
	figures/source-repos.png figures/yp-download.png \
	figures/wip.png
        else
TARFILES = mega-manual.html mega-style.css \
        figures/building-an-image.png figures/YP-flow-diagram.png \
	figures/using-a-pre-built-image.png \
	figures/poky-title.png figures/buildhistory.png \
        figures/buildhistory-web.png \
	figures/sdk-title.png figures/bsp-title.png \
	figures/kernel-dev-title.png figures/kernel-architecture-overview.png \
	figures/bsp-dev-flow.png \
        figures/dev-title.png \
	figures/git-workflow.png figures/index-downloads.png \
        figures/kernel-dev-flow.png \
	figures/kernel-overview-2-generic.png \
	figures/source-repos.png figures/yp-download.png \
        figures/profile-title.png figures/kernelshark-all.png \
        figures/kernelshark-choose-events.png \
        figures/kernelshark-i915-display.png \
        figures/kernelshark-output-display.png figures/lttngmain0.png \
        figures/oprofileui-busybox.png figures/oprofileui-copy-to-user.png \
        figures/oprofileui-downloading.png figures/oprofileui-processes.png \
        figures/perf-probe-do_fork-profile.png \
        figures/perf-report-cycles-u.png \
        figures/perf-systemwide.png figures/perf-systemwide-libc.png \
        figures/perf-wget-busybox-annotate-menu.png \
        figures/perf-wget-busybox-annotate-udhcpc.png \
        figures/perf-wget-busybox-debuginfo.png \
        figures/perf-wget-busybox-dso-zoom.png \
        figures/perf-wget-busybox-dso-zoom-menu.png \
        figures/perf-wget-busybox-expanded-stripped.png \
        figures/perf-wget-flat-stripped.png \
        figures/perf-wget-g-copy-from-user-expanded-stripped.png \
        figures/perf-wget-g-copy-to-user-expanded-debuginfo.png \
        figures/perf-wget-g-copy-to-user-expanded-stripped.png \
        figures/perf-wget-g-copy-to-user-expanded-stripped-unresolved-hidden.png \
        figures/pybootchartgui-linux-yocto.png \
        figures/pychart-linux-yocto-rpm.png \
        figures/pychart-linux-yocto-rpm-nostrip.png \
        figures/sched-wakeup-profile.png figures/sysprof-callers.png \
        figures/sysprof-copy-from-user.png figures/sysprof-copy-to-user.png \
        figures/cross-development-toolchains.png \
	figures/yocto-environment-ref.png figures/user-configuration.png \
        figures/source-input.png figures/package-feeds.png \
        figures/layer-input.png figures/images.png figures/sdk.png \
	figures/source-fetching.png figures/patching.png \
        figures/configuration-compile-autoreconf.png \
	figures/analysis-for-package-splitting.png \
        figures/image-generation.png \
	figures/sdk-generation.png figures/recipe-workflow.png \
	figures/build-workspace-directory.png figures/mega-title.png \
	figures/toaster-title.png figures/hosted-service.png \
	figures/simple-configuration.png \
	figures/compatible-layers.png figures/import-layer.png figures/new-project.png \
	figures/sdk-environment.png figures/sdk-installed-standard-sdk-directory.png \
	figures/sdk-devtool-add-flow.png figures/sdk-installed-extensible-sdk-directory.png \
	figures/sdk-devtool-modify-flow.png figures/sdk-eclipse-dev-flow.png \
	figures/sdk-devtool-upgrade-flow.png figures/bitbake-build-flow.png
	endif

MANUALS = $(DOC)/$(DOC).html
FIGURES = figures
STYLESHEET = $(DOC)/*.css

endif

ifeq ($(DOC),ref-manual)
XSLTOPTS = --xinclude
ALLPREQ = html eclipse tarball
TARFILES = ref-manual.html ref-style.css figures/poky-title.png figures/YP-flow-diagram.png \
	figures/buildhistory.png figures/buildhistory-web.png eclipse \
        figures/cross-development-toolchains.png figures/layer-input.png \
	figures/package-feeds.png figures/source-input.png \
	figures/user-configuration.png figures/yocto-environment-ref.png \
	figures/images.png figures/sdk.png figures/source-fetching.png \
	figures/patching.png figures/configuration-compile-autoreconf.png \
	figures/analysis-for-package-splitting.png figures/image-generation.png \
	figures/sdk-generation.png figures/building-an-image.png \
	figures/build-workspace-directory.png figures/source-repos.png \
	figures/index-downloads.png figures/yp-download.png figures/git-workflow.png
MANUALS = $(DOC)/$(DOC).html $(DOC)/eclipse
FIGURES = figures
STYLESHEET = $(DOC)/*.css
endif

ifeq ($(DOC),sdk-manual)
XSLTOPTS = --xinclude
ALLPREQ = html eclipse tarball
TARFILES = sdk-manual.html sdk-style.css figures/sdk-title.png \
           figures/sdk-environment.png figures/sdk-installed-standard-sdk-directory.png \
	   figures/sdk-installed-extensible-sdk-directory.png figures/sdk-devtool-add-flow.png \
	   figures/sdk-devtool-modify-flow.png figures/sdk-eclipse-dev-flow.png \
	   figures/sdk-devtool-upgrade-flow.png \
           eclipse
MANUALS = $(DOC)/$(DOC).html $(DOC)/eclipse
FIGURES = figures
STYLESHEET = $(DOC)/*.css
endif

ifeq ($(DOC),profile-manual)
XSLTOPTS = --xinclude
ALLPREQ = html eclipse tarball
TARFILES = profile-manual.html profile-manual-style.css \
           figures/profile-title.png figures/kernelshark-all.png \
           figures/kernelshark-choose-events.png \
           figures/kernelshark-i915-display.png \
           figures/kernelshark-output-display.png figures/lttngmain0.png \
           figures/oprofileui-busybox.png figures/oprofileui-copy-to-user.png \
           figures/oprofileui-downloading.png figures/oprofileui-processes.png \
           figures/perf-probe-do_fork-profile.png \
           figures/perf-report-cycles-u.png \
           figures/perf-systemwide.png figures/perf-systemwide-libc.png \
           figures/perf-wget-busybox-annotate-menu.png \
           figures/perf-wget-busybox-annotate-udhcpc.png \
           figures/perf-wget-busybox-debuginfo.png \
           figures/perf-wget-busybox-dso-zoom.png \
           figures/perf-wget-busybox-dso-zoom-menu.png \
           figures/perf-wget-busybox-expanded-stripped.png \
           figures/perf-wget-flat-stripped.png \
           figures/perf-wget-g-copy-from-user-expanded-stripped.png \
           figures/perf-wget-g-copy-to-user-expanded-debuginfo.png \
           figures/perf-wget-g-copy-to-user-expanded-stripped.png \
           figures/perf-wget-g-copy-to-user-expanded-stripped-unresolved-hidden.png \
           figures/pybootchartgui-linux-yocto.png \
           figures/pychart-linux-yocto-rpm.png \
           figures/pychart-linux-yocto-rpm-nostrip.png \
           figures/sched-wakeup-profile.png figures/sysprof-callers.png \
           figures/sysprof-copy-from-user.png figures/sysprof-copy-to-user.png \
           eclipse
MANUALS = $(DOC)/$(DOC).html $(DOC)/eclipse
FIGURES = figures
STYLESHEET = $(DOC)/*.css
endif

ifeq ($(DOC),kernel-dev)
XSLTOPTS = --xinclude
ALLPREQ = html eclipse tarball
TARFILES = kernel-dev.html kernel-dev-style.css \
           figures/kernel-dev-title.png figures/kernel-overview-2-generic.png \
           figures/kernel-architecture-overview.png figures/kernel-dev-flow.png \
           eclipse
MANUALS = $(DOC)/$(DOC).html $(DOC)/eclipse
FIGURES = figures
STYLESHEET = $(DOC)/*.css
endif

ifeq ($(DOC),toaster-manual)
XSLTOPTS = --xinclude
ALLPREQ = html tarball
TARFILES = toaster-manual.html toaster-manual-style.css \
	   figures/toaster-title.png figures/simple-configuration.png \
	   figures/hosted-service.png \
	   figures/compatible-layers.png figures/import-layer.png figures/new-project.png
MANUALS = $(DOC)/$(DOC).html
FIGURES = figures
STYLESHEET = $(DOC)/*.css
endif


##
# These URI should be rewritten by your distribution's xml catalog to
# match your locally installed XSL stylesheets.
XSL_BASE_URI  = http://docbook.sourceforge.net/release/xsl/1.76.1
XSL_XHTML_URI = $(XSL_BASE_URI)/xhtml/docbook.xsl

all: $(ALLPREQ)

pdf:
ifeq ($(DOC),yocto-project-qs)
	@echo " "
	@echo "ERROR: You cannot generate a yocto-project-qs PDF file."
	@echo " "

else ifeq ($(DOC),mega-manual)
	@echo " "
	@echo "ERROR: You cannot generate a mega-manual PDF file."
	@echo " "

else

	cd $(DOC); ../tools/poky-docbook-to-pdf $(DOC).xml ../template; cd ..
endif

html:
ifeq ($(DOC),mega-manual)
#       See http://www.sagehill.net/docbookxsl/HtmlOutput.html
	@echo " "
	@echo "******** Building "$(DOC)
	@echo " "
	cd $(DOC); xsltproc $(XSLTOPTS) -o $(DOC).html $(DOC)-customization.xsl $(DOC).xml; cd ..
	@echo " "
	@echo "******** Using mega-manual.sed to process external links"
	@echo " "
	cd $(DOC); sed -f ../tools/mega-manual.sed < mega-manual.html > mega-output.html; cd ..
	@echo " "
	@echo "******** Cleaning up transient file mega-output.html"
	@echo " "
	cd $(DOC); rm mega-manual.html; mv mega-output.html mega-manual.html; cd ..
else
#       See http://www.sagehill.net/docbookxsl/HtmlOutput.html
	@echo " "
	@echo "******** Building "$(DOC)
	@echo " "
	cd $(DOC); xsltproc $(XSLTOPTS) -o $(DOC).html $(DOC)-customization.xsl $(DOC).xml; cd ..
endif


eclipse: BASE_DIR = html/$(DOC)/

eclipse: eclipse-generate eclipse-resolve-links

.PHONY : eclipse-generate eclipse-resolve-links

eclipse-generate:
ifeq ($(filter $(DOC), sdk-manual bsp-guide dev-manual kernel-dev profile-manual ref-manual yocto-project-qs),)
	@echo " "
	@echo "ERROR: You can only create eclipse documentation"
	@echo "       of the following documentation parts:"
	@echo "       - sdk-manual"
	@echo "       - bsp-guide"
	@echo "       - dev-manual"
	@echo "       - kernel-dev"
	@echo "       - profile-manual"
	@echo "       - ref-manual"
	@echo "       - yocto-project-qs"
	@echo " "
else
	@echo " "
	@echo "******** Building eclipse help of "$(DOC)
	@echo " "
	cd $(DOC) && \
		xsltproc $(XSLTOPTS) \
				--stringparam base.dir '$(BASE_DIR)' \
				-o eclipse/$(DOC).html \
				$(DOC)-eclipse-customization.xsl $(DOC).xml && \
		mv eclipse/toc.xml eclipse/$(DOC)-toc.xml && \
		cp -rf $(FIGURES) eclipse/$(BASE_DIR) && \
		cd ..;

	$(call modify-eclipse)
endif

eclipse-resolve-links:
	@echo " "
	@echo "******** Using eclipse-help.sed to process external links"
	@echo " "
	$(foreach FILE, \
		$(wildcard $(DOC)/eclipse/html/$(DOC)/*.html), \
		$(shell sed -i -f tools/eclipse-help.sed $(FILE)))

tarball: html
	@echo " "
	@echo "******** Creating Tarball of document files"
	@echo " "
	cd $(DOC); tar -cvzf $(DOC).tgz $(TARFILES); cd ..

validate:
	cd $(DOC); xmllint --postvalid --xinclude --noout $(DOC).xml; cd ..


publish:
	@if test -f $(DOC)/$(DOC).html; \
	  then \
            echo " "; \
            echo "******** Publishing "$(DOC)".html"; \
            echo " "; \
            scp -r $(MANUALS) $(STYLESHEET) docs.yp:/var/www/www.yoctoproject.org-docs/$(VER)/$(DOC); \
            cd $(DOC); scp -r $(FIGURES) docs.yp:/var/www/www.yoctoproject.org-docs/$(VER)/$(DOC); \
	else \
          echo " "; \
          echo $(DOC)".html missing.  Generate the file first then try again."; \
          echo " "; \
	fi

clean:
	@rm -rf $(BUILDDIR)

# Catch-all target: route all unknown targets to Sphinx using the new
# "make mode" option.  $(O) is meant as a shortcut for $(SPHINXOPTS).
%: Makefile
	@$(SPHINXBUILD) -M $@ "$(SOURCEDIR)" "$(BUILDDIR)" $(SPHINXOPTS) $(O)

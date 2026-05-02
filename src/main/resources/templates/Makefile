DIST_DIR := dist
WIDGET   := $(notdir $(CURDIR))

.PHONY: all clean

all: $(DIST_DIR)
	cd src && zip -j ../$(DIST_DIR)/$(WIDGET).wgt *
	@echo "Built: $(DIST_DIR)/$(WIDGET).wgt"

$(DIST_DIR):
	mkdir -p $(DIST_DIR)

clean:
	rm -rf $(DIST_DIR)
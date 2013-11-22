#!/bin/bash

# Import Jataka common install script (from source0)
. "./install/bin/redhat/_jataka-install.sh"

install_files() {
	local base_dir="$1"

	export SOURCE_DIR="$(get_full_path $(dirname "$0"))"
	
	install_recursive "../../../cube-server" "${CUBE_SERVER_SHARE}"
	install_recursive "../../../jataka-common" "${CUBE_SERVER_SHARE}"
}

main() {
	install_files
}

CUBE_SERVER_HOME="${CUBE_SERVER_HOME:-server}"
CUBE_SERVER_SHARE="/usr/share/jatakasource/testcube/${CUBE_SERVER_HOME}"

main

exit 0

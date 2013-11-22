#!/bin/bash

get_full_path() {
    # Absolute path to this script, e.g. /home/user/bin/foo.sh
    SCRIPT=$(readlink -f $0)

    if [ ! -d ${SCRIPT} ]; then
        # Absolute path this script is in, thus /home/user/bin
        SCRIPT=`dirname $SCRIPT`
    fi

    ( cd "${SCRIPT}" ; pwd )
}

export SYS_CONFIG_DIR="/etc/sysconfig"
export INITD_CONFIG_DIR="/lib/systemd/system"
export INITD_CONFIG_TMPL="testcube.init.d.fedora"
export INITD_CONFIG_EXTE=".service"

$(get_full_path ./)/../server-instance.sh --action=create --instance=server --db-type=pgsql
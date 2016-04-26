#!/bin/sh
ALGO=$1
SPL=$2

USER=sobernig
PREFIX=/home/${USER}

LOCAL=/local/${USER}/${SLURM_JOB_ID}
## staging to nodes
cp -R ${PREFIX}/sayyad ${LOCAL}
(
    cd ${LOCAL}
    ## trigger the run
    ## (assuming `ant compile` was performed once on the SLURM server: debussy).
    ant -Dalgo=${ALGO} -Dspl=${SPL} -Drepeats=1 run
    
    ## copy-merge the result data back to the SLURM server 
    for file in $(find NSGAIIDMStudy/data -type f); do
	## echo "${file}"
	mkdir -p "${PREFIX}/$(dirname "${file}")"
	cat "${file}" >> "${PREFIX}/${file}"
    done
)

## provide a clean slate
rm -rf ${LOCAL}

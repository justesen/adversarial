#!/bin/bash

ROOT=$(readlink -m .)
REFERENCE=../../depqbf/depqbf
CP=$ROOT/../out/production/adversarial
INSTANCES=$ROOT/../instances/qbf/testsuite/*.qdimacs

TIMEOUT=$1
EXPL_CONST=$2
PLAYOUTS=$3
SEARCHTRAP_LEVEL=$4

echo -e "UCT (c = ${EXPL_CONST}, ${PLAYOUTS} playouts)\tNodes\tTime (ms) (TO ${TIMEOUT}s)\tSearch traps (level ${SEARCHTRAP_LEVEL})"

for I in $INSTANCES
do
    timeout $1 java -cp $CP adv.Main uct $EXPL_CONST $PLAYOUTS $SEARCHTRAP_LEVEL $I
    if [ $? -ge 124 ]
    then
        echo ""
    fi
done
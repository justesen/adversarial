#!/bin/bash

ROOT=$(readlink -m .)
CP=$ROOT/../out/production/adversarial:/usr/local/lib/antlr-3.5.2-complete.jar
INSTANCES=$ROOT/../instances/qbf/NPNCNF2008/*.qbf

TIMEOUT=$1
EXPL_CONST=$2
PLAYOUTS=$3

echo -e "UCT (expr, c = ${EXPL_CONST}, ${PLAYOUTS} playouts)\tNodes\tTime (ms) (TO ${TIMEOUT}s)"

for I in $INSTANCES
do
    timeout $1 java -cp $CP adv.Main expr $EXPL_CONST $PLAYOUTS $I
    if [ $? -ge 124 ]
    then
        echo ""
    fi
done
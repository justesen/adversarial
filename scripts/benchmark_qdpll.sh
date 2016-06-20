#!/bin/bash

ROOT=$(readlink -m .)
REFERENCE=../../depqbf/depqbf
CP=$ROOT/../out/production/adversarial
INSTANCES=$ROOT/../instances/qbf/testsuite/*.qdimacs

echo -e "QDPLL\tNodes\tTime (ms) (TO ${1}s)"

for I in $INSTANCES
do
    timeout $1 java -cp $CP adv.Main qdpll $I
    if [ $? -ge 124 ]
    then
        echo ""
    fi
done
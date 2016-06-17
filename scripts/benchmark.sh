#!/bin/bash

ROOT=$(readlink -m .)
REFERENCE=../../depqbf/depqbf
CP=$ROOT/../out/production/adversarial
INSTANCES=$ROOT/../instances/qbf/testsuite/*.qdimacs

echo -e "${2^^}\tNodes\tTime (ms) (TO $1s)"

for I in $INSTANCES
do
    timeout $1 java -cp $CP adv.Main $2 $I
    if [ $? -ge 124 ]
    then
        echo ""
    fi
done
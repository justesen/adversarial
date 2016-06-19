#!/bin/bash

ROOT=$(readlink -m .)
CP=$ROOT/../out/production/adversarial
INSTANCES=$ROOT/../instances/qbf/testsuite/*.qdimacs

echo Search traps (level $2) (TO $1s)

for I in $INSTANCES
do
    timeout $1 java -cp $CP adv.Main searchtraps $2 $I
    if [ $? -ge 124 ]
    then
        echo ""
    fi
done
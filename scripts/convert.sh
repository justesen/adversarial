#!/bin/bash

ROOT=$(readlink -m .)
CP=$ROOT/../out/production/adversarial:/usr/local/lib/antlr-3.5.2-complete.jar
INSTANCES=$ROOT/../instances/qbf/NPNCNF2008/*.qbf

for I in $INSTANCES
do
    java -cp $CP adv.Main convert $I
done
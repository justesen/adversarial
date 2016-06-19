#!/bin/bash

ROOT=$(readlink -m .)
CP=$ROOT/../out/production/adversarial

echo Make graph of $2...
java -cp $CP adv.Main graph $1 $2 $3
twopi -Nshape=point -Nwidth=0 -Tpng -O $3
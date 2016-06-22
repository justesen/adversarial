#!/bin/bash

EXPL_CONST=$1
FORMULA=$2
DOTFILE=$3

ROOT=$(readlink -m .)
CP=$ROOT/../out/production/adversarial

echo Make graph of $2...
java -cp $CP adv.Main graph $EXPL_CONST $FORMULA $DOTFILE
twopi -Nshape=point -Nwidth=0 -Tpng -O $3
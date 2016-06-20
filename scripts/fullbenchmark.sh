#!/bin/bash

./benchmark_qdpll.sh 10 > results_qdpll.log
./benchmark_uct.sh 20 0 5 7 > results_uct.log

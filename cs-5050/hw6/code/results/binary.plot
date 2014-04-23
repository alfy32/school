set logscale x 2
set logscale y 2

set xlabel 'Problem Size(n)'
set ylabel 'Average Runtime(ms)'

set key top left

set title 'String Match Binary'

set term png
set output 'binary.png'

set xtics rotate by -60

plot 'binary.bm.20' using 1:2 title 'BM 20%' with linespoints, \
     'binary.bm.50' using 1:2 title 'BM 50%' with linespoints, \
     'binary.bm.80' using 1:2 title 'BM 80%' with linespoints, \
     'binary.kmp.20' using 1:2 title 'KMP 20%' with linespoints, \
     'binary.kmp.50' using 1:2 title 'KMP 50%' with linespoints, \
     'binary.kmp.80' using 1:2 title 'KMP 80%' with linespoints, \
     'binary.naive.20' using 1:2 title 'Naive 20%' with linespoints, \
     'binary.naive.50' using 1:2 title 'Naive 50%' with linespoints, \
     'binary.naive.80' using 1:2 title 'Naive 80%' with linespoints;
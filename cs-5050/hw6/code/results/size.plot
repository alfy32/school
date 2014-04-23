set logscale x 2
set logscale y 2

set xlabel 'Problem Size(n)'
set ylabel 'Average Runtime(ms)'

set key top left

set title 'String Match Pattern Size'

set term png
set output 'size.png'

set xtics rotate by -60

plot 'size.bm.5' using 1:2 title 'BM 5' with linespoints, \
     'size.bm.10' using 1:2 title 'BM 10' with linespoints, \
     'size.bm.20' using 1:2 title 'BM 20' with linespoints, \
     'size.kmp.5' using 1:2 title 'KMP 5' with linespoints, \
     'size.kmp.10' using 1:2 title 'KMP 10' with linespoints, \
     'size.kmp.20' using 1:2 title 'KMP 20' with linespoints, \
     'size.naive.5' using 1:2 title 'Naive 5' with linespoints, \
     'size.naive.10' using 1:2 title 'Naive 10' with linespoints, \
     'size.naive.20' using 1:2 title 'Naive 20' with linespoints;
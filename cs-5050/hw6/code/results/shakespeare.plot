set logscale x 2
set logscale y 2

set xlabel 'Problem Size(n)'
set ylabel 'Average Runtime(ms)'

set key top left

set title 'String Match Shakespeare'

set term png
set output 'shakespeare.png'

set xtics rotate by -60

plot 'shakespeare.bm' using 1:2 title 'BM' with linespoints, \
     'shakespeare.kmp' using 1:2 title 'KMP' with linespoints, \
     'shakespeare.naive' using 1:2 title 'Naive' with linespoints;
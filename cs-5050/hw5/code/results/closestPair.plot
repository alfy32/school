set logscale x 2
set logscale y 2

set xlabel 'Problem Size(n)'
set ylabel 'Average Runtime(ms)'

set key bottom right

set title 'Closest Pair'

set term png
set output 'closestPair.png'

# set xtics rotate by -60

plot 'naiveRandom' using 1:2 title 'Naive Random' with linespoints, \
     'slowRandom' using 1:2 title 'Slow Random' with linespoints, \
     'fastRandom' using 1:2 title 'Fast Random' with linespoints;
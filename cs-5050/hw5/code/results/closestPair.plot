set logscale x 2
set logscale y 2

set xlabel 'Problem Size(n)'
set ylabel 'Average Runtime(ms)'

set key top left

set title 'Closest Pair'

set term png
set output 'closestPair.png'

set xtics rotate by -60

plot 'naiveRandom' using 1:2 title 'Naive Random' with linespoints, \
     'slowRandom' using 1:2 title 'Slow Random' with linespoints, \
     'fastRandom' using 1:2 title 'Fast Random' with linespoints, \
     'naiveUniform' using 1:2 title 'Naive Uniform' with linespoints, \
     'slowUniform' using 1:2 title 'Slow Uniform' with linespoints, \
     'fastUniform' using 1:2 title 'Fast Uniform' with linespoints, \
     'naiveMixed' using 1:2 title 'Naive Mixed' with linespoints, \
     'slowMixed' using 1:2 title 'Slow Mixed' with linespoints, \
     'fastMixed' using 1:2 title 'Fast Mixed' with linespoints;
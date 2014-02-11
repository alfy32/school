set logscale x 2
set logscale y 2

set xlabel "Problem Size(n)"
set ylabel "Average Runtime(ms)"

set key bottom

set title 'DP TraceBack VS D&C Linear Space'

set term png
set output 'dynamicVSlinear.png'

plot 'dynamicVSlinear' using 1:2 title 'D&C Linear Space' with linespoints, \
     'dynamicVSlinear' using 1:3 title 'Dynamic Progamming Traceback' with linespoints;

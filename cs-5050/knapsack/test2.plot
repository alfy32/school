set logscale y
set logscale x
set xlabel "Number of items(n)"
set ylabel "Time(ms)"

set title 'Caching and Dynamic Programming Solutions'

set term png
set output 'test2.plot.png'

plot 'test2.caching.results' using 1:2 title 'Caching Wide' with linespoints,\
     'test2.caching.results' using 1:3 title 'Caching Narrow' with linespoints,\
     'test2.dynamic.results' using 1:2 title 'Dynamic Wide' with linespoints,\
     'test2.dynamic.results' using 1:3 title 'Dynamic Narrow' with linespoints;
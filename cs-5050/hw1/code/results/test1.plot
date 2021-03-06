set logscale y
set xlabel "Number of items(n)"
set ylabel "Time(ms)"

set title 'Recursive solution with item sizes 1-1000'

set term png
set output 'test1.plot.png'

plot 'test1.results' using 1:2 title 'Size 1 to 1000' with linespoints;

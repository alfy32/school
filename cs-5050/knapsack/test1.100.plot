set logscale y
set xlabel "Number of items(n)"
set ylabel "Time(ms)"

set title 'Recursive solution'

set term png
set output 'test1.100.plot.png'

plot 'test1.100.results' using 1:2 title 'Size 1 to 100' with lines;
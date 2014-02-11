set logscale x 2
set logscale y 2

set xlabel "Problem Size(n)"
set ylabel "Average Runtime(ms)"

set key bottom

set title 'Split Size Test'

set term png
set output 'linearSplit.png'

plot 'linearSplit' using 1:2 title '1/2' with linespoints, \
     'linearSplit' using 1:3 title '1/4' with linespoints, \
     'linearSplit' using 1:4 title '1/8' with linespoints, \
     'linearSplit' using 1:5 title '1/16' with linespoints, \
     'linearSplit' using 1:6 title '1/32' with linespoints, \
     'linearSplit' using 1:7 title '1' with linespoints;

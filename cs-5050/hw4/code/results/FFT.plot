# set logscale x 2
set logscale y 2

set xlabel 'Problem Size(n) - Labeled with log2(n) to keep number smaller'
set ylabel 'Average Runtime(ms)'

set key bottom right

set title 'FFT PolyMult - DP Vs Recursive'

set term png
set output 'FFT.png'

# set xtics rotate by -60

plot 'FFT.recursive' using log(1):2 title 'Recursive' with linespoints, \
     'FFT.dynamic' using log(1):2 title 'Dynamic Programming' with linespoints;

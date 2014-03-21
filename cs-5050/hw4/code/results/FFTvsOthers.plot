# set logscale x 2
set logscale y 2

set xlabel 'Problem Size(n) - Labeled with log2(n) to keep number smaller'
set ylabel 'Average Runtime(ms)'

set key bottom right

set title 'FFT VS Other Methods'

set term png
set output 'FFTvsOthers.png'

# set xtics rotate by -60

plot 'FFT.recursive' using log(1):2 title 'FFT Recursive' with linespoints, \
     'FFT.dynamic' using log(1):2 title 'FFT DP' with linespoints, \
     'pastResults/polymult.dnc3' using log(1):2 title 'Dnc 3' with linespoints, \
     'pastResults/polymult.dnc4' using log(1):2 title 'Dnc 4' with linespoints, \
     'pastResults/polymult.schoolbook' using log(1):2 title 'SchoolBook' with linespoints;

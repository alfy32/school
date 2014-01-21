knapsack
========

CS 5050 - Advanced Algorithms - Assignment One - 40 points

1)      Develop a simple recursive solution to the following Knapsack optimization problem:

Given: n items each of size s[i] and value v[i] and maximum capacity S. All objects have a non-zero size and a positive value
Find a subset of objects that fit into the knapsack. It is not necessary that they exactly fit into the knapsack
Such that the sum of the values of the objects put in the knapsack is maximized.
2)      Implement the solution as a recursive algorithm

3)      Develop a caching (memorizing) solution to this problem and implement it in the language of your choice (approved by Flann).

4)      Develop a dynamic programming solution to this problem and implement it in the language of your choice (approved by Flann).

5)      Once you have verified that the code is running correctly (i.e., all algorithms compute the same answers), perform the following two empirical studies:

For randomized data generate a graph that measures the average cpu time of the recursive solution as a function of n. Pick a fixed S as 1000 and assign the sizes of the objects from 1 to 1000 using a uniform random distribution. Start at n=6 and go as large as you can within reasonable running times, increasing the problem size by 1 each time. Produce a semi-log graph where n is the size of the problem (number of objects) along the x axis and y is the average run time (log scale). If your code is running correctly your graph should illustrate a near-straight line. Compute the slope of the line and explain the value you obtained.
Here you will compare the run-time performance of the dynamic programming solution with the caching solution under different random data distributions and much larger problem sizes. Given n is the problem size and m is the capacity of the knapsack for each experiment set m= 10*n. In each experiment, generate the sizes of the n objects need from a uniform random distribution between min-size and m/10. You will run the experiments under two different distributions (a) wide where min-size is 1, and narrow where min-size is m/20. Start at size 64 and go as large as you can within reasonable running times, increasing the problem size by a factor of 2 each time. Produce a log-log graph where x is the log of the size of the problem and y is the log of the average running time. The graph should have two lines, one for wide and one for narrow.
6)      Your graphs should be clearly captioned with the axis labeled. I recommend using gnuplot or some other professional quality graphing software. Whatever you use, you must have the correct linear-log or log-log graph.

7)      Write a brief (4-6 sentence) technical explanation of the behavior of the algorithms derived from the graphs.

8)      The 40 points will be awarded as follows: 10 points fully working program that correctly implements the three algorithms; 10 points for the empirical studies; 10 points for the correct graphs; 10 points for a correct technical explanation of the behavior

9)      Submit your graphs and report, data files containing your raw data and your commented code along with instructions to run the code.
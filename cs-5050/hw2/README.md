Assignment 2
============

[LinearSpaceKnapsack.pdf](assignment/LinearSpaceKnapsack.pdf)

**CS 5050 Assignment Two 40 points**

1. Implement the trace-back routine that takes the 2D cache array and returns an assignment of true or false to each object in the problem description, where true means the object is selected.

2. Implement the linear space knapsack algorithm to solve the same problem statement as Assignment 1.  Initially, the linear-space algorithm should divide the problem in to equal halves (so k is n/2).

3. For a set of small problem verify that the three algorithms (linear, standard DP and caching) produce the same answer.

4. Once you have verified that the code is running correctly perform the following empirical studies:

  1. For randomized data generate a graph that measures the average cpu time of the **linear-space solution** as a function of the problem size. Given n is the problem size and m is the capacity of the knapsack for each experiment set m= 10*n. In each experiment, generate the sizes of the n objects need from a uniform random distribution between 1 and m/10 (the distribution does not matter since we are using DP). Start at size 64 and go as large as you can within reasonable running times, increasing the problem size by a factor of 2 each time. Produce a log-log graph where x is the log of the size of the problem and y is the log of the average running time. This first graph will have one line corresponding to the k=n/2 algorithm.
  2. Now we will perform an experiment to measure how the cpu time is affected by the method used to split the problem into two sub- problems. Let k be the size of the first sub-problem and n-k be the size of the second. Repeat the experiment above but vary the split value k from the set: n/4, n/8, n/16, n/32 and 1. Produce a graph as above with 6 lines one for each experimental condition. You will find that under some experimental conditions, the run time increases significantly. In these cases limit the problem size n.

5. For each of the six data sets estimate the slope of the line by fitting a linear function to the point sequence log n, log t. Report these in a table.

6. Your graphs should be clearly captioned with the axis labeled. I recommend using gnuplot or some other professional quality graphing software. Whatever you use, you must have the correct log-log graph.

7. Write a brief (4-6 sentence) technical explanation of the behavior of the algorithms derived from the graphs.

8. The 40 points will be awarded as follows: 20 points fully working program that correctly implements the linear-space algorithm; 10 points for the empirical studies and line fitting; 10 points for the correct graphs and correct technical explanation of the behavior.

9. Submit your graphs and report, data files containing your raw data and your commented code along with instructions to run the code.

================================================================================================


The split step where two smaller DP problems are created and solved using the 2 column technique

![split 1](assigment/split1.png)

Once the k-1, k and k+1 columns are computed, then find the crossing point (bestSize) and whether the kth object should be used.

![bestSize](assignment/bestSize.png)

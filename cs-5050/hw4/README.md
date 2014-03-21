panAssignment 4 FFT
----------------


**CS Assignment Four 50 Points**

Fast Fourier Transform for Polynomial Multiplication

Summary: Implement recursive and dynamic programming version of the FFT and apply it to perform polynomial multiplication. Compare the performance of the FFT method with the previous algebraic techniques both w.r.t speed and accuracy.

1. Implement the recursive FFT with the following optimizations: (a) pre-compute the omega table (do not count this as part of the run time) and use the indexing technique to compute the x^2 values as needed.

2. Apply your FFT method to polynomial multiplication of two polynomials P Q size n by (a) padding the polynomials with high-order 0’s to make them size 2n, (b) evaluate P and Q at 2n values (powers of omega), (c) multiply P(xi)*Q(xi) to obtain samples of PQ(xi), (d) use the inverse FFT to interpolate the coefficients of PQ.

3. Check that the values computed by the FFT sampling technique are the same as those obtained by the previous approaches. There will be some slight rounding errors due limited resolution of the doubles (or longs).  (*Steps 1,2,3 are worth 20 points*)

4. Implement FFT using the optimized DP method: (a) No dynamic allocation, (b) pre-computed bit shuffle for coefficient lookup (don’t count in the timing), (c) 2 row space optimization. (*worth 10 points*)

5. Execute timing studies comparing the performance of recursive with DP FFT for the largest problems that can be feasibly solved. I’m hoping for at least 2^20. Plot the results on a log-log graph, measure the vertical offset between the two methods and compute the corresponding factor. How much faster is DP compared to recursive? (*worth 10 points*)

6. Compare the run-time performance of FFT with the previous methods. Plot the time performance for the four algorithms on one graph. The maximum problem size solvable will increase with algorithm performance. At what problem sizes do the lines cross? Give a table of crossing points. (*worth 10 points*)

7. Compare the accuracy of FFT with the algebraic methods. For larger problem sizes compute the mean absolute error between the coefficient values obtained by FFT and the n1.59 method as a function of problem size. The x axis should be problem size (increasing geometrically) vs. the mean absolute error (on a linear scale).  (*Extra 5 points 5*)

8. For the three empirical studies above (5, 6, 7) you must do randomized repeats and plot the average value on the graphs.

9. Implement two overloads of the complex number class. One where the complex multiply uses 3 real multiplies and one where it uses 4. Compare the performance using these two implementations running the DP FFT for the largest problem size. What is the difference in performance?  (*Extra 5 points*)

Submit a brief report that includes all your experimental results performed along with your documented code by the due date.

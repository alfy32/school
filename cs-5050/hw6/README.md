Assignment 6 string matching
============================

**CS 5050 Assignment Six, 60 points**

In this assignment you will implement three string matching algorithms: naïve, KML and BM and compare their performance over artificial and natural strings. For all runs you will implement the “all matches” version where the code goes to the end of the larger string.

1. Implement the three string-match algorithms and test them on some simple problems to verify they work correctly by returning the same answers. See the references below for some useful resources to help you with the coding details

2. Download the text file of the complete works of Shakespeare and some DNA sequences from the links listed below.

3. Write an artificial string problem generator that can generate a string of a given length from the alphabet {0, 1}, with a specific regularity. Use the following Markov decision process to create a string, given **p** (the probability of generating the same character again). Note that this technique can be generalized to larger vocabularies by adding more states (nodes) and modifying the probability of the edges that change the character output. In all graphs the sum of the leaving edges must be 1.0.

 

4. In this assignment you will need to design your own experiments to answer a series of questions concerning algorithm performance. First define **three** questions that you are interested in answering, for instance “How does the regularity of the string affect the performance of the algorithms for large string sizes?” or “How does the alphabet size affect performance for the algorithms on natural strings (e.g., DNA and Shakespeare).”

5. Once you have defined your three questions, design experiments to answer them. Where the dependent variable is cpu time, use a log-log plot to display the results and a doubling scheme to increase problem sizes. For other graphs use either a log/log, log/linear or linear/linear scales based on the data. If your studies involve randomized data, make sure each point on the graph is a result of at least 10 distinct runs.

6. The 60 points will be awarded as follows: 30 points fully working program that correctly implements the algorithms, the natural string data and the problem generator; 10 points each for a clearly defined and interesting question, well designed empirical studies, appropriate graph plot and discussion of the results.

7. Submit your graphs and report, data files containing your empirical data (not the actual strings!) and your commented code along with instructions to run the code.

Helpful resources:
------------------

Complete works of Shakespeare:

http://www.gutenberg.org/files/100/

Example DNA sequences: (chromosome 1, from position 1 to position 248,956,422)

http://www.ncbi.nlm.nih.gov/mapview/seq_reg.cgi?taxid=9606&chr=1&from=1&to=248956422

Some lecture notes that provide pseudo code for the algorithms:

http://www.cs.uku.fi/~kilpelai/BSA05/lectures/slides03.pdf

http://cs.indstate.edu/~kmandumula/presentation.pdf

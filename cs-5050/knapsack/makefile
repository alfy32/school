all: knapsack.o test.o
	g++ -std=c++11 main.cpp knapsack.o test.o -o knapsack

clean: 
	rm -r -f knapsack
	rm -r -f *.o

knapsack.o: knapsack.cpp knapsack.hpp
	g++ -std=c++11 -c knapsack.cpp -o knapsack.o

test.o: test.cpp test.hpp
	g++ -std=c++11 -c test.cpp -o test.o

test1: all
	./knapsack test1 > test1.results

test2.dynamic: all
	./knapsack test2 dynamic 30 > test2.dynamic.results

test2.caching: all
	./knapsack test2 caching 30 > test2.caching.results
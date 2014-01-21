all: knapsack.o test.o
	g++ -std=c++11 main.cpp knapsack.o test.o -o knapsack

clean: 
	rm -r -f knapsack
	rm -r -f *.o

knapsack.o: knapsack.cpp knapsack.hpp
	g++ -std=c++11 -c knapsack.cpp -o knapsack.o

test.o: test.cpp test.hpp
	g++ -std=c++11 -c test.cpp -o test.o
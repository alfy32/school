all: cacheRegular knapsack main.cpp
	g++ -std=c++11 main.cpp cacheRegular.o knapsack.o -o knapsack

clean: 
	rm -r -f knapsack
	rm -r -f *.o

cacheRegular: CacheRegular.hpp CacheRegular.cpp Cache.hpp
	g++ -std=c++11 -c CacheRegular.cpp -o cacheRegular.o

knapsack: Knapsack.hpp Knapsack.cpp
	g++ -std=c++11 -c Knapsack.cpp -o knapsack.o
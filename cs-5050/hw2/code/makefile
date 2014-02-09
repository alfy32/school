all: cacheReg cacheLin knapsack test main.cpp
	g++ -std=c++11 main.cpp cacheReg.o cacheLin.o knapsack.o test.o -o knapsack

clean:
	rm -r -f knapsack
	rm -r -f *.o

cacheReg: CacheRegular.hpp CacheRegular.cpp Cache.hpp
	g++ -std=c++11 -c CacheRegular.cpp -o cacheReg.o

cacheLin: CacheLinear.hpp CacheLinear.cpp Cache.hpp
	g++ -std=c++11 -c CacheLinear.cpp -o cacheLin.o

knapsack: Knapsack.hpp Knapsack.cpp
	g++ -std=c++11 -c Knapsack.cpp -o knapsack.o

test: Test.hpp Test.cpp
	g++ -std=c++11 -c Test.cpp -o test.o
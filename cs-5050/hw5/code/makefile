all: ClosestPair Point test main.cpp
	g++ -g -std=c++11 main.cpp ClosestPair.o Point.o test.o -o ClosestPair

clean:
	rm -r -f ClosestPair
	rm -r -f *.o

ClosestPair: ClosestPair.hpp ClosestPair.cpp
	g++ -g -std=c++11 -c ClosestPair.cpp -o ClosestPair.o

Point: Point.hpp Point.cpp
	g++ -g -std=c++11 -c Point.cpp -o Point.o

test: Test.hpp Test.cpp
	g++ -g -std=c++11 -c Test.cpp -o test.o
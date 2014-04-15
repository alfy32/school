all: StringMatch Point Test main.cpp
	g++ -g -std=c++11 main.cpp StringMatch.o Test.o -o match

clean:
	rm -r -f match
	rm -r -f *.o

StringMatch: StringMatch.hpp StringMatch.cpp
	g++ -g -std=c++11 -c StringMatch.cpp -o StringMatch.o

Test: Test.hpp Test.cpp
	g++ -g -std=c++11 -c Test.cpp -o test.o
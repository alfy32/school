all: StringMatch Point Test main.cpp
	g++ -g -std=c++11 main.cpp StringMatch.o Test.o -o match

clean:
	rm -r -f match
	rm -r -f *.o

StringMatch: StringMatch.hpp StringMatch.cpp
	g++ -g -std=c++11 -c StringMatch.cpp -o StringMatch.o

NaiveStringMatch: StringMatch NaiveStringMatch.hpp NaiveStringMatch.cpp
	g++ -g -std=c++11 -c StringMatch.o NaiveStringMatch.cpp -o NaiveStringMatch.o

BMStringMatch: StringMatch BMStringMatch.hpp BMStringMatch.cpp
	g++ -g -std=c++11 -c StringMatch.o BMStringMatch.cpp -o BMStringMatch.o

KMPStringMatch: StringMatch KMPStringMatch.hpp KMPStringMatch.cpp
	g++ -g -std=c++11 -c StringMatch.o KMPStringMatch.cpp -o KMPStringMatch.o

Test: Test.hpp Test.cpp
	g++ -g -std=c++11 -c Test.cpp -o test.o
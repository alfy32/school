all: StringMatch NaiveStringMatch BMStringMatch KMPStringMatch Test main.cpp
	g++ -g -std=c++11 main.cpp StringMatch.o NaiveStringMatch.o BMStringMatch.o KMPStringMatch.o Test.o -o match

clean:
	rm -r -f match
	rm -r -f *.o

StringMatch: StringMatch.hpp StringMatch.cpp
	g++ -g -std=c++11 -c StringMatch.cpp -o StringMatch.o

NaiveStringMatch: NaiveStringMatch.hpp NaiveStringMatch.cpp
	g++ -g -std=c++11 -c NaiveStringMatch.cpp -o NaiveStringMatch.o

BMStringMatch: BMStringMatch.hpp BMStringMatch.cpp
	g++ -g -std=c++11 -c BMStringMatch.cpp -o BMStringMatch.o

KMPStringMatch: KMPStringMatch.hpp KMPStringMatch.cpp
	g++ -g -std=c++11 -c KMPStringMatch.cpp -o KMPStringMatch.o

Test: Test.hpp Test.cpp
	g++ -g -std=c++11 -c Test.cpp -o Test.o
all: polymult test main.cpp
	g++ -std=c++11 main.cpp polymult.o test.o -o polymult

clean:
	rm -r -f polymult
	rm -r -f *.o

polymult: PolyMult.hpp PolyMult.cpp
	g++ -std=c++11 -c PolyMult.cpp -o polymult.o

test: Test.hpp Test.cpp
	g++ -std=c++11 -c Test.cpp -o test.o
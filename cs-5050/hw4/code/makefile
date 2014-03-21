all: FFT PolyMult test main.cpp
	g++ -g -std=c++11 main.cpp FFT.o PolyMult.o test.o -o FFT

clean:
	rm -r -f FFT
	rm -r -f *.o

FFT: FFT.hpp FFT.cpp
	g++ -g -std=c++11 -c FFT.cpp -o FFT.o

PolyMult: PolyMult.hpp PolyMult.cpp
	g++ -g -std=c++11 -c PolyMult.cpp -o PolyMult.o

test: Test.hpp Test.cpp
	g++ -g -std=c++11 -c Test.cpp -o test.o
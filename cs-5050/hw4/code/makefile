all: FFT test main.cpp
	g++ -g -std=c++11 main.cpp FFT.o test.o -o FFT

clean:
	rm -r -f polymult
	rm -r -f *.o

FFT: FFT.hpp FFT.cpp
	g++ -g -std=c++11 -c FFT.cpp -o FFT.o

test: Test.hpp Test.cpp
	g++ -g -std=c++11 -c Test.cpp -o test.o
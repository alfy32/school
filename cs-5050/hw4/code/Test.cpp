#include "Test.hpp"

const int RUNS = 30;

/////////////// Private ////////////////////

template<typename T>
double Test::averageRuntime(T t, int runs) {
  double times[runs];

  for(int i = 0; i < runs; i++) {
    clock_t start = clock();

    t();

    clock_t end = clock();

    times[i] = (end - start)/(CLOCKS_PER_SEC/1000);
  }

  return avg(times, runs);
}

double Test::avg(double values[], int size) {
  double sum = 0;
  for(int i = 0; i < size; i++) {
    sum += values[i];
  }
  return sum/size;
}

///////////////// Public ////////////////////

void Test::test(std::string which) {
  if(which == "All") {

  } else if(which == "pmr") {
    test_PolyMult_recursive();
  } else if(which == "pmd") {
    test_PolyMult_dynamic();
  } else if(which == "fftworks") {
    test_FFT_works();
  } else if(which == "polymultworks") {
    test_PolyMult_works();
  }
}

void Test::run(std::string which) {
  if(which == "") {
    std::cout << "Type which test to run." << std::endl;
  }
}

void Test::test_PolyMult_recursive() {
  int n = 2;
  Vector<Complex> P(n),Q(n);

  P[0] = Complex(2,0);
  P[1] = Complex(2,0);

  Q[0] = Complex(2,0);
  Q[1] = Complex(2,0);

  FFT fft;

  fft.preComputeOmega(n);
  Vector<Complex> results = fft.polyMultR(P, Q, n);

  for(Complex c : results) {
    std::cout << c << std::endl;
  }
}

void Test::test_PolyMult_dynamic() {
  int n = 2;
  Vector<Complex> P(n),Q(n);

  P[0] = Complex(2,0);
  P[1] = Complex(2,0);

  Q[0] = Complex(2,0);
  Q[1] = Complex(2,0);

  FFT fft;

  fft.preComputeOmega(n);
  Vector<Complex> results = fft.polyMultD(P, Q, n);

  for(Complex c : results) {
    std::cout << c << std::endl;
  }
}

void Test::test_FFT_works() {
  srand(time(0));

  int n = 4;
  Vector<Complex> P(n);

  int randMin = -100, randMax = 100;

  for(int i = 0; i < n; ++i) {

    double real = rand()%(randMax-randMin)+randMin;
    double imaginary = rand()%(randMax-randMin)+randMin;

    P[i] = Complex(real, imaginary);
  }

  // P[0] = Complex(2,0);
  // P[1] = Complex(2,0);
  // P[2] = Complex(2,0);
  // P[3] = Complex(2,0);

  Vector<Complex> x = Vector<Complex> (n);

  for(int i = 0; i < n; i++) {
    x[i] = Complex(cos(2*PI/n * i), sin(2*PI/n * i));
  }

  FFT fft;

  Vector<Complex> algebraic = fft.algebraic(P, n, x);
  Vector<Complex> recursive = fft.recursive(P, n, x, 1);
  Vector<Complex> dynamic = fft.dynamic(P, n, x);

  for(int i = 0; i < n; ++i) {
    std::cout << "A(" << i << "): " << algebraic[i] << std::endl;
    std::cout << "R(" << i << "): " << recursive[i] << std::endl;
    std::cout << "D(" << i << "): " << dynamic[i] << std::endl;
    std::cout << std::endl;
  }
}

void Test::test_PolyMult_works() {
  srand(time(0));

  int n = 4;

  int randMin = -100, randMax = 100;

  Vector<Complex> P(n), Q(n);

  for(int i = 0; i < n; ++i) {

    double real = rand()%(randMax-randMin)+randMin;
    double imaginary = rand()%(randMax-randMin)+randMin;

    P[i] = Complex(real, imaginary);
    Q[i] = Complex(real, imaginary);
  }

  FFT fft;

  fft.preComputeOmega(n);
  Vector<Complex> resultsR = fft.polyMultR(P, Q, n);
  Vector<Complex> resultsD = fft.polyMultD(P, Q, n);

  for(int i = 0; i < n; ++i) {
    if(resultsD[i] != resultsR[i])
      std::cout << "*** Failed ***" << std::endl;

    std::cout << resultsR[i] << " " << resultsD[i] << std::endl;
  }
}
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
  if(which == "all") {
    std::cout << std::endl << "test_PolyMult_recursive" << std::endl << std::endl;
    test_PolyMult_recursive();
    std::cout << std::endl << "test_PolyMult_dynamic" << std::endl << std::endl;
    test_PolyMult_dynamic();
    std::cout << std::endl << "test_FFT_works" << std::endl << std::endl;
    test_FFT_works();
    std::cout << std::endl << "test_FFT_vs_previous_approaches" << std::endl << std::endl;
    test_FFT_vs_previous_approaches();

  } else if(which == "pmr") {
    test_PolyMult_recursive();
  } else if(which == "pmd") {
    test_PolyMult_dynamic();
  } else if(which == "fftworks") {
    test_FFT_works();
  } else if(which == "polymultworks") {
    test_PolyMult_works();
  } else if(which == "fftvsold") {
    test_FFT_vs_previous_approaches();
  }
}

void Test::run(std::string which) {
  if(which == "") {
    std::cout << "Type which test to run." << std::endl;
  } else if(which == "pmr") {
    run_FFT_recursive();
  } else if(which == "pmd") {
    run_FFT_dynamic();
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
  fft.preComputeRBS(n);
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
  fft.preComputeRBS(n);

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
  fft.preComputeRBS(n);
  Vector<Complex> resultsD = fft.polyMultD(P, Q, n);

  for(int i = 0; i < n; ++i) {
    if(resultsD[i] != resultsR[i])
      std::cout << "*** Failed ***" << std::endl;

    std::cout << resultsR[i] << " " << resultsD[i] << std::endl;
  }
}

void Test::test_FFT_vs_previous_approaches() {
  std::cout << "Simple Test" << std::endl;

  {
    int n = 2;
    double p[] = {3,5};
    double q[] = {2,1};

    Vector<double> P(p, p+n);
    Vector<double> Q(q, q+n);

    Vector<Complex> PFFT (P.begin(), P.end());
    Vector<Complex> QFFT (Q.begin(), Q.end());

    PolyMult polyMultSB;
    Vector<double> pqActualSB = polyMultSB.schoolBook(n, P, Q);

    PolyMult polyMultDnC4;
    std::vector<double> pqActualDnC4 = polyMultDnC4.divideAndConquer4(n, P, Q);

    PolyMult polyMultDnC3;
    std::vector<double> pqActualDnC3 = polyMultDnC3.divideAndConquer3(n, P, Q);

    FFT fft;
    fft.preComputeOmega(n);
    Vector<Complex> pqActualFFTR = fft.polyMultR(PFFT, QFFT, n);
    fft.preComputeRBS(n);
    Vector<Complex> pqActualFFTD = fft.polyMultD(PFFT, QFFT, n);

    double pqExpected[] = {6,13,5};

    int w1 = 10;

    std::cout << std::setw(w1) << "Expected"
              << std::setw(w1) << "SB"
              << std::setw(w1) << "DnC 4"
              << std::setw(w1) << "DnC 3"
              << std::setw(w1) << "FFT R"
              << std::setw(w1) << "FFT D"
              << std::endl;

    std::cout << std::right;

    for(int index = 0; index < 2*n-1; ++index) {
      std::cout << std::setw(w1) << pqExpected[index]
                << std::setw(w1) << pqActualSB[index]
                << std::setw(w1) << pqActualDnC4[index]
                << std::setw(w1) << pqActualDnC3[index]
                << std::setw(w1) << pqActualFFTR[index].real()
                << std::setw(w1) << pqActualFFTD[index].real()
                << std::endl;
    }
  }

  std::cout << std::endl
            << "Random Test" << std::endl;

  {
    srand(time(0));

    int n = 8;
    int randMin = 0, randMax = 100;

    Vector<double> P(n), Q(n);

    Vector<Complex> PFFT(n), QFFT(n);

    for(int i = 0; i < n; ++i) {
      double randomP = rand()%(randMax-randMin)+randMin;
      double randomQ = rand()%(randMax-randMin)+randMin;

      P[i] = randomP;
      Q[i] = randomQ;

      PFFT[i] = Complex(randomP, 0);
      QFFT[i] = Complex(randomQ, 0);
    }

    PolyMult polyMultSB;
    Vector<double> pqActualSB = polyMultSB.schoolBook(n, P, Q);

    PolyMult polyMultDnC4;
    std::vector<double> pqActualDnC4 = polyMultDnC4.divideAndConquer4(n, P, Q);

    PolyMult polyMultDnC3;
    std::vector<double> pqActualDnC3 = polyMultDnC3.divideAndConquer3(n, P, Q);

    FFT fft;
    fft.preComputeOmega(n);
    Vector<Complex> pqActualFFTR = fft.polyMultR(PFFT, QFFT, n);
    fft.preComputeRBS(n);
    Vector<Complex> pqActualFFTD = fft.polyMultD(PFFT, QFFT, n);

    int w1 = 10;

    std::cout << std::setw(w1) << "SB"
              << std::setw(w1) << "DnC 4"
              << std::setw(w1) << "DnC 3"
              << std::setw(w1) << "FFT R"
              << std::setw(w1) << "FFT D"
              << std::endl;

    std::cout << std::right << std::fixed;

    for(int index = 0; index < 2*n-1; ++index) {
      std::cout << std::setprecision(0) << std::setw(w1) << pqActualSB[index]
                << std::setprecision(0) << std::setw(w1) << pqActualDnC4[index]
                << std::setprecision(0) << std::setw(w1) << pqActualDnC3[index]
                << std::setprecision(0) << std::setw(w1) << pqActualFFTR[index].real()
                << std::setprecision(0) << std::setw(w1) << pqActualFFTD[index].real()
                << std::endl;
    }
  }
}

void Test::run_FFT_dynamic() {
  int minN = 32, maxN = 2000000;

  std::cout << "FFT dynamic" << std::endl
            << "N" << '\t' << "Time" << std::endl;

  for(int n = minN; n <= maxN; n*=2) {

    FFT fft;
    fft.preComputeRBS(n);
    fft.preComputeOmega(n);

    int runTime = averageRuntime([&](){

      Vector<Complex> P = fft.makeRandomVector(n, -1, 1);
      Vector<Complex> Q = fft.makeRandomVector(n, -1, 1);

      Vector<Complex> result = fft.polyMultD(P, Q, n);

    }, RUNS);

    std::cout << n << '\t' << runTime << std::endl;
  }
}

void Test::run_FFT_recursive() {
  int minN = 32, maxN = 2000000;

  std::cout << "FFT recursive" << std::endl
            << "N" << '\t' << "Time" << std::endl;

  for(int n = minN; n <= maxN; n*=2) {

    FFT fft;
    fft.preComputeOmega(n);

    int runTime = averageRuntime([&](){

      Vector<Complex> P = fft.makeRandomVector(n, -1, 1);
      Vector<Complex> Q = fft.makeRandomVector(n, -1, 1);

      Vector<Complex> result = fft.polyMultR(P, Q, n);

    }, RUNS);

    std::cout << n << '\t' << runTime << std::endl;
  }
}
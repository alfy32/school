#include "Test.hpp"

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

  } else if(which == "schoolbook") {
    SchoolBook_works();
  } else if(which == "dnc4") {
    DnC4_works();
  }
}

void Test::run(std::string which) {
  if(which == "") {
    std::cout << "Type which test to run. example run schoolbook" << std::endl;
  } else if(which == "schoolbook") {
    SchoolBook();
  } else if(which == "dnc4") {
    DnC4();
  }
}

void Test::SchoolBook() {
  int minN = 32, maxN = 2000000;
  const int RUNS = 30;

  std::cout << "School Book Nested For Loops" << std::endl
            << "N" << '\t' << "Time" << std::endl;

  for(int n = minN; n <= maxN; n*=2) {

    int runTime = averageRuntime([=](){

      PolyMult polyMult(n);
      polyMult.schoolBook();

    }, 30);

    std::cout << n << '\t' << runTime << std::endl;
  }
}

void Test::SchoolBook_works() {
  { // TEST 1
    int n = 2;
    double p[] = {3,5};
    double q[] = {2,1};

    PolyMult polyMult(n, p, q);
    polyMult.schoolBook();
    polyMult.print();

    double pqExpected[] = {6,13,5};
    std::vector<double> pqActual = polyMult.getPQ();

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        return;
      }
    }
  }

  std::cout << std::endl;

  { // TEST 2
    int n = 3;
    double p[] = {2,3,4};
    double q[] = {2,1,6};

    PolyMult polyMult(n, p, q);
    polyMult.schoolBook();
    polyMult.print();

    double pqExpected[] = {4,8,23,22,24};
    std::vector<double> pqActual = polyMult.getPQ();

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        return;
      }
    }
  }
}

void Test::DnC4() {

}

void Test::DnC4_works() {
  { // TEST 1
    int n = 2;
    double p[] = {3,5};
    double q[] = {2,1};

    PolyMult polyMult(n, p, q);
    polyMult.divideAndConquer4(0, n-1, 0, n-1);
    polyMult.print();

    double pqExpected[] = {6,13,5};
    std::vector<double> pqActual = polyMult.getPQ();

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        break;
      }
    }
  }

  std::cout << std::endl;

  { // TEST 2
    int n = 3;
    double p[] = {2,3,4};
    double q[] = {2,1,6};

    PolyMult polyMult(n, p, q);
    polyMult.divideAndConquer4(0, n-1, 0, n-1);
    polyMult.print();

    double pqExpected[] = {4,8,23,22,24};
    std::vector<double> pqActual = polyMult.getPQ();

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        break;
      }
    }
  }
}
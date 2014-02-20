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

  } else if(which == "schoolbook") {
    SchoolBook_works();
  } else if(which == "dnc4") {
    DnC4_works();
  } else if(which == "dnc3") {
    DnC3_works();
  }
}

void Test::run(std::string which) {
  if(which == "") {
    std::cout << "Type which test to run. example run schoolbook" << std::endl;
  } else if(which == "schoolbook") {
    SchoolBook();
  } else if(which == "dnc4") {
    DnC4();
  } else if(which == "dnc3") {
    DnC3();
  }
}

void Test::SchoolBook() {
  int minN = 32, maxN = 2000000;

  std::cout << "School Book Nested For Loops" << std::endl
            << "N" << '\t' << "Time" << std::endl;

  for(int n = minN; n <= maxN; n*=2) {

    int runTime = averageRuntime([=](){

      PolyMult polyMult;

      std::vector<double> P = polyMult.makeRandomVector(n, -1, 1);
      std::vector<double> Q = polyMult.makeRandomVector(n, -1, 1);

      polyMult.schoolBook(n, P, Q);

    }, RUNS);

    std::cout << n << '\t' << runTime << std::endl;
  }
}

void Test::SchoolBook_works() {
  {
    int n = 2;
    double p[] = {3,5};
    double q[] = {2,1};

    std::vector<double> P(p, p+n);
    std::vector<double> Q(q, q+n);

    PolyMult polyMult;
    std::vector<double> pqActual = polyMult.schoolBook(n, P, Q);
    polyMult.print(n, P, Q, pqActual);

    double pqExpected[] = {6,13,5};

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        break;
      }
    }
  }

  std::cout << std::endl;

  {
    int n = 3;
    double p[] = {2,3,4};
    double q[] = {2,1,6};

    double pqExpected[] = {4,8,23,22,24};

    std::vector<double> P(p, p+n);
    std::vector<double> Q(q, q+n);

    PolyMult polyMult;
    std::vector<double> pqActual = polyMult.schoolBook(n, P, Q);
    polyMult.print(n, P, Q, pqActual);

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        break;
      }
    }
  }

  std::cout << std::endl;

  {
    int n = 4;
    double p[] = {2,3,4,2};
    double q[] = {2,1,6,3};

    double pqExpected[] = {4,8,23,32,35,24,6};

    std::vector<double> P(p, p+n);
    std::vector<double> Q(q, q+n);

    PolyMult polyMult;
    std::vector<double> pqActual = polyMult.schoolBook(n, P, Q);
    polyMult.print(n, P, Q, pqActual);

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        break;
      }
    }
  }

  std::cout << std::endl;

  {
    int n = 5;
    double p[] = {2,3,4,2,3};
    double q[] = {2,1,6,3,4};

    double pqExpected[] = {4,8,23,32,49,39,40,17,12};

    std::vector<double> P(p, p+n);
    std::vector<double> Q(q, q+n);

    PolyMult polyMult;
    std::vector<double> pqActual = polyMult.schoolBook(n, P, Q);
    polyMult.print(n, P, Q, pqActual);

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        break;
      }
    }
  }

  std::cout << std::endl;

  {
    int n = 6;
    double p[] = {2,3,4,2,3,2};
    double q[] = {2,1,6,3,1,2};

    double pqExpected[] = {4,8,23,32,43,38,36,31,13,8,4};

    std::vector<double> P(p, p+n);
    std::vector<double> Q(q, q+n);

    PolyMult polyMult;
    std::vector<double> pqActual = polyMult.schoolBook(n, P, Q);
    polyMult.print(n, P, Q, pqActual);

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        break;
      }
    }
  }

  std::cout << std::endl;

  {
    int n = 7;
    double p[] = {2,3,4,2,3,2,5};
    double q[] = {2,1,6,3,1,2,6};

    double pqExpected[] = {4,8,23,32,43,38,58,54,67,35,27,22,30};

    std::vector<double> P(p, p+n);
    std::vector<double> Q(q, q+n);

    PolyMult polyMult;
    std::vector<double> pqActual = polyMult.schoolBook(n, P, Q);
    polyMult.print(n, P, Q, pqActual);

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        break;
      }
    }
  }

  std::cout << std::endl;

  {
    int n = 8;
    double p[] = {2,3,4,2,3,2,7,2};
    double q[] = {2,1,6,3,1,2,2,1};

    double pqExpected[] = {4,8,23,32,43,38,54,50,68,49,25,23,20,11,2};

    std::vector<double> P(p, p+n);
    std::vector<double> Q(q, q+n);

    PolyMult polyMult;
    std::vector<double> pqActual = polyMult.schoolBook(n, P, Q);
    polyMult.print(n, P, Q, pqActual);

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        break;
      }
    }
  }
}

void Test::DnC4() {
  int minN = 32, maxN = 2000000;

  std::cout << "DnC 4 smaller problems" << std::endl
            << "N" << '\t' << "Time" << std::endl;

  for(int n = minN; n <= maxN; n*=2) {

    int runTime = averageRuntime([=](){

      PolyMult polyMult;

      std::vector<double> P = polyMult.makeRandomVector(n, -1, 1);
      std::vector<double> Q = polyMult.makeRandomVector(n, -1, 1);

      polyMult.divideAndConquer4(n, P, Q);

    }, RUNS);

    std::cout << n << '\t' << runTime << std::endl;
  }
}

void Test::DnC4_works() {
  {
    int n = 2;
    double p[] = {3,5};
    double q[] = {2,1};

    double pqExpected[] = {6,13,5};

    std::vector<double> P(p, p+n);
    std::vector<double> Q(q, q+n);

    PolyMult polyMult;
    std::vector<double> pqActual = polyMult.divideAndConquer4(n, P, Q);
    polyMult.print(n, P, Q, pqActual);

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        break;
      }
    }
  }

  std::cout << std::endl;

  {
    int n = 4;
    double p[] = {2,3,4,2};
    double q[] = {2,1,6,3};

    double pqExpected[] = {4,8,23,32,35,24,6};

    std::vector<double> P(p, p+n);
    std::vector<double> Q(q, q+n);

    PolyMult polyMult;
    std::vector<double> pqActual = polyMult.divideAndConquer4(n, P, Q);
    polyMult.print(n, P, Q, pqActual);

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        break;
      }
    }
  }

  std::cout << std::endl;

  {
    int n = 8;
    double p[] = {2,3,4,2,3,2,7,2};
    double q[] = {2,1,6,3,1,2,2,1};

    double pqExpected[] = {4,8,23,32,43,38,54,50,68,49,25,23,20,11,2};

    std::vector<double> P(p, p+n);
    std::vector<double> Q(q, q+n);

    PolyMult polyMult;
    std::vector<double> pqActual = polyMult.divideAndConquer4(n, P, Q);
    polyMult.print(n, P, Q, pqActual);

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        break;
      }
    }
  }

  std::cout << std::endl;
}

void Test::DnC3() {
  int minN = 32, maxN = 2000000;

  std::cout << "DnC 4 smaller problems" << std::endl
            << "N" << '\t' << "Time" << std::endl;

  for(int n = minN; n <= maxN; n*=2) {

    int runTime = averageRuntime([=](){

      PolyMult polyMult;

      std::vector<double> P = polyMult.makeRandomVector(n, -1, 1);
      std::vector<double> Q = polyMult.makeRandomVector(n, -1, 1);

      polyMult.divideAndConquer3(n, P, Q);

    }, RUNS);

    std::cout << n << '\t' << runTime << std::endl;
  }
}

void Test::DnC3_works() {
  {
    int n = 2;
    double p[] = {3,5};
    double q[] = {2,1};

    double pqExpected[] = {6,13,5};

    std::vector<double> P(p, p+n);
    std::vector<double> Q(q, q+n);

    PolyMult polyMult;
    std::vector<double> pqActual = polyMult.divideAndConquer3(n, P, Q);
    polyMult.print(n, P, Q, pqActual);

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        break;
      }
    }
  }

  std::cout << std::endl;

  {
    int n = 4;
    double p[] = {2,3,4,2};
    double q[] = {2,1,6,3};

    double pqExpected[] = {4,8,23,32,35,24,6};

    std::vector<double> P(p, p+n);
    std::vector<double> Q(q, q+n);

    PolyMult polyMult;
    std::vector<double> pqActual = polyMult.divideAndConquer3(n, P, Q);
    polyMult.print(n, P, Q, pqActual);

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        break;
      }
    }
  }

  std::cout << std::endl;

  {
    int n = 8;
    double p[] = {2,3,4,2,3,2,7,2};
    double q[] = {2,1,6,3,1,2,2,1};

    double pqExpected[] = {4,8,23,32,43,38,54,50,68,49,25,23,20,11,2};

    std::vector<double> P(p, p+n);
    std::vector<double> Q(q, q+n);

    PolyMult polyMult;
    std::vector<double> pqActual = polyMult.divideAndConquer3(n, P, Q);
    polyMult.print(n, P, Q, pqActual);

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        break;
      }
    }
  }

  std::cout << std::endl;
}
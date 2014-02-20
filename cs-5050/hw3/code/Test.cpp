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
    polyMult.schoolBook();
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

  std::cout << std::endl;

  { // TEST 3
    int n = 4;
    double p[] = {2,3,4,2};
    double q[] = {2,1,6,3};

    PolyMult polyMult(n, p, q);
    polyMult.schoolBook();
    polyMult.print();

    double pqExpected[] = {4,8,23,32,35,24,6};
    std::vector<double> pqActual = polyMult.getPQ();

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        break;
      }
    }
  }

  std::cout << std::endl;

  { // TEST 4
    int n = 5;
    double p[] = {2,3,4,2,3};
    double q[] = {2,1,6,3,4};

    PolyMult polyMult(n, p, q);
    polyMult.schoolBook();
    polyMult.print();

    double pqExpected[] = {4,8,23,32,49,39,40,17,12};
    std::vector<double> pqActual = polyMult.getPQ();

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        break;
      }
    }
  }

  std::cout << std::endl;

  { // TEST 5
    int n = 6;
    double p[] = {2,3,4,2,3,2};
    double q[] = {2,1,6,3,1,2};

    PolyMult polyMult(n, p, q);
    polyMult.schoolBook();
    polyMult.print();

    double pqExpected[] = {4,8,23,32,43,38,36,31,13,8,4};
    std::vector<double> pqActual = polyMult.getPQ();

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        return;
      }
    }
  }

  std::cout << std::endl;

  { // TEST 6
    int n = 7;
    double p[] = {2,3,4,2,3,2,5};
    double q[] = {2,1,6,3,1,2,6};

    PolyMult polyMult(n, p, q);
    polyMult.schoolBook();
    polyMult.print();

    double pqExpected[] = {4,8,23,32,43,38,58,54,67,35,27,22,30};
    std::vector<double> pqActual = polyMult.getPQ();

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        return;
      }
    }
  }

  std::cout << std::endl;

  { // TEST 7
    int n = 8;
    double p[] = {2,3,4,2,3,2,7,2};
    double q[] = {2,1,6,3,1,2,2,1};

    PolyMult polyMult(n, p, q);
    polyMult.schoolBook();
    polyMult.print();

    double pqExpected[] = {4,8,23,32,43,38,54,50,68,49,25,23,20,11,2};
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
  int minN = 32, maxN = 2000000;
  const int RUNS = 30;

  std::cout << "DnC 4 smaller problems" << std::endl
            << "N" << '\t' << "Time" << std::endl;

  for(int n = minN; n <= maxN; n*=2) {

    int runTime = averageRuntime([=](){

      PolyMult polyMult(n);
      polyMult.divideAndConquer4(0, n-1, 0, n-1);

    }, 30);

    std::cout << n << '\t' << runTime << std::endl;
  }
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

  std::cout << std::endl;

  { // TEST 3
    int n = 4;
    double p[] = {2,3,4,2};
    double q[] = {2,1,6,3};

    PolyMult polyMult(n, p, q);
    polyMult.divideAndConquer4(0, n-1, 0, n-1);
    polyMult.print();

    double pqExpected[] = {4,8,23,32,35,24,6};
    std::vector<double> pqActual = polyMult.getPQ();

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        return;
      }
    }
  }

  { // TEST 4
    int n = 5;
    double p[] = {2,3,4,2,3};
    double q[] = {2,1,6,3,4};

    PolyMult polyMult(n, p, q);
    polyMult.divideAndConquer4(0, n-1, 0, n-1);
    polyMult.print();

    double pqExpected[] = {4,8,23,32,49,39,40,17,12};
    std::vector<double> pqActual = polyMult.getPQ();

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        break;
      }
    }
  }

  { // TEST 5
    int n = 6;
    double p[] = {2,3,4,2,3,2};
    double q[] = {2,1,6,3,1,2};

    PolyMult polyMult(n, p, q);
    polyMult.divideAndConquer4(0, n-1, 0, n-1);
    polyMult.print();

    double pqExpected[] = {4,8,23,32,43,38,36,31,13,8,4};
    std::vector<double> pqActual = polyMult.getPQ();

    for(int index = 0; index < 2*n-1; ++index) {
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        return;
      }
    }
  }
}

void Test::DnC3() {
  // int minN = 32, maxN = 2000000;
  // const int RUNS = 30;

  // std::cout << "DnC 4 smaller problems" << std::endl
  //           << "N" << '\t' << "Time" << std::endl;

  // for(int n = minN; n <= maxN; n*=2) {

  //   int runTime = averageRuntime([=](){

  //     PolyMult polyMult(n);
  //     polyMult.divideAndConquer3(0, n-1, 0, n-1);

  //   }, 30);

  //   std::cout << n << '\t' << runTime << std::endl;
  // }
}

void Test::DnC3_works() {
  { // TEST 1
    int n = 2;
    double p[] = {3,5};
    double q[] = {2,1};

    std::vector<double> P(p, p+n);
    std::vector<double> Q(q, q+n);

    PolyMult polyMult(n, p, q);
    std::vector<double> pqActual = polyMult.divideAndConquer3(n, P, Q);

    double pqExpected[] = {6,13,5};

    for(int index = 0; index < 2*n-1; ++index) {
      std::cout << index << "\t" << pqActual[index] << "\t" << pqExpected[index] << std::endl;
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        // break;
      }
    }
  }

  std::cout << std::endl;

  // { // TEST 2
  //   int n = 3;
  //   double p[] = {2,3,4};
  //   double q[] = {2,1,6};

  //   std::vector<double> P(p, p+n);
  //   std::vector<double> Q(q, q+n);

  //   PolyMult polyMult(n, p, q);
  //   std::vector<double> pqActual = polyMult.divideAndConquer3(n, P, Q);

  //   double pqExpected[] = {4,8,23,22,24};

  //   for(int index = 0; index < 2*n-1; ++index) {
  //     std::cout << index << "\t" << pqActual[index] << "\t" << pqExpected[index] << std::endl;
  //     if(pqActual[index] != pqExpected[index]) {
  //       std::cout << "************** Failed ***************" << std::endl;
  //       // break;
  //     }
  //   }
  // }

  std::cout << std::endl << "n: " << 4 << std::endl;

  { // TEST 3
    int n = 4;
    double p[] = {2,3,4,2};
    double q[] = {2,1,6,3};

    std::vector<double> P(p, p+n);
    std::vector<double> Q(q, q+n);

    PolyMult polyMult(n, p, q);
    std::vector<double> pqActual = polyMult.divideAndConquer3(n, P, Q);

    double pqExpected[] = {4,8,23,32,35,24,6};

    for(int index = 0; index < 2*n-1; ++index) {
      std::cout << index << "\t" << pqActual[index] << "\t" << pqExpected[index] << std::endl;
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        // break;
      }
    }
  }

  // std::cout << std::endl << "n: " << 5 << std::endl;

  // { // TEST 4
  //   int n = 5;
  //   double p[] = {2,3,4,2,3};
  //   double q[] = {2,1,6,3,4};

  //   std::vector<double> P(p, p+n);
  //   std::vector<double> Q(q, q+n);

  //   PolyMult polyMult(n, p, q);
  //   std::vector<double> pqActual = polyMult.divideAndConquer3(n, P, Q);

  //   double pqExpected[] = {4,8,23,32,49,39,40,17,12};

  //   for(int index = 0; index < 2*n-1; ++index) {
  //     if(pqActual[index] != pqExpected[index]) {
  //       std::cout << "************** Failed ***************" << std::endl;
  //       break;
  //     }
  //   }
  // }

  // std::cout << std::endl << "n: " << 6 << std::endl;

  // { // TEST 5
  //   int n = 6;
  //   double p[] = {2,3,4,2,3,2};
  //   double q[] = {2,1,6,3,1,2};

  //   std::vector<double> P(p, p+n);
  //   std::vector<double> Q(q, q+n);

  //   PolyMult polyMult(n, p, q);
  //   std::vector<double> pqActual = polyMult.divideAndConquer3(n, P, Q);

  //   double pqExpected[] = {4,8,23,32,43,38,36,31,13,8,4};

  //   for(int index = 0; index < 2*n-1; ++index) {
  //     std::cout << index << "\t" << pqActual[index] << "\t" << pqExpected[index] << std::endl;
  //     if(pqActual[index] != pqExpected[index]) {
  //       std::cout << "************** Failed ***************" << std::endl;
  //       // break;
  //     }
  //   }
  // }

  std::cout << std::endl << "n: " << 8 << std::endl;

  { // TEST 7
    int n = 8;
    double p[] = {2,3,4,2,3,2,7,2};
    double q[] = {2,1,6,3,1,2,2,1};

    std::vector<double> P(p, p+n);
    std::vector<double> Q(q, q+n);

    PolyMult polyMult(n, p, q);
    std::vector<double> pqActual = polyMult.divideAndConquer3(n, P, Q);

    double pqExpected[] = {4,8,23,32,43,38,54,50,68,49,25,23,20,11,2};

    for(int index = 0; index < 2*n-1; ++index) {
      std::cout << index << "\t" << pqActual[index] << "\t" << pqExpected[index] << std::endl;
      if(pqActual[index] != pqExpected[index]) {
        std::cout << "************** Failed ***************" << std::endl;
        return;
      }
    }
  }
}
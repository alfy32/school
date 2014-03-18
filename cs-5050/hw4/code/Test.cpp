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

  }
}

void Test::run(std::string which) {
  if(which == "") {
    std::cout << "Type which test to run." << std::endl;
  }
}


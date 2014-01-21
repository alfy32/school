#include "test.hpp"

template<typename T>
double Test::run(T t, int runs) {
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

void Test::test1(int runs, int bagSize,
  int minItemSize, int maxItemSize,
  int minValue, int maxValue,
  int minN, int maxN) {

  std::cout << "Bag Size: " << bagSize << std::endl;
  std::cout << "Item Size: " << minItemSize << "-" << maxItemSize << std::endl;
  std::cout << "Runs: " << runs << std::endl;

  std::cout << "n\ttime(ms)" << std::endl;

  for(int n = minN; n < maxN; n++) {
    double runTime = run([&]() {
      Knapsack knapsack(n);
      
      knapsack.initSize(minItemSize, maxItemSize);
      knapsack.initValue(minValue, maxValue);

      knapsack.fillBagRecursive(n,bagSize);
    }, runs);

    std::cout << n << '\t' << runTime << std::endl;
  }
}

void Test::test2dynamic(int runs) {
  int minN = 64;
  int maxN = 10000;

  int minValue = 1;
  int maxValue = 100;

  std::cout << "n\twide\tnarrow" << std::endl;

  for(int n = minN; n < maxN; n*=2) {
    int bagSize = 10*n;
    int maxSize = bagSize/10;

    double wideRunTime = run([=]() {
      int minSize = 1;

      Knapsack knapsack(n);

      knapsack.initSize(minSize, maxSize);
      knapsack.initValue(minValue, maxValue);

      knapsack.fillBagDynamic(n,bagSize);
    }, runs);

    double narrowRunTime = run([=]() {
      int minSize = bagSize/20;

      Knapsack knapsack(minSize, maxSize, n, bagSize);

      knapsack.initSize(minSize, maxSize);
      knapsack.initValue(minValue, maxValue);

      knapsack.fillBagDynamic(n,bagSize);
    }, runs);

    std::cout << n << '\t' << wideRunTime << '\t' << narrowRunTime << std::endl;
  }
}

void Test::test2caching(int runs) {
  int minN = 64;
  int maxN = 10000;

  int minValue = 1;
  int maxValue = 100;

  std::cout << "n\twide\tnarrow" << std::endl;

  for(int n = minN; n < maxN; n*=2) {
    int bagSize = 10*n;
    int maxSize = bagSize/10;

    double wideRunTime = run([=]() {
      int minSize = 1;

      Knapsack knapsack(n);

      knapsack.initSize(minSize, maxSize);
      knapsack.initValue(minValue, maxValue);

      knapsack.fillBagCaching(n,bagSize);
    }, runs);

    double narrowRunTime = run([=]() {
      int minSize = bagSize/20;

      Knapsack knapsack(minSize, maxSize, n, bagSize);

      knapsack.initSize(minSize, maxSize);
      knapsack.initValue(minValue, maxValue);

      knapsack.fillBagCaching(n,bagSize);
    }, runs);

    std::cout << n << '\t' << wideRunTime << '\t' << narrowRunTime << std::endl;
  }
}